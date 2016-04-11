import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Spot which can be occupied by a vehicle. Its relevant properties are its ID
 * and its hourly rate.
 *
 * Exposed methods:
 * - isOccupied()
 * - occupant()
 * - spotID()
 * - arrivalTime()
 * - occupy(Vehicle v, Integer arrivalTime)
 * - vacate(Integer departureTime)
 */
public class ParkingSpot {
    private final String mSpotID;
    private final String mSpotType;
    private final BigDecimal mMinuteRate;
    private Boolean mIsOccupied;
    private Vehicle mOccupant;
    private Integer mArrivalTime;

    /**
     * Creates a new ParkingSpot.
     *
     * @param spotID Unique identifier for the spot in the ParkingLot.
     * @param spotType Type of vehicle the spot is designed for (does not mean
     *                 only vehicles of this type are allowed to occupy it)
     * @param hourlyRate Hourly rate for occupying this spot.
     */
    public ParkingSpot(String spotID, String spotType, BigDecimal hourlyRate){
        mSpotID = spotID;
        mSpotType = spotType;
        mMinuteRate = hourlyRate.divide(new BigDecimal(60.0), MathContext.DECIMAL128);
        mIsOccupied = false;
        mOccupant = null;
    }

    public Boolean isOccupied(){
        return mIsOccupied;
    }

    public Vehicle occupant(){
        return mOccupant;
    }

    public String spotID(){
        return mSpotID;
    }

    public Integer arrivalTime(){
        return mArrivalTime;
    }

    /**
     * Occupies the parking spot with a vehicle which arrived at a certain
     * time.
     *
     * @param v New occupant
     * @param arrivalTime Time at which the vehicle arrived (in minutes)
     * @throws ParkingException Thrown if the spot is already occupied
     */
    public void occupy(Vehicle v, Integer arrivalTime) throws ParkingException {
        if (mIsOccupied){
            throw new ParkingException("Attempted to park on an occupied spot");
        }
        mIsOccupied = true;
        mOccupant = v;
        mArrivalTime = arrivalTime;
    }

    /**
     * Vacates the parking spot and calculates the price to be paid by the
     * occupant.
     *
     * @param departureTime Time at which the occupant left
     * @return Value to be paid
     * @throws ParkingException Thrown if the spot it not occupied
     */
    public BigDecimal vacate(Integer departureTime) throws ParkingException {
        if (!mIsOccupied){
            throw new ParkingException("Attempted to depart from a non-occupied spot");
        }
        mIsOccupied = false;
        mOccupant = null;
        BigDecimal interval = new BigDecimal(departureTime - mArrivalTime);
        return mMinuteRate.multiply(interval, MathContext.DECIMAL128);
    }

}
