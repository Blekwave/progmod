import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Describe this class and the methods exposed by it.
 */
public class ParkingLot {

    private HashMap<String, ArrayList<ArrayList<ParkingSpot>>> mSpotListsByVehicle;
    private Integer mNumLevels;

    public ParkingLot(ParkingLotConfig config){
        mNumLevels = config.numLevels();
        HashMap<String, ArrayList<ParkingSpot>> spotsByType;
        spotsByType = generateSpotsByType(config);
        mSpotListsByVehicle = new HashMap<>();
        assignSpotListsToVehicleTypes(config, spotsByType);
    }

    private HashMap<String,ArrayList<ParkingSpot>> generateSpotsByType(ParkingLotConfig config) {
        Iterator<ParkingLotConfig.ParkingSpotConfig> it;
        HashMap<String, ArrayList<ParkingSpot>> spotsByType;
        it = config.spotTypesIterator();
        spotsByType = new HashMap<>();
        while (it.hasNext()){
            ParkingLotConfig.ParkingSpotConfig spotConfig = it.next();
            ArrayList<ParkingSpot> spots = new ArrayList<>();
            for (int level = 1; level <= config.numLevels(); level++){
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

    private void assignSpotListsToVehicleTypes(ParkingLotConfig config, HashMap<String, ArrayList<ParkingSpot>> spotsByType) {
        Iterator<Map.Entry<String, ArrayList<String>>> it;
        it = config.vehicleSpotRelationsIterator();

        while (it.hasNext()){
            Map.Entry<String, ArrayList<String>> relation;
            relation = it.next();
            String vehicleType = relation.getKey();
            ArrayList<String> spotTypePriorities = relation.getValue();

            ArrayList<ArrayList<ParkingSpot>> spotLists;
            spotLists = new ArrayList<>();

            for (String spotType : spotTypePriorities) {
                spotLists.add(spotsByType.get(spotType));
            }

            mSpotListsByVehicle.put(vehicleType, spotLists);
        }
    }
}
