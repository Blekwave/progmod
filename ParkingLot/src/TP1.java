/**
 * Main class for this assignment. Contains only main(), which deals with stdio and general top-level logic.
 */

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Main class for this assignment. Deals with IO, mostly.
 *
 * Exposed methods:
 * - main(String[] args)
 */
public class TP1
{
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

    public static Pattern sEventRegex = compileEventRegex();

    public static void main(String[] args) throws IOException, ParkingException
    {
        Scanner input = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLotBuilder("../parkinglot.config").build();

        while (input.hasNext())
        {
            String s = input.next();
            Matcher m = sEventRegex.matcher(s);
            m.matches();
            String plate = m.group("plate");
            String vehicleType = m.group("vehicle");
            String eventType = m.group("type");
            String hours = m.group("hours");
            String minutes = m.group("minutes");
            Integer time = Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);

            Vehicle v = new Vehicle(plate, vehicleType);

            if (eventType.equals("E")) {
                System.out.println(parkingLot.park(v, time));
            }
            else {
                System.out.println(parkingLot.depart(v, time));
            }
        }
    }
}
