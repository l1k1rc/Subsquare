package mocks;

import city.District;
import city.Station;
import used.Point;

/**
 * @author raphael
 *
 */
public class DistrictLevelUpMock {
	
	private Station station;
	private Point point;
	
	public DistrictLevelUpMock() {
		station = new Station(1, "StationTest");
		point = new Point(1,1);
	}
	
	public District DistrictLevel1() {
		District district = new District("DistrictTest", station, 40, 50, 70, "Private", point);
		return district;
	}
	
	public District DistrictLevel2() {
		District district = new District("DistrictTest", station, 40, 50, 70, "Private", 2, point);
		return district;
	}
	
	public District DistrictLevel3() {
		District district = new District("DistrictTest", station, 40, 50, 70, "Private", 3, point);
		return district;
	}

}
