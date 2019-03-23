package economy;

import java.util.ArrayList;

import city.Citizen;
import city.City;
import city.District;
import city.Station;

public class EcoCalculator
{	
	public static float calcProsperity(District dist) {
		return 0;
	}
	
	public static float calcStationOverload(Station station) {
		return 0;
	}
	
	public static float calcMaintenanceCost(District dist) {
		float mc = dist.getLevel()*EcoData.MAINT_COST;
		dist.getType().setMaintenanceCost(mc);
		return mc;
	}
	
	public static float calcTaxes(District dist){
		float tx = dist.getLevel();
		
		if(dist.getType().isResidential()) {
			tx *= EcoData.TAX_RES*dist.getType().getNbCitizens();
		}
		if(dist.getType().isPrivate()) {
			tx *= EcoData.TAX_PRV;
		}
		
		dist.getType().setTaxes(tx);
		
		return tx;
	}
	
	public static float calcDistUnemployement(City city, District district) {
		ArrayList<Citizen> citizens = city.getCitizensByDistrict(district);
		float result = calcUnemployement(citizens);
		district.setUnemployement(result);
		return result;
	}
	
	public static float calcUnemployement(ArrayList<Citizen> citizens) {
		if(citizens.isEmpty()) return 0;
		int unempl = 0;
		for (Citizen c : citizens) {
			 if(!c.isEmployed()) {
				 unempl++;
			 }
		}
		return unempl/citizens.size();
	}
	
	/*public static float calcTravelTime(City city, District district) {
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
