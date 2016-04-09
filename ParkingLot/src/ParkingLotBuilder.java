import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkingLotBuilder {
    public class ParkingSpotConfig {
        private String mType;
        private Integer mSpotsPerLevel;
        private BigDecimal mHourlyRate;

        public String type() {
            return mType;
        }

        public Integer spotsPerLevel(){
            return mSpotsPerLevel;
        }

        public BigDecimal hourlyRate(){
            return mHourlyRate;
        }


        public ParkingSpotConfig(String type, Integer spotsPerLevel, BigDecimal hourlyRate){
            mType = type;
            mSpotsPerLevel = spotsPerLevel;
            mHourlyRate = hourlyRate;
        }

    }

    private class ConfigFormatException extends IOException {
        public ConfigFormatException(String message){
            super(message);
        }

        public ConfigFormatException(String message, Throwable cause){
            super(message, cause);
        }
    }

    private Integer mNumLevels;
    private ArrayList<ParkingSpotConfig> mSpotTypes;
    private HashMap<String, ArrayList<String>> mVehicleSpotRelations;
    private Boolean mParsingVehicleSpotRelations = false;

    private static String nextNonEmptyLineOrEOF(BufferedReader br) throws IOException {
        String line;
        do {
            line = br.readLine();
        } while (line != null && line.isEmpty());
        return line;
    }

    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String line = nextNonEmptyLineOrEOF(br);
        if (line == null){
            throw new EOFException();
        }
        return line;
    }

    public ParkingLotBuilder (String configPath) throws IOException {
        mSpotTypes = new ArrayList<>();
        mVehicleSpotRelations = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(configPath))) {
            parseNumLevels(br);
            parseSpotTypes(br);
            parseVehicleSpotRelations(br);
        } catch (EOFException e) {
            if (!mParsingVehicleSpotRelations) {
                throw new ConfigFormatException("Unexpected end of file.", e);
            } else {
                mParsingVehicleSpotRelations = false;
            }
        }
    }

    private static Pattern sSpotTypeRegex = Pattern.compile(
        "(?<type>\\w+) x(?<spotsperlevel>\\d+) \\$(?<hourlyrate>\\d+(?:\\.\\d+)?)"
    );
    private static Pattern sLevelsRegex = Pattern.compile("(\\d+) LEVELS");
    private static Pattern sVehicleSpotRelationRegex = Pattern.compile(
        "(\\w+): (?:(\\w+) > )*(\\w+)"
    );

    private void parseNumLevels(BufferedReader br) throws IOException {
        String line;// Read levels line: <INT> LEVELS
        line = nextNonEmptyLine(br);

        Matcher levelsMatcher = sLevelsRegex.matcher(line);
        if (levelsMatcher.matches()){
            mNumLevels = new Integer(levelsMatcher.group(1));
        } else {
            throw new ConfigFormatException("Failed to read number of levels");
        }
    }

    private void parseSpotTypes(BufferedReader br) throws IOException {
        String line;// Read spot types header: [SPOT TYPES]
        line = nextNonEmptyLine(br);
        String sSpotTypesHeader = "[SPOT TYPES]";
        if (!line.equals(sSpotTypesHeader)){
            throw new ConfigFormatException("Failed to read spot types header");
        }

        // Read spot type descriptions: <STR> x<INT> $<DECIMAL>
        //                              type  count  hourly rate
        // Stop when it reads the vehicle spot relations header:
        // [VEHICLE SPOT RELATIONS]
        String sVehicleSpotRelationsHeader = "[VEHICLE SPOT RELATIONS]";
        while (!(line = nextNonEmptyLine(br)).equals(sVehicleSpotRelationsHeader)){
            Matcher spotTypeMatcher = sSpotTypeRegex.matcher(line);
            if (spotTypeMatcher.matches()){
                ParkingSpotConfig psc = new ParkingSpotConfig(
                    spotTypeMatcher.group("type"),
                    new Integer(spotTypeMatcher.group("spotsperlevel")),
                    new BigDecimal(spotTypeMatcher.group("hourlyrate"))
                );
                mSpotTypes.add(psc);
            } else {
                throw new ConfigFormatException("Failed to read spot type description");
            }
        }
    }

    private void parseVehicleSpotRelations(BufferedReader br) throws IOException {
        String line;
        mParsingVehicleSpotRelations = true;

        // Read vehicle spot relations: <STR>: [<STR> > (...)]<STR>
        // Will throw EOFException when the file reaches the end
        while ((line = nextNonEmptyLineOrEOF(br)) != null){
            Matcher vehicleSpotRelationMatcher = sVehicleSpotRelationRegex.matcher(line);
            if (vehicleSpotRelationMatcher.matches()){
                String vehicleType = vehicleSpotRelationMatcher.group(1); // vehicle type
                ArrayList<String> spotTypePrecedence = new ArrayList<>();
                for (int i = 2; i <= vehicleSpotRelationMatcher.groupCount(); i++){
                    spotTypePrecedence.add(vehicleSpotRelationMatcher.group(i));
                }
                mVehicleSpotRelations.put(vehicleType, spotTypePrecedence);
            } else {
                throw new ConfigFormatException("Failed to read vehicle spot relation description");
            }
        }
    }

    public ParkingLot build(){
        HashMap<String, ArrayList<ParkingSpot>> spotsByType;
        spotsByType = generateSpotsByType();
        HashMap<String, ArrayList<ArrayList<ParkingSpot>>> spotListsByVehicle;
        spotListsByVehicle = generateVehicleSpotPriorityMap(spotsByType);
        return new ParkingLot(spotListsByVehicle);
    }

    private HashMap<String,ArrayList<ParkingSpot>> generateSpotsByType() {
        HashMap<String, ArrayList<ParkingSpot>> spotsByType;
        spotsByType = new HashMap<>();
        for (ParkingSpotConfig spotConfig : mSpotTypes){
            ArrayList<ParkingSpot> spots = new ArrayList<>();
            for (int level = 1; level <= mNumLevels; level++){
                for (int id = 1; id <= spotConfig.spotsPerLevel(); id++){
                    ParkingSpot spot = new ParkingSpot(
                        String.format("N%d%s%d", level, spotConfig.type(), id),
                        spotConfig.type(),
                        spotConfig.hourlyRate()
                    );
                    spots.add(spot);
                }
            }
            spotsByType.put(spotConfig.type(), spots);
        }
        return spotsByType;
    }

    private HashMap<String, ArrayList<ArrayList<ParkingSpot>>>
    generateVehicleSpotPriorityMap (HashMap<String, ArrayList<ParkingSpot>> spotsByType){
        HashMap<String, ArrayList<ArrayList<ParkingSpot>>> spotListsByVehicle;
        spotListsByVehicle = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> relation :
                mVehicleSpotRelations.entrySet()){
            String vehicleType = relation.getKey();
            ArrayList<String> spotTypePriorities = relation.getValue();

            ArrayList<ArrayList<ParkingSpot>> spotLists;
            spotLists = new ArrayList<>();

            for (String spotType : spotTypePriorities) {
                spotLists.add(spotsByType.get(spotType));
            }

            spotListsByVehicle.put(vehicleType, spotLists);
        }
        return spotListsByVehicle;
    }

}
