package engine;

import city.Station;

/**
 * This class increase of 1 the level of a district 
 * @author raphael
 *
 */
public class StationLevelUp {
	
	public static Station StationUpper(Station stationUp) {
		int levelUp = stationUp.getLevel();
		if (levelUp < 3) {
			levelUp++;
			stationUp.setLevel(levelUp);
			//Increase maximum inhabitants capacity in station
			stationUp.determineMaxCapacity();

		}
		return stationUp;
		
	}

}