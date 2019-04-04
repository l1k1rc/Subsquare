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
 * @author QA
 *
 */
public class DistrictLevelUp {
	
	public static boolean districtUpper(District districtUp) {
		int levelUp = districtUp.getLevel();
		if (levelUp < 3) {
			levelUp++;
			String path = "/images/City/";
			if(districtUp.getType().isPublic()) {
				path += "Public/"+levelUp+".png";
			}
			else if(districtUp.getType().isPrivate()){
				path += "Private/"+levelUp+".png";
			}
			else {
				path += "Residential/"+levelUp+".png";
			}
			districtUp.getType().setImage(path);
			districtUp.setLevel(levelUp);
			districtUp.determineMaxCapacity();
			
			return true;
		}
		return false;
		
	}
	
	public static String automatedLevelUpper(City city, District district, int increaseNbr) {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		citizens = city.getCitizensByDistrict(district);
		if ((citizens.size()+increaseNbr) > district.getMaxCapacity()) {
			boolean upOk = districtUpper(district);
			if(upOk) {
				return "levelup";
			} else {
				return "levelmax";
			}
		}
		return "notmaxcap";
	}
	
	public static void automatedLevelUpperPublicPrivate(City city) {
		for (District district : city.getDistricts().values()) {
			if(!district.getType().isResidential()) {
				if (district.getType().getNbWorkers() > district.getMaxCapacity()) {
					districtUpper(district);
				}
			}
		}
	}

}
