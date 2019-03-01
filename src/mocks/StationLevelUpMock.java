package mocks;

import city.Station;

/**
 * @author raphael
 *
 */
public class StationLevelUpMock {

	public static Station StationLevel1() {
		Station station = new Station(1);
		return station;
	}
	
	public static Station StationLevel2() {
		Station station = new Station(2);
		return station;
	}
	
	public static Station StationLevel3() {
		Station station = new Station(3);
		return station;
	}
	
}
