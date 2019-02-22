package tests.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import city.Citizen;
import used.Point;
/**
 * Training testU for Citizen
 * @author Raphaël
 *
 */
class CitizenTest {
	private static Point pos;
	private static Citizen ctz;
	private static String expectedStr;
	private static Point expectedPos;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		pos = new Point(5,6);
		ctz = new Citizen("Private", "Home", pos);
		
	}

	@Test
	void testCitizen() {
		ctz = new Citizen("Private", "Home", pos);
		expectedStr = "Private";
		assertEquals(expectedStr, ctz.getWorkDistrict());
		
		expectedStr = "Home";
		assertEquals(expectedStr, ctz.getOriginDistrict());

		expectedPos = new Point(5,6);
		assertEquals(expectedPos, ctz.getPosition());

	}

	@Test
	void testSetWorkDistrict() {
		ctz.setWorkDistrict("Public");
		expectedStr = "Public";
		assertEquals(expectedStr, ctz.getWorkDistrict());
		
	}

	@Test
	void testSetOriginDistrict() {
		ctz.setOriginDistrict("Bridge");
		expectedStr = "Bridge";
		assertEquals(expectedStr, ctz.getOriginDistrict());
	}

	@Test
	void testSetPosition() {
		Point newPos = new Point(10,11);
		ctz.setPosition(newPos);
		expectedPos = newPos;
		assertEquals(expectedPos, ctz.getPosition());
	}

}
