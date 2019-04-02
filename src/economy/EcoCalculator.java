package economy;

import java.util.ArrayList;

import city.Citizen;
import city.City;
import city.District;
import city.Station;
import engine.Simulation;
import used.Point;

public class EcoCalculator
{	
	
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
	
	
	public static float calcDistUnemployement(City city, District district) {
		ArrayList<Citizen> citizens = city.getCitizensByDistrict(district);
		float result = calcUnemployement(citizens);
		district.setUnemployement(result);
		return result;
	}
	
	static public double sqr(double a) {
	        return a*a;
	}
	
	public static float calcTravelTime(City city, District district) {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		citizens = city.getCitizensByDistrict(district);
		int totalTravelTime = 0;
		int travelTime;
		for (Citizen i : citizens) {
			travelTime = 0;
			if(i.isEmployed()) {
				if(!i.getPosition().equals(i.getWorkDistrict().getPosition())) {
					int begin = city.getIdByPosition(i.getOriginDistrict().getPosition());
					int end = city.getIdByPosition(i.getWorkDistrict().getPosition());
					ArrayList<Point> path = Simulation.getStationsPosByFloyd(begin, end);
					Point actualPos = i.getPosition();
					for (Point target : path) {
						double distance = Math.sqrt(sqr(actualPos.getOrdonne() - target.getOrdonne()) + sqr(actualPos.getAbscisse() - target.getAbscisse()));
						if(distance<0) {
							distance*= (-1);
						}
						actualPos = target;
						travelTime+=distance;
					}
				}
			}
			totalTravelTime+=travelTime;
		}
		
		float moyTravelTime = totalTravelTime/citizens.size();
		return moyTravelTime;
	}
	
	
	public static int calcProsperity(City city, District district) {
		float unemployementRate = 1-calcDistUnemployement(city, district);
		float travelTime = (float) (100-(calcTravelTime(city, district)*2.127));
		float stationOverloadRate = (calcStationOverload(district.getStation())*20);
		
		float prosperity = (float) (unemployementRate*((travelTime)-(stationOverloadRate)));
		
		if (prosperity > 100) {
			prosperity = 100;
		} else if (prosperity < 0){
			prosperity = 0;
		}
		
		district.setProsperity(prosperity);
		return (int) prosperity;
	}
	
	public static String prosperityInterpretor(int prosperity) {
		String interpretor;
		/*if (prosperity)
			
		}	*/
		
		return "";
	}
	
}
