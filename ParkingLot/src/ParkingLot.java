import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Describe this class and the methods exposed by it.
 */
public class ParkingLot {
    private HashMap<String, ArrayList<ArrayList<ParkingSpot>>> mParkingSpots;

    public ParkingLot(HashMap<String, ArrayList<ArrayList<ParkingSpot>>> spots){
        mParkingSpots = spots;
    }

    void park(Vehicle v, Integer arrivalTime) throws ParkingException {
        Iterator<ArrayList<ParkingSpot>> typeIt = mParkingSpots.get(v.type()).iterator();
        while(typeIt.hasNext()) {
            Iterator<ParkingSpot> spotIt = typeIt.next().iterator();
            while(spotIt.hasNext()) {
                ParkingSpot p = spotIt.next();
                if(!p.isOccupied()) {
                    p.occupy(v, arrivalTime);
                    System.out.println(p.spotID());
                    return;
                }
                else if(p.occupant().plate() == v.plate()) {
                    throw new ParkingException("This is a cloned car! "
                            + "Call the cops! Refusing to accept the car into "
                            + "the parking lot. Fight with your life, brave "
                            + "Merida!");
                }
            }
        }

        throw new ParkingException("Failed to find a suitable parking spot!");
    }

    void depart(Vehicle v, Integer departureTime) throws ParkingException {
        Iterator<ArrayList<ParkingSpot>> typeIt = mParkingSpots.get(v.type()).iterator();
        while(typeIt.hasNext()) {
            Iterator<ParkingSpot> spotIt = typeIt.next().iterator();
            while(spotIt.hasNext()) {
                ParkingSpot p = spotIt.next();
                if(p.isOccupied() && p.occupant().plate() == v.plate()) {
                    BigDecimal price = p.vacate(departureTime);
                    Integer hour = departureTime / 60;
                    System.out.println(String.format("%s;%d:%d;%,.2f",
                            v.type(), hour, departureTime - hour*60,
                            new DecimalFormat("0,00").format(price)));
                }
            }
        }

        throw new ParkingException("Didn't find the car. Call the cops, "
                 + "and pray to God that it is not your fault.");
    }
}
