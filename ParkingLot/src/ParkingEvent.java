/**
 * Class that represents an event from a parking input.
 */
public class ParkingEvent {
    Vehicle mVehicle;
    Integer mTime;
    String mType;

    /**
     * Constructs a parking event.
     * @param plate the car's plate.
     * @param vehicleType the vehicle's type.
     * @param eventType the event type.
     * @param time the time of the event.
     */
    public ParkingEvent(String plate, String vehicleType, String eventType,
            Integer time) {
        mVehicle = new Vehicle(plate, vehicleType);
        mTime = time;
        mType = eventType;
    }

    /// Event type.
    String type() {
        return mType;
    }

    /// Vehicle of this event.
    Vehicle vehicle() {
        return mVehicle;
    }

    /// Time of the event, in minutes since the start of the day.
    Integer time() {
        return mTime;
    }
}
