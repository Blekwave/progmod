import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which parses a config file and is able to generate ParkingLot objects
 * based on it. Its main responsibility is generating the map between vehicle
 * types and parking spots, prioritized by parking spot type first and parking
 * lot level second.
 *
 * Exposed methods:
 * - Constructor(String s)
 * - build()
 */
public class ParkingLotBuilder {

    private Integer mNumLevels;
    private ArrayList<ParkingSpotConfig> mSpotTypes;
    private HashMap<String, ArrayList<String>> mVehicleSpotRelations;

    /**
     * Internal class which contains information about parking spot types.
     * Parking spots have three relevant settings: type (string unique identi-
     * fier), spots per level (how many) and hourly rate.
     *
     * Exposed methods:
     * - type()
     * - spotsPerLevel()
     * - hourlyRate()
     */
    private class ParkingSpotConfig {
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

    /**
     * Exception raised whenever there's an issue related to the format of the
     * config file being parsed.
     */
    private class ConfigFormatException extends IOException {
        public ConfigFormatException(String message){
            super(message);
        }

        public ConfigFormatException(String message, Throwable cause){
            super(message, cause);
        }
    }

    private Boolean mParsingVehicleSpotRelations = false;

    /**
     * Reads a configuration file for a ParkingLot and parses it, so that new
     * parking lots can be built via build().
     *
     * The configuration file is defined as follows. First, the number of
     * levels in the parking lot is set:
     *
     *     <INT> LEVELS
     *
     * Then, a header specifies the beginning of a list of spot types, with
     * their respective numbers of spots per level and hourly wages:
     *
     *     [SPOT TYPES]
     *     <STR: spot type> x<INT: spots per level> $<DECIMAL: hourly wage>
     *     (as many as needed)
     *
     * Finally, there's the section which defines the parking spot priority for
     * each vehicle type. This section also implicitly defines the list of ve-
     * hicle types, and has a header of its own:
     *
     *     [VEHICLE SPOT RELATIONS]
     *     <STR: vehicle type>: [<STR: spot type> > (...)]<STR: spot type>
     *     (as many as needed)
     *
     * Any empty lines are disregarded. An example is provided for maximum cla-
     * rity:
     *
     *     4 LEVELS
     *
     *     [SPOT TYPES]
     *     VP x4 $6
     *     MT x2 $3.5
     *     VG x2 $8
     *     NE x2 $6
     *
     *     [VEHICLE SPOT RELATIONS]
     *     VP: VP > VG
     *     VG: VG
     *     MT: MT > VP > VG
     *     NE: NE
     *
     * @param configPath path to the configuration file.
     * @throws ConfigFormatException If there's something wrong with the confi-
     *                               guration file's format.
     * @throws IOException If BufferedReader.readLine raises it
     */
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

    private static Pattern sLevelsRegex = Pattern.compile("(\\d+) LEVELS");

    /**
     * Parses the number of levels from the config file.
     *
     * @param br Buffered reader from a text file
     * @throws EOFException If EOF was met before any non-empty lines were
     *                      found.
     * @throws IOException If BufferedReader.readLine raised it
     */
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

    private static Pattern sSpotTypeRegex = Pattern.compile(
        "(?<type>\\w+) x(?<spotsperlevel>\\d+) \\$(?<hourlyrate>\\d+(?:\\.\\d+)?)"
    );

    /**
     * Parses the spot types and their settings from the config file.
     *
     * @param br Buffered reader from a text file
     * @throws EOFException If EOF was met before any non-empty lines were
     *                      found.
     * @throws IOException If BufferedReader.readLine raised it
     */
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

    private static Pattern sVehicleSpotRelationRegex = Pattern.compile(
        "(?<vehicletype>\\w+): (?<spotpriority>(?:\\w+ > )*\\w+)"
    );

    private static Pattern sSpotPriorityRegex = Pattern.compile(
        "\\w+"
    );

    /**
     * Parses the priority relations between vehicle and spot types.
     *
     * @param br Buffered reader from a text file
     * @throws EOFException If EOF was met before any non-empty lines were
     *                      found.
     * @throws IOException If BufferedReader.readLine raised it
     */
    private void parseVehicleSpotRelations(BufferedReader br) throws IOException {
        String line;
        mParsingVehicleSpotRelations = true;

        // Read vehicle spot relations: <STR>: [<STR> > (...)]<STR>
        // Will throw EOFException when the file reaches the end
        while ((line = nextNonEmptyLineOrEOF(br)) != null){
            Matcher vehicleSpotRelationMatcher = sVehicleSpotRelationRegex.matcher(line);
            if (vehicleSpotRelationMatcher.matches()){
                String vehicleType = vehicleSpotRelationMatcher.group("vehicletype");
                String spotPriority = vehicleSpotRelationMatcher.group("spotpriority");

                Matcher spotPriorityMatcher = sSpotPriorityRegex.matcher(spotPriority);
                ArrayList<String> spotTypePrecedence = new ArrayList<>();
                while (spotPriorityMatcher.find()){
                    String spotType = spotPriorityMatcher.group();
                    spotTypePrecedence.add(spotType);
                }

                mVehicleSpotRelations.put(vehicleType, spotTypePrecedence);
            } else {
                throw new ConfigFormatException("Failed to read vehicle spot relation description");
            }
        }
    }

    /**
     * Builds a new ParkingLot object based on the parsed config file.
     *
     * @return ParkingLot object
     */
    public ParkingLot build(){
        HashMap<String, ArrayList<ArrayList<ParkingSpot>>> spotListsByVehicle;
        spotListsByVehicle = generateVehicleSpotPriorityMap();
        return new ParkingLot(spotListsByVehicle);
    }

    /**
     * Generates a list of ParkingSpot objects for each spot type. The lists
     * generated are sorted by level, in ascending order.
     *
     * @return Map from each parking spot type to its list of spots.
     */
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

    /**
     * Maps each vehicle type to a list of parking spot lists, ordered accor-
     * ding to each vehicle type's spot type priorities.
     *
     * @return The map described above.
     */
    private HashMap<String, ArrayList<ArrayList<ParkingSpot>>>
    generateVehicleSpotPriorityMap (){
        HashMap<String, ArrayList<ParkingSpot>> spotsByType = generateSpotsByType();
        HashMap<String, ArrayList<ArrayList<ParkingSpot>>> spotListsByVehicle;
        spotListsByVehicle = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> relation :
             mVehicleSpotRelations.entrySet()){
            String vehicleType = relation.getKey();
            ArrayList<String> spotTypePriorities = relation.getValue();

            ArrayList<ArrayList<ParkingSpot>> spotLists;
            spotLists = new ArrayList<>();

            for (String spotType : spotTypePriorities){
                spotLists.add(spotsByType.get(spotType));
            }

            spotListsByVehicle.put(vehicleType, spotLists);
        }
        return spotListsByVehicle;
    }

    /**
     * UTILITY METHODS
     */

    /**
     * Returns the next non-empty line in a buffered reader or null, if EOF is
     * met.
     *
     * @param br Buffered reader from a text file
     * @return The next non-empty line, or null, if EOF is met.
     * @throws IOException If BufferedReader.readLine raised it
     */
    private static String nextNonEmptyLineOrEOF(BufferedReader br) throws IOException {
        String line;
        do {
            line = br.readLine();
        } while (line != null && line.isEmpty());
        return line;
    }

    /**
     * Returns the next non-empty line in a buffered reader. Throws an excep-
     * tion on EOF.
     *
     * @param br Buffered reader from a text file
     * @return The next non-empty line.
     * @throws EOFException If EOF was met before any non-empty lines were
     *                      found.
     * @throws IOException If BufferedReader.readLine raised it
     */
    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String line = nextNonEmptyLineOrEOF(br);
        if (line == null){
            throw new EOFException();
        }
        return line;
    }
}
