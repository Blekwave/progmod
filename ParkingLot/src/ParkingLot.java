import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
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
        for (ArrayList<ParkingSpot> parkingSpots : mParkingSpots.get(v.type())) {
            for (ParkingSpot p : parkingSpots) {
                if (!p.isOccupied()) {
                    p.occupy(v, arrivalTime);
                    System.out.println(p.spotID());
                    return;
                } else if (p.occupant().plate().equals(v.plate())) {
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
        for (ArrayList<ParkingSpot> parkingSpots : mParkingSpots.get(v.type())) {
            for (ParkingSpot p : parkingSpots) {
                if (p.isOccupied() && p.occupant().plate().equals(v.plate())) {
                    BigDecimal price = p.vacate(departureTime);

                    Integer interval = departureTime - p.arrivalTime();

                    DecimalFormat twoDigits = new DecimalFormat("00");
                    DecimalFormat twoPlaces = new DecimalFormat("0.00");
                    DecimalFormatSymbols sym = DecimalFormatSymbols.getInstance();
                    sym.setDecimalSeparator(',');
                    twoPlaces.setDecimalFormatSymbols(sym);

                    String hours = twoDigits.format(interval / 60);
                    String minutes = twoDigits.format(interval % 60);
                    String priceStr = twoPlaces.format(price);

                    System.out.println(String.format("%s;%s:%s;%s",
                        v.type(), hours, minutes, priceStr));

                    return;
                }
            }
        }

        throw new ParkingException("Didn't find the car. Call the cops, "
                 + "and pray to God that it is not your fault.");
    }
}
