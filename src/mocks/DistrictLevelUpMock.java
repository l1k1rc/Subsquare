package mocks;

import city.District;
import city.DistrictType;
import city.PrivateDistrict;
import city.Station;
import used.Point;
import used.Random;

/**
 * @author raphael
 *
 */
public class DistrictLevelUpMock {
	
	private Station station;
	private Point point;
	

	public DistrictLevelUpMock() {
		point = new Point(1,1);
		station = new Station(1, Random.randomInt(8, false), point);
	}

	public District DistrictLevel1() {
		District district = new District(point,new PrivateDistrict(),"DistrictTest1",1);
		return district;
	}
	
	public District DistrictLevel2() {
		District district = new District(point,new PrivateDistrict(),"DistrictTest2",2);
		return district;
	}
	
	public District DistrictLevel3() {
		District district = new District(point,new PrivateDistrict(),"DistrictTest3",3);
		return district;
	}

	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
