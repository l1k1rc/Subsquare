package tests.engine;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import city.District;
import engine.DistrictLevelUp;
import mocks.DistrictLevelUpMock;
import staticData.districtData;

class DistrictLevelUpTest {
	
	private static DistrictLevelUpMock lduMock;
	private static District districtTest1;
	private static District districtTest2;
	private static District districtTest3;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		lduMock = new DistrictLevelUpMock();
		districtTest1 = lduMock.DistrictLevel1();
		districtTest2 = lduMock.DistrictLevel2();
		districtTest3 = lduMock.DistrictLevel3();
	}

	@Test
	void testDistrictUpper() {
		//test
		DistrictLevelUp.DistrictUpper(districtTest1);
		//Exp
		District districtExp1 = lduMock.DistrictLevel1();
		districtExp1.setLevel(2);
		districtExp1.setMaxCapacity(districtData.maxInhabitantsCapacityLevel2);
		//Comp
		assertEquals("Le district niveau 1 aurait dû passer niveau 2", districtExp1.getLevel(), districtTest1.getLevel());
		assertEquals("Le district devrait être de capacité 2", districtExp1.getMaxCapacity(), districtTest1.getMaxCapacity());
		
		//test
		DistrictLevelUp.DistrictUpper(districtTest2);
		//Exp
		District districtExp2 = lduMock.DistrictLevel2();
		districtExp2.setLevel(3);
		districtExp2.setMaxCapacity(districtData.maxInhabitantsCapacityLevel3);
		//Comp
		assertEquals("Le district niveau 2 aurait dû passer niveau 3", districtExp2.getLevel(), districtTest2.getLevel());
		assertEquals("Le district devrait être de capacité 3", districtExp2.getMaxCapacity(), districtTest2.getMaxCapacity());

		//test
		DistrictLevelUp.DistrictUpper(districtTest3);
		//Exp
		District districtExp3 = lduMock.DistrictLevel3();
		//Comp
		assertEquals("Le district niveau 3 aurait dû rester niveau 3", districtExp3.getLevel(), districtTest3.getLevel());
		assertEquals("Le district devrait encore être de capacité 3", districtExp3.getMaxCapacity(), districtTest3.getMaxCapacity());

	}

}