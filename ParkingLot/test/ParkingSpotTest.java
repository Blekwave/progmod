import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Describe this class and the methods exposed by it.
 */
public class ParkingSpotTest {
    @Test
    public void occupyAndVacate() throws Exception {
        Vehicle v = new Vehicle("ABC-1234", "VG");
        ParkingSpot s = new ParkingSpot("MySpot", "VG", new BigDecimal("13.5"));
        assertFalse("already occupied before it should be", s.isOccupied());
        s.occupy(v, 20);
        assertTrue("not occupied after occupation", s.isOccupied());
        assertSame("occupant is not the provided vehicle", s.occupant(), v);
        BigDecimal price = s.vacate(160);
        BigDecimal expectedPrice = new BigDecimal("31.5");
        assertFalse("still occupied after vacancy", s.isOccupied());
        assertTrue("wrong price", price.compareTo(expectedPrice) == 0);
    }
}