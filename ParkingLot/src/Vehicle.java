/**
 * Encapsulates a vehicle that can be stored at the parking lot.
 */
public class Vehicle {
    private final String mPlate;
    private final String mType;

    public Vehicle(String plate, String type){
        mPlate = plate;
        mType = type;
    }

    public String plate(){
        return mPlate;
    }

    public String type(){
        return mType;
    }
}
