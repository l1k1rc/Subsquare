package engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import city.Citizen;
import city.City;
import city.District;
import used.Point;

/**
 * @author raphael
 *
 */
public class DistrictGrowth {
	
	/*public static void districtGrowth(District district) {
		this.district = district;
	}*/
	
	public static void populationGrowth(City city) {
		HashMap<Point,District> districts = city.getDistricts();
		Iterator<Entry<Point, District>> iterator = districts.entrySet().iterator();
		//
		String resultDLU;
		int randNbr, j;
		Citizen citizen;
		//
	    while (iterator.hasNext()) {
	    	@SuppressWarnings("rawtypes")
			Map.Entry mapentry = (Map.Entry) iterator.next();
	        District dist =  (District) mapentry.getValue();
	        randNbr =  used.Random.randomInt(1,7);        	
	        if (randNbr == 2) {
        		for(j=0;j<2;j++) {
			        resultDLU = DistrictLevelUp.automatedLevelUpper(city, dist, 1);
			        if(resultDLU == "levelmax") {
			        	//Nothing, cap max reached
			        } else {
			        	citizen = new Citizen(dist, (Point) mapentry.getKey());
			        	city.addCitizen(citizen);
			        }
        		}
        	} else {
        		resultDLU = DistrictLevelUp.automatedLevelUpper(city, dist, 1);
		        if(resultDLU == "levelmax") {
		        	//Nothing, cap max reached
		        } else {
		        	//Creation of 1 citizens
		        	if(randNbr == 1) {
		        		citizen = new Citizen(dist, (Point) mapentry.getKey());
		        		city.addCitizen(citizen);
		        	//Creation of 2 citizens
		        	} else {
		        		//Nothing
		        	}
		        }
        	}
	    } 
	}
	
	public static void populationGrowthStatic(City city) {
		HashMap<Point,District> districts = city.getDistricts();
		Iterator<Entry<Point, District>> iterator = districts.entrySet().iterator();
		//
		String resultDLU;
		int randNbr, j;
		Citizen citizen;
		//
	    while (iterator.hasNext()) {
	    	@SuppressWarnings("rawtypes")
			Map.Entry mapentry = (Map.Entry) iterator.next();
	        District dist =  (District) mapentry.getValue();
        	randNbr =  2;
        	if (randNbr == 2) {
        		for(j=0;j<2;j++) {
			        resultDLU = DistrictLevelUp.automatedLevelUpper(city, dist, 1);
			        if(resultDLU == "levelmax") {
			        	//Nothing, cap max reached
			        } else {
			        	citizen = new Citizen(dist, (Point) mapentry.getKey());
			        	city.addCitizen(citizen);
			        }
        		}
        	} else {
        		resultDLU = DistrictLevelUp.automatedLevelUpper(city, dist, 1);
		        if(resultDLU == "levelmax") {
		        	//Nothing, cap max reached
		        } else {
		        	//Creation of 1 citizens
		        	if(randNbr == 1) {
		        		citizen = new Citizen(dist, (Point) mapentry.getKey());
		        		city.addCitizen(citizen);
		        	//Creation of 2 citizens
		        	} else {
		        		//Nothing
		        	}
		        }
        	}
	    } 
	}
}
