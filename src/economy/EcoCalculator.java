package economy;

import java.util.ArrayList;

import city.Citizen;
import city.City;
import city.District;
import city.Station;

/**
 * Operations about economic data
 * 
 * @author MOEs, QA
 *
 */
public class EcoCalculator {

	public static float calcStationOverload(City city, District district) {
		Station targetStation = district.getStation();
		int result;
		if (district.getStation() != null) {
			int userStationCompt = 0;
			ArrayList<Citizen> citizens = city.getCitizens();
			for (Citizen citizen : citizens) {
				if (citizen.getClosestStation() != null) {
					if (citizen.getClosestStation().getId() == district.getStation().getId())
						userStationCompt++;
				}
			}
			result = targetStation.getMaxCapacity() - userStationCompt;
			if (result < 20) {
				return 20;
			} else if (result < 0) {
				return result * (-1);
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static float calcMaintenanceCost(District dist) {
		float mc = dist.getLevel() * EcoData.MAINT_COST;
		dist.getType().setMaintenanceCost(mc);
		return mc;
	}

	public static float calcTaxes(District dist) {
		float tx = dist.getLevel();

		if (dist.getType().isResidential()) {
			tx *= EcoData.TAX_RES * dist.getType().getNbCitizens();
		}
		if (dist.getType().isPrivate()) {
			tx *= EcoData.TAX_PRV;
		}

		dist.getType().setTaxes(tx);

		return tx;
	}

	public static float calcUnemployement(ArrayList<Citizen> citizens) {
		if (citizens.isEmpty())
			return 0;
		int unempl = 0;
		for (Citizen c : citizens) {
			if (!c.isEmployed()) {
				unempl++;
			}
		}
		return unempl / citizens.size();
	}

	public static float calcDistUnemployement(City city, District district) {
		ArrayList<Citizen> citizens = city.getCitizensByDistrict(district);
		float result = calcUnemployement(citizens);
		district.setUnemployement(result);
		return result;
	}

	public static double calcTravelTime(City city, District district) {
		ArrayList<Citizen> citizens = city.getCitizensByDistrict(district);
		double traveling = 0d;
		double result = 0d;

		for (Citizen citizen : citizens)
			traveling += (citizen.getTravelFoot() + citizen.getTravelSubWay());

		if (citizens.size() != 0)
			result = traveling / citizens.size();

		return result;
	}

	public static int calcProsperity(City city, District district) {
		float unemployementRate = 1 - calcDistUnemployement(city, district);
		float travelTime = (float) (100 - (calcTravelTime(city, district) * 2.127));
		float stationOverloadRate = calcStationOverload(city, district);

		float gain = district.getType().getTaxes() - district.getType().getMaintenanceCost();
		float prosperity = (float) (unemployementRate * ((travelTime) - (stationOverloadRate))) + gain;
		
		if (prosperity > 100) {
			prosperity = 100;
		} else if (prosperity < 0) {
			prosperity = 0;
		}

		district.setProsperity(prosperity);
		return (int) prosperity;
	}

	public static String prosperityInterpretor(int prosperity) {
		String interpretor;
		if (prosperity <= 25) {
			interpretor = "Critical";
		} else if (prosperity > 25 && prosperity <= 43) {
			interpretor = "Bad";
		} else if (prosperity > 43 && prosperity <= 57) {
			interpretor = "In average";
		} else if (prosperity > 57 && prosperity <= 75) {
			interpretor = "Good";
		} else {
			interpretor = "Excelent";
		}

		return interpretor;
	}

}
