package city;

import java.util.ArrayList;

import used.Point;

public class DistrictProsperityCalculator {
	
	public static float unemployementCalc(City city, District district) {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		citizens = city.getCitizensByDistrict(district);
		int nbUnemployed = 0;
		for (Citizen i : citizens) {
			 if(!i.isEmployed()) {
				 nbUnemployed++;
			 }
		}
		
		//Calc
		float unemployementRate = nbUnemployed/citizens.size();
		return unemployementRate;
	}
	
	
	/*public static float travelTimeCalc(City city, District district) {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		citizens = city.getCitizensByDistrict(district);
		int travelTime = 0;
		
		for (Citizen i : citizens) {
			 if(i.isEmployed()) {
				 Point workingDistrictPosition = i.getWorkDistrict().getPosition();
				 Point originDistrictPosition = i.getOriginDistrict().getPosition();
				 double distance = Math.sqrt(sqr(workingDistrictPosition.getOrdonne() - originDistrictPosition.getOrdonne()) + sqr(workingDistrictPosition.getAbscisse() - originDistrictPosition.getAbscisse()));
				 travelTime+= distance;
			 }
		}
		
		
		return 0;
	}*/

}
