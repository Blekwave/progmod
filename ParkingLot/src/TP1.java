/**
 * Main class for this assignment. Contains only main(), which deals with stdio and general top-level logic.
 */

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TP1
{
    public static void main(String[] args)
    {
        System.out.println("I guess the program works, let's try reading some stuff.");
        Scanner input = new Scanner(System.in);
        String eventTypeRegex = "(?<type>[ES])";
        String timeRegex = "(?<time>(?<hours>\\d{2}):(?<minutes>\\d{2}))";
        String licensePlateRegex = "(?<plate>[A-Z]{3}-\\d{4})";
        String vehicleTypeRegex = "(?<vehicle>(?:VP)|(?:VG)|(?:MT)|(?:NE))";
        Pattern event = Pattern.compile(
            eventTypeRegex + ";" + timeRegex + ";" + licensePlateRegex + ";" + vehicleTypeRegex
        );

        while (input.hasNext())
        {
            String s = input.next();
            System.out.println("I just read: " + s);
            Matcher m = event.matcher(s);
            m.matches();
            String plate = m.group("plate");
            String vehicle = m.group("vehicle");
            String eventType = m.group("type");
            String time = m.group("time");
            if (eventType.equals("E"))
            {
                System.out.println(plate + " (" + vehicle + ") entered the parking lot at " + time);
            }
            else
            {
                System.out.println(plate + " (" + vehicle + ") left the parking lot at " + time);
            }
        }
        System.out.println("Done here, folks.");
    }
}
