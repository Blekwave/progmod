import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ParkingLot class and its main methods
 */
public class ParkingLotTest {

    private ParkingLotBuilder mBuilder;

    @Before
    public void setUp() throws Exception {
        mBuilder = new ParkingLotBuilder("parkinglot.config");
    }

    @Test
    public void parkSomeVehicles() throws Exception {
        ParkingLot lot = mBuilder.build();

        Vehicle vehicleA = new Vehicle("ABC1234", "NE");
        Vehicle vehicleB = new Vehicle("ROFL", "VG");
        Vehicle vehicleC = new Vehicle("DEF4321", "NE");

        String feedback = lot.park(vehicleA, 0);
        assertTrue("wrong feedback for vehicle A's arrival",
                feedback.equals("N1NE1"));
        feedback = lot.park(vehicleB, 10);
        assertTrue("wrong feedback for vehicle B's arrival",
                feedback.equals("N1VG1"));
        feedback = lot.park(vehicleC, 30);
        assertTrue("wrong feedback for vehicle C's arrival",
                feedback.equals("N1NE2"));

        feedback = lot.depart(vehicleA, 30);
        assertTrue("wrong feedback for vehicle A's departure",
                feedback.equals("NE;00:30;3,00"));

        feedback = lot.depart(vehicleC, 150);
        assertTrue("wrong feedback for vehicle C's departure",
            feedback.equals("NE;02:00;12,00"));

        feedback = lot.depart(vehicleB, 40);
        assertTrue("wrong feedback for vehicle B's departure",
            feedback.equals("VG;00:30;4,00"));
    }

    @Test
    public void fillLotWithSmallVehicles() throws Exception {
        ParkingLot lot = mBuilder.build();
        for (int level = 0; level < 4; level++) {
            for (int i = 0; i < 6; i++) {
                String plate = String.format("L%d-I%d", level, i);
                Vehicle v = new Vehicle(plate, "VP");
                assertFalse("lot is full before it should be",
                            lot.park(v, 0).equals("LOTADO"));
            }
        }

        Vehicle vp = new Vehicle("One more small vehicle", "VP");
        assertTrue("lot not full when it should be",
                    lot.park(vp, 0).equals("LOTADO"));

        Vehicle ne = new Vehicle("Special needs vehicle", "NE");
        assertFalse("lot should still be able to take in special needs vehicles",
                    lot.park(ne, 0).equals("LOTADO"));
    }
}