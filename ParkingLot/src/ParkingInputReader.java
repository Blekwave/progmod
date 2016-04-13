import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Class that reads parking input from an input stream.
 */
public class ParkingInputReader {
    private Scanner mScanner;
    private static Pattern sEventRegex = compileEventRegex();

    /**
     * Compiles the regular expression for parsing parking/departure events.
     *
     * @return The compiled pattern.
     */
    private static Pattern compileEventRegex(){
        String eventTypeRegex = "(?<type>[ES])";
        String timeRegex = "(?<time>(?<hours>\\d{2}):(?<minutes>\\d{2}))";
        String licensePlateRegex = "(?<plate>[A-Z]{3}-\\d{4})";
        String vehicleTypeRegex = "(?<vehicle>(?:VP)|(?:VG)|(?:MT)|(?:NE))";
        return Pattern.compile(
            eventTypeRegex + ";" + timeRegex + ";" + licensePlateRegex + ";" + vehicleTypeRegex
        );
    }

    public ParkingInputReader(InputStream stream){
        mScanner = new Scanner(stream);
    }

    public Boolean hasNext(){
        return mScanner.hasNext();
    }

    public ParkingEvent next(){
        String s = mScanner.next();
        Matcher m = sEventRegex.matcher(s);
        m.matches();

        String plate = m.group("plate");
        String vehicleType = m.group("vehicle");
        String eventType = m.group("type");
        String hours = m.group("hours");
        String minutes = m.group("minutes");
        Integer time = Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);

        return new ParkingEvent(plate, vehicleType, eventType, time);
    }
}
