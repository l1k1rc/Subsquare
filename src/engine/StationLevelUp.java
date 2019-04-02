package engine;

import java.util.ArrayList;

import city.Citizen;
import city.City;
import city.Station;

/**
 * This class increase of 1 the level of a district 
 * @author raphael
 *
 */
public class StationLevelUp {
	
	public static Station stationUpper(Station stationUp) {
		int levelUp = stationUp.getLevel();
		if (levelUp < 3) {
			levelUp++;
			stationUp.setLevel(levelUp);
			//Increase maximum inhabitants capacity in station
			stationUp.determineMaxCapacity();

		}
		return stationUp;
		
	}
	
	/*public void automatedLevelUpper(City city, Station district) {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		citizens = city.getCitizensByStation(station);
		if (station.getMaxCapacity() <= citizens.size()) {
			stationUpper(station);
		}
	}*/

}