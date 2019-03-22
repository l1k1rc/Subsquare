package mocks;

import city.Station;
import used.Point;
import used.Random;

/**
 * @author raphael
 *
 */
public class StationLevelUpMock {

	public static Station StationLevel1() {
		Point point = new Point(1,1);
		Station station = new Station(1, Random.randomInt(8, false),point);
		return station;
	}
	
	public static Station StationLevel2() {
		Point point = new Point(1,1);
		Station station = new Station(2, Random.randomInt(8, false),point);
		return station;
	}
	
	public static Station StationLevel3() {
		Point point = new Point(1,1);
		Station station = new Station(3, Random.randomInt(8, false),point);
		return station;
	}
	
}
