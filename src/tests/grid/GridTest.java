/**
 * 
 */
package tests.grid;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import grid.Grid;
import grid.Obstacle;
import used.Point;

/**
 * @author raphael
 *
 */
class GridTest {

	private static Grid gridT;
	private static Obstacle obsT;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gridT = new Grid();
		obsT = new Obstacle(1,1);
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		gridT = new Grid();
	}

	/**
	 * Test method for {@link grid.Grid#prefDistanceObstacle(used.Point)}.
	 */
	@Test
	void testPrefDistanceObstacle() {
		Point pT = new Point(2,2);
		assertTrue(gridT.prefDistanceObstacle(pT));
		
		gridT.addObstacle(obsT);
		assertFalse(gridT.prefDistanceObstacle(pT));
	}

	/**
	 * Test method for {@link grid.Grid#addObstacle(grid.Obstacle)}.
	 */
	@Test
	void testAddObstacle() {
		ArrayList<Obstacle> arrayObstacle= new ArrayList<Obstacle>();
		gridT.addObstacle(obsT);
		
		arrayObstacle.add(obsT);
		assertEquals("Aucun obstacle n'a été placé", arrayObstacle, gridT.getObstacle());
		
		gridT.addObstacle(obsT);
		assertEquals("Placement d'un obstacle alors qu'il y est déjà", arrayObstacle, gridT.getObstacle());
	}


}
