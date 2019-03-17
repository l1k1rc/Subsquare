package city;

import used.Direction;
import used.Point;

public class Citizen {
	private String workDistrict;
	private String originDistrict;
	private Direction lastDirection;
	private Point position;
	
	public Citizen(String workDistrict, String originDistrict, Point position) {
		this.workDistrict = workDistrict;
		this.originDistrict = originDistrict;
		lastDirection=Direction.randomDirection();
		this.position = position;
	}

	public String getWorkDistrict() {
		return workDistrict;
	}

	public void setWorkDistrict(String workDistrict) {
		this.workDistrict = workDistrict;
	}

	public String getOriginDistrict() {
		return originDistrict;
	}

	public void setOriginDistrict(String originDistrict) {
		this.originDistrict = originDistrict;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Direction getLastDirection() {
		return lastDirection;
	}
	
	public void setLastDirection(Direction lastDirection) {
		this.lastDirection=lastDirection;
	}
}
