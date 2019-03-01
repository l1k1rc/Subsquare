/**
 * 
 */
package tests.grid;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import grid.Grid;
import used.Point;

/**
 * @author raphael
 *
 */
class GridTest {

	private static Grid gridT;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gridT = new Grid();
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
	}

	/**
	 * Test method for {@link grid.Grid#addObstacle(grid.Obstacle)}.
	 */
	@Test
	void testAddObstacle() {

	}


}
