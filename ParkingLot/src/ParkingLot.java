import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Place where vehicles can park for an hourly wage. It defines supported
 * vehicle types, existing spot types and which vehicles can park at which
 * spots. It should not be created manually, but only via the ParkingLotBuilder
 * class, which parses a settings file.
 *
 * Exposed methods:
 * - park(Vehicle v, Integer arrivalTime)
 * - depart(Vehicle v, Integer departureTime)
 */
public class ParkingLot {
    private HashMap<String, ArrayList<ArrayList<ParkingSpot>>> mParkingSpots;

    /**
     * Create a new ParkingLot. Not supposed to be called directly, but through
     * a ParkingLotBuilder object.
     *
     * @param spots Map between a vehicle type and lists of spots where such a
     *              vehicle would be allowed to park. More about this in the
     *              ParkingLotBuilder documentation.
     */
    public ParkingLot(HashMap<String, ArrayList<ArrayList<ParkingSpot>>> spots){
        mParkingSpots = spots;
    }

    /**
     * Attempt to park a vehicle. If successful, the ID from the spot where it
     * is parked gets printed to stdout. Else, "LOTADO" is printed, warning the
     * user that there are no vacant spots for such a vehicle.
     *
     * @param v Vehicle to be parked.
     * @param arrivalTime Time of arrival, in minutes.
     * @return Feedback string related to the parking operation
     * @throws ParkingException Thrown if a vehicle with such a plate is alrea-
     *                          dy parked in this lot.
     */
    String park(Vehicle v, Integer arrivalTime) throws ParkingException {
        for (ArrayList<ParkingSpot> parkingSpots : mParkingSpots.get(v.type())) {
            for (ParkingSpot p : parkingSpots) {
                if (!p.isOccupied()) {
                    p.occupy(v, arrivalTime);
                    return p.spotID();
                } else if (p.occupant().plate().equals(v.plate())) {
                    throw new ParkingException("This is a cloned car! "
                        + "Call the cops! Refusing to accept the car into "
                        + "the parking lot. Fight with your life, brave "
                        + "Merida!");
                }
            }
        }

        return "LOTADO";
    }

    /**
     * Remove a parked vehicle from the parking lot and print to stdout some
     * information: how much its owner owes, for how long the vehicle had been
     * parked and the type of spot where it had been parked.
     *
     * @param v Departing Vehicle
     * @param departureTime Time of departure, in minutes
     * @return Feedback string related to the departure operation
     * @throws ParkingException Thrown if no such vehicle exists in the lot
     */
    String depart(Vehicle v, Integer departureTime) throws ParkingException {
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

                    return String.format("%s;%s:%s;%s", v.type(), hours, minutes, priceStr);

                }
            }
        }

        throw new ParkingException("Didn't find the car. Call the cops, "
                 + "and pray to God that it is not your fault.");
    }
}
