package tests.engine;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import city.Citizen;
import city.City;
import engine.DistrictGrowth;
import mocks.DistrictGrowthMock;
import used.Point;

class DistrictGrowthTest {
	private static DistrictGrowthMock dgMockTest;
	private static DistrictGrowthMock dgMockExp;

	private static City cityTest;
	private static City cityExp;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dgMockTest = new DistrictGrowthMock(1);
		dgMockExp = new DistrictGrowthMock(2);
		cityTest = dgMockTest.generateCity();
	}

	@Test
	void testPopulationGrowth() {
		//Testing function
		DistrictGrowth.populationGrowthStatic(cityTest);
		
		//Expected values
		cityExp = dgMockExp.generateCity();
		int i;
		Point point;
		Citizen citizen;
		for(i=1; i<=9; i++) {
			point = new Point(i,i);
			citizen = new Citizen(cityExp.getDistrictByPosition(point), point);
			if ((i==1) || (i==4) || (i==7)) {
				cityExp.addCitizen(citizen);
				cityExp.addCitizen(citizen);
				assertEquals("Les quartiers non pleins ne sont pas au même niveau", cityExp.getDistrictByPosition(point).getLevel(), cityTest.getDistrictByPosition(point).getLevel());
				assertEquals("Les quartiers non pleins sont différents en nombre d'habitants", cityExp.getCitizensByDistrict(cityExp.getDistrictByPosition(point)).size(), cityTest.getCitizensByDistrict(cityTest.getDistrictByPosition(point)).size());
			} else if ((i==2) || (i==3)) {
				cityExp.getDistrictByPosition(point).setLevel(2);
				assertEquals("Les quartiers presque pleins ne sont pas au même niveau", cityExp.getDistrictByPosition(point).getLevel(), cityTest.getDistrictByPosition(point).getLevel());				
				cityExp.addCitizen(citizen);
				cityExp.addCitizen(citizen);
				assertEquals("Les quartiers presque pleins sont différents en nombre d'habitants", cityExp.getCitizensByDistrict(cityExp.getDistrictByPosition(point)).size(), cityTest.getCitizensByDistrict(cityTest.getDistrictByPosition(point)).size());
			} else if ((i==5) || (i==6)){
				cityExp.getDistrictByPosition(point).setLevel(3);
				assertEquals("Les quartiers pleins lvl 1 et 2 ne sont pas au même niveau", cityExp.getDistrictByPosition(point).getLevel(), cityTest.getDistrictByPosition(point).getLevel());				
				cityExp.addCitizen(citizen);
				cityExp.addCitizen(citizen);
				assertEquals("Les quartiers pleins lvl 1 et 2 sont différents en nombre d'habitants", cityExp.getCitizensByDistrict(cityExp.getDistrictByPosition(point)).size(), cityTest.getCitizensByDistrict(cityTest.getDistrictByPosition(point)).size());
			} else if ((i==8)){
				cityExp.addCitizen(citizen);
				assertEquals("Les quartiers presque pleins lvl 3 ne sont pas au même niveau", cityExp.getDistrictByPosition(point).getLevel(), cityTest.getDistrictByPosition(point).getLevel());				
				assertEquals("Les quartiers presque pleins lvl 3 sont différents en nombre d'habitants", cityExp.getCitizensByDistrict(cityExp.getDistrictByPosition(point)).size(), cityTest.getCitizensByDistrict(cityTest.getDistrictByPosition(point)).size());
			} else {
				//Nothing
				assertEquals("Les quartiers pleins lvl 3 ne sont pas au même niveau", cityExp.getDistrictByPosition(point).getLevel(), cityTest.getDistrictByPosition(point).getLevel());				
				assertEquals("Les quartiers pleins lvl 3 sont différents en nombre d'habitants", cityExp.getCitizensByDistrict(cityExp.getDistrictByPosition(point)).size(), cityTest.getCitizensByDistrict(cityTest.getDistrictByPosition(point)).size());
			}
		}
		//assertEquals("Le district niveau 1 aurait dû passer niveau 2", cityExp, cityTest);

	}
	
}
