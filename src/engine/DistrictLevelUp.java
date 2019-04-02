/**
 * 
 */
package engine;

import java.util.ArrayList;

import city.Citizen;
import city.City;
import city.District;

/**
 * This class increase of 1 the level of a district 
 * @author raphael
 *
 */
public class DistrictLevelUp {
	
	public static District districtUpper(District districtUp) {
		int levelUp = districtUp.getLevel();
		if (levelUp < 3) {
			levelUp++;
			districtUp.setLevel(levelUp);
			//Increase maximum inhabitants capacity in district
			districtUp.determineMaxCapacity();
		}
		return districtUp;
		
	}
	
	public void automatedLevelUpper(City city, District district) {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		citizens = city.getCitizensByDistrict(district);
		if (district.getMaxCapacity() <= citizens.size()) {
			districtUpper(district);
		}
	}

}
