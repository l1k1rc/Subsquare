package city;

import used.Point;

public class Citizen {
	private String workDistrict;
	private String originDistrict;
	private Point position;
	
	public Citizen(String workDistrict, String originDistrict, Point position) {
		this.workDistrict = workDistrict;
		this.originDistrict = originDistrict;
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
}
