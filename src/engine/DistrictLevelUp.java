/**
 * 
 */
package engine;

import city.District;

/**
 * This class increase of 1 the level of a district 
 * @author raphael
 *
 */
public class DistrictLevelUp {
	
	public static District DistrictUpper(District districtUp) {
		int levelUp = districtUp.getLevel();
		if (levelUp < 3) {
			levelUp++;
			districtUp.setLevel(levelUp);
			//Increase maximum inhabitants capacity in district
			districtUp.determineMaxCapacity();
		}
		return districtUp;
		
	}

}
