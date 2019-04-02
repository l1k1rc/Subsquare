/*package tests.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import city.CityFactory;
import city.District;
import city.ResidentialDistrict;
import city.Station;
import engine.GridParameters;
import engine.Simulation;
import engine.StationLevelUp;
import grid.Box;
import mocks.StationLevelUpMock;
import staticData.StationData;
import used.Point;

class SimulationTest {
	
	private static ResidentialDistrict rdTest;
	private static Point point;
	private static Simulation simulationTest;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		rdTest = new ResidentialDistrict();
		point = new Point(1,2);
		simulationTest = new Simulation(GridParameters.getInstance());
	}

	@Test
	void testBuildDistrict() {
		/***** Position is not free ******
		//test
		Box boxTest = simulationTest.getGrid().getBoxAt(point.getOrdonne(),point.getAbscisse());
		boxTest.setIsFree(false);
		simulationTest.buildDistrict(point, rdTest);
		//Exp
		ResidentialDistrict rdExp = new ResidentialDistrict();
		Simulation simulationExp = new Simulation(GridParameters.getInstance());
		Box boxExp = simulationExp.getGrid().getBoxAt(point.getOrdonne(),point.getAbscisse());
		boxExp.setIsFree(false);
		//Comp
		assertEquals("Rien n'aurait dû se passer", boxExp.getClass(), boxTest.getClass());
		
		/***** Position is free & not trees & District not Public ******
		//Test
		boxTest = simulationTest.getGrid().getBoxAt(point.getOrdonne(),point.getAbscisse());
		boxTest.setIsFree(true);
		simulationTest.buildDistrict(point, rdTest);
		//Exp
		rdExp = new ResidentialDistrict();
		simulationExp = new Simulation(GridParameters.getInstance());
		boxExp = simulationExp.getGrid().getBoxAt(point.getOrdonne(),point.getAbscisse());
		boxExp.setIsFree(true);
		
		District dsExp = CityFactory.creatDistrict(point, rdExp);
		simulationExp.getCity().addDistrict(point,dsExp);
		//Comp
		assertEquals("Il y aurait dû avoir un ajout de district à cette position", simulationExp.getClass(), simulationTest.getClass());
	}

	@Test
	void testBuildStation() {
		
	}

	@Test
	void testBuildSubwayLine() {
	}

}*/
