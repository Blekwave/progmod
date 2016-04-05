/**
 * Describe this class and the methods exposed by it.
 */
public abstract class ParkingSpot {
    private String spotID;
    private Boolean occupied;
    private Vehicle occupant;
    private int arrivalTime;
    final String spotType;
    final double hourlyRate;

    public ParkingSpot(String spotID, String spotType, double hourlyRate){
        this.spotID = spotID;
        this.spotType = spotType;
        this.hourlyRate = hourlyRate;
    }

    public Boolean isOccupied(){
        return this.occupied;
    }

    public String getSpotID(){
        return this.spotID;
    }

    public void parkVehicle(Vehicle v, int arrivalTime){
        if (this.occupied){
            throw new ParkingSpot.ParkingException("Attempted to park on an occupied spot.");
        }
    }

    private double getPrice(int departureTime){
        return this.hourlyRate * (departureTime - this.arrivalTime) / 60;
    }

    public static class ParkingException extends Exception {
        public ParkingException(String message){
            super(message);
        }
    }
}
