package tests.engine;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import city.Station;
import engine.StationLevelUp;
import mocks.StationLevelUpMock;
import staticData.StationData;

class StationLevelUpTest {

	private static Station stationTest1;
	private static Station stationTest2;
	private static Station stationTest3;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		stationTest1 =StationLevelUpMock.StationLevel1();
		stationTest2 =StationLevelUpMock.StationLevel2();
		stationTest3 =StationLevelUpMock.StationLevel3();
	}

	@Test
	void testStationUpper() {
		//test
		StationLevelUp.StationUpper(stationTest1);
		//Exp
		Station stationExp1 =StationLevelUpMock.StationLevel1();
		stationExp1.setLevel(2);
		stationExp1.setMaxCapacity(StationData.maxInhabitantsCapacityLevel2);
		//Comp
		assertEquals("La station niveau 1 aurait dû passer niveau 2", stationExp1.getLevel(), stationTest1.getLevel());
		assertEquals("La station devrait être de capacité 2", stationExp1.getMaxCapacity(), stationTest1.getMaxCapacity());
	
		//test
		StationLevelUp.StationUpper(stationTest2);
		//Exp
		Station stationExp2 =StationLevelUpMock.StationLevel2();
		stationExp2.setLevel(3);
		stationExp2.setMaxCapacity(StationData.maxInhabitantsCapacityLevel3);
		//Comp
		assertEquals("La station niveau 2 aurait dû passer niveau 3", stationExp2.getLevel(), stationTest2.getLevel());
		assertEquals("La station devrait être de capacité 3", stationExp2.getMaxCapacity(), stationTest2.getMaxCapacity());

		//test
		StationLevelUp.StationUpper(stationTest3);
		//Exp
		Station stationExp3 = StationLevelUpMock.StationLevel3();
		//Comp
		assertEquals("La station niveau 3 aurait dû rester niveau 3", stationExp3.getLevel(), stationTest3.getLevel());
		assertEquals("La station devrait encore être de capacité 3", stationExp3.getMaxCapacity(), stationTest3.getMaxCapacity());

	}

}
