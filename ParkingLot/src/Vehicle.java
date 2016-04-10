/**
 * Represents a vehicle which can park at a lot. Its relevant properties are
 * its license plate and its type.
 *
 * Exposed methods:
 * - plate()
 * - type()
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
