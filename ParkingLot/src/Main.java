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
public class Main {
    public static void main(String[] args) throws IOException, ParkingException{
        FileInputStream inputStream = new FileInputStream("entrada.txt");
        PrintWriter output = new PrintWriter("saida.txt");

        ParkingInputReader input = new ParkingInputReader(inputStream);
        ParkingLot parkingLot = new ParkingLotBuilder("parkinglot.config").build();

        while(input.hasNext()) {
            ParkingEvent e = input.next();

            if(e.type().equals("E")) {
                output.println(parkingLot.park(e.vehicle(), e.time()));
            }
            else {
                output.println(parkingLot.depart(e.vehicle(), e.time()));
            }
        }

        output.close();
    }
}
