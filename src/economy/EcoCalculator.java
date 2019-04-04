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
	
	public static float calcStationOverload(City city, District district) {
		Station targetStation = district.getStation();
		int result;
		if(district.getStation() != null) {
			int userStationCompt = 0;
			ArrayList<Citizen> citizens = city.getCitizens();
			for (Citizen citizen : citizens) {
				if (citizen.getClosestStation() == district.getStation()) userStationCompt++;
			}
			result = targetStation.getMaxCapacity()-userStationCompt;
			if (result<20) {
				return 20;
			} else if (result<0) {
				return result*(-1);
			} else {
				return 0;
			}
		} else {
			return 0;
		}
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

		if(citizens.size()==0) return 0;
		
		Point actualPos;
		int totalTravelTime = 0;
		int travelTimeSubway, travelTimeFoot;
		int beginWork, endWork;
		Point beginWalk, endWalk;
		double distanceSubway, distanceWalk;
		for (Citizen i : citizens) {
			travelTimeSubway = 0;
			travelTimeFoot = 0;
			if(i.isEmployed()) {
				if(!i.getPosition().equals(i.getWorkDistrict().getPosition())) {
					if((district.getStation() == null) || (i.getClosestStation() != district.getStation())) {
						beginWalk = i.getOriginDistrict().getPosition();
						endWalk =  i.getClosestStation().getStationPos();
						
						distanceWalk = Math.sqrt(sqr(beginWalk.getOrdonne() - endWalk.getOrdonne()) + sqr(beginWalk.getAbscisse() - endWalk.getAbscisse()));
						if(distanceWalk<0) {
							distanceWalk*= (-1);
						}
						travelTimeFoot+=(distanceWalk*EcoData.travelOnFoot_Cost);
					}
					
					
					
					beginWork = city.getIdByPosition(i.getOriginDistrict().getPosition());
					endWork = city.getIdByPosition(i.getWorkDistrict().getPosition());
					ArrayList<Point> path = Simulation.getStationsPosByFloyd(beginWork, endWork);
					actualPos = i.getPosition();
					for (Point target : path) {
						distanceSubway = Math.sqrt(sqr(actualPos.getOrdonne() - target.getOrdonne()) + sqr(actualPos.getAbscisse() - target.getAbscisse()));
						if(distanceSubway<0) {
							distanceSubway*= (-1);
						}
						actualPos = target;
						travelTimeSubway+=(distanceSubway*EcoData.travelInTrain_Cost);
					}
				}
			}
			totalTravelTime+= (travelTimeSubway + travelTimeFoot);
		}
		
		float moyTravelTime = totalTravelTime/citizens.size();
		return moyTravelTime;
	}
	
	
	public static int calcProsperity(City city, District district) {
		float unemployementRate = 1-calcDistUnemployement(city, district);
		float travelTime = (float) (100-(calcTravelTime(city, district)*2.127));
		float stationOverloadRate = calcStationOverload(city,district);
		
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
