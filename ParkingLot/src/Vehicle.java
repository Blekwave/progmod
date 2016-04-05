/**
 * Generic vehicle with no defined type.
 * Subclasses must specify the vehicle's type through the constructor.
 */
public abstract class Vehicle {
    private String plate;
    final String type;

    public Vehicle(String plate, String type){
        this.plate = plate;
        this.type = type;
    }

    public String getPlate(){
        return plate;
    }

    public String getType(){
        return type;
    }
}
