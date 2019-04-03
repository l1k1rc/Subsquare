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
	
	public static boolean districtUpper(District districtUp) {
		int levelUp = districtUp.getLevel();
		if (levelUp < 3) {
			levelUp++;
			districtUp.setLevel(levelUp);
			//Increase maximum inhabitants capacity in district
			districtUp.determineMaxCapacity();
			
			return true;
		}
		return false;
		
	}
	
	public static String automatedLevelUpper(City city, District district, int increaseNbr) {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		citizens = city.getCitizensByDistrict(district);
		if (district.getMaxCapacity() <= (citizens.size()+increaseNbr)) {
			boolean upOk = districtUpper(district);
			if(upOk) {
				return "levelup";
			} else {
				return "levelmax";
			}
		}
		return "notmaxcap";
	}

}
