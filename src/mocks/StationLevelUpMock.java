package mocks;

import city.Station;
import used.Random;

/**
 * @author raphael
 *
 */
public class StationLevelUpMock {

	public static Station StationLevel1() {
		Station station = new Station(1, Random.randomInt(8, false));
		return station;
	}
	
	public static Station StationLevel2() {
		Station station = new Station(2, Random.randomInt(8, false));
		return station;
	}
	
	public static Station StationLevel3() {
		Station station = new Station(3, Random.randomInt(8, false));
		return station;
	}
	
}
