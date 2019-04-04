package mocks;

import city.Citizen;
import city.City;
import city.District;
import used.Point;

public class DistrictGrowthMock {
	private City city;

	private Point point;
	private DistrictLevelUpMock dlum;
	private District district;
	private Citizen citizen;

	public DistrictGrowthMock(int nbr) {
		if (nbr == 1) city = City.getInstance();
		if (nbr == 2) city = City.getInstanceTest();

		dlum = new DistrictLevelUpMock();
	}
	
	public City generateCity() {
		int i, j;
		//Generation of 9 districts with 3 different levels
		for(i=1; i<=9; i++) {
			//new point
			point = new Point(i,i);
			//Update point in district generation
			dlum.setPoint(point);
			//New citizen
			citizen = new Citizen(district,point);
			//Generation 3 districts lvl 1
			if(i<=3) {
				//New district level 1
				district = dlum.DistrictLevel1();
				//Generation 10 citizens in district lvl 1
				if(i==1) {
					for(j=1; j<=10; j++) {
						//New citizens
						city.addCitizen(citizen);
					}
				//Generation 19 (Just before max limit) citizens in district lvl 1
				} else if(i==2){
					for(j=1; j<=19; j++) {
						//New citizens
						city.addCitizen(citizen);
					}
				//Generation 20 (max limit) citizens in district lvl 1
				} else {
					for(j=1; j<=20; j++) {
						//New citizens
						city.addCitizen(citizen);
					}
				}
			//Generation 3 districts lvl 2
			} else if (i>3 && i<=6) {
				district = dlum.DistrictLevel2();
				//Generation 25 citizens in district lvl 2
				if(i==4) {
					for(j=1; j<=25; j++) {
						city.addCitizen(citizen);
					}
				//Generation 49 (Just before max limit) citizens in district lvl 2
				} else if(i==5){
					for(j=1; j<=49; j++) {
						city.addCitizen(citizen);
					}
				//Generation 50 (max limit) citizens in district lvl 2
				} else {
					for(j=1; j<=50; j++) {
						city.addCitizen(citizen);
					}
				}
			//Generation 3 districts lvl 3
			} else {
				district = dlum.DistrictLevel3();
				//Generation 75 citizens in district lvl 3
				if(i==7) {
					for(j=1; j<=75; j++) {
						city.addCitizen(citizen);
					}
				//Generation 99 (Just before max limit) citizens in district lvl 3
				} else if(i==8){
					for(j=1; j<=99; j++) {
						city.addCitizen(citizen);
					}
				//Generation 100 (max limit) citizens in district lvl 3
				} else {
					for(j=1; j<=100; j++) {
						city.addCitizen(citizen);
					}
				}
			}
			
			//Adding district to city
			city.addDistrict(point, district);
			
		}
		
		return city;
	}
	
	
}
