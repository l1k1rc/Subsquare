package city;

import used.Direction;
import used.Point;

public class Citizen {
	private District workDistrict;
	private District originDistrict;
	private Direction lastDirection;
	private Point position;
	private boolean employed;
	
	public Citizen(District workDistrict, District originDistrict, Point position) {
		this.workDistrict = workDistrict;
		this.originDistrict = originDistrict;
		lastDirection=Direction.randomDirection();
		this.position = position;
		employed = true;
	}
	
	public Citizen(District originDistrict, Point position) {
		this.originDistrict = originDistrict;
		this.position = position;
		lastDirection = Direction.randomDirection();
		employed = false;
	}

	public District getWorkDistrict() {
		return workDistrict;
	}

	public void setWorkDistrict(District workDistrict) {
		this.workDistrict = workDistrict;
	}

	public District getOriginDistrict() {
		return originDistrict;
	}

	public void setOriginDistrict(District originDistrict) {
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
	
	public boolean employed() {
		return employed;
	}
}
