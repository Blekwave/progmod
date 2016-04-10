import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Describe this class and the methods exposed by it.
 */
public class ParkingSpot {
    private final String mSpotID;
    private final String mSpotType;
    private final BigDecimal mHourlyRate;
    private Boolean mIsOccupied;
    private Vehicle mOccupant;
    private Integer mArrivalTime;

    public ParkingSpot(String spotID, String spotType, BigDecimal hourlyRate){
        mSpotID = spotID;
        mSpotType = spotType;
        mHourlyRate = hourlyRate;
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

    public void occupy(Vehicle v, Integer arrivalTime) throws ParkingException {
        if (mIsOccupied){
            throw new ParkingException("Attempted to park on an occupied spot");
        }
        mIsOccupied = true;
        mOccupant = v;
        mArrivalTime = arrivalTime;
    }

    public BigDecimal vacate(Integer departureTime) throws ParkingException {
        if (!mIsOccupied){
            throw new ParkingException("Attempted to depart from a non-occupied spot");
        }
        mIsOccupied = false;
        mOccupant = null;
        BigDecimal interval = new BigDecimal(departureTime - mArrivalTime);
        return mHourlyRate.multiply(interval).divide(new BigDecimal(60), RoundingMode.HALF_UP);
    }

}
