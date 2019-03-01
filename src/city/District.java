package city;

import staticData.districtData;
import used.Point;

public class District
{	
	private String name;
	DistrictType type;
	private Station station;
	private int density;
	private float prosperity;
	private double maintenanceCost;
	private int level;
	private Point position;
	private int maxCapacity;
	
	public District(String name, Station station, int density, float prosperity, double maintenanceCost, String type,int level, Point position) {
		this.name = name;
		this.station = station;
		this.density = density;
		this.prosperity = prosperity;
		this.maintenanceCost = maintenanceCost;
		this.level= level;
		this.position = position;
		this.determineMaxCapacity();
	}
	
	public District(String name, Station station, int density, float prosperity, double maintenanceCost, String type, Point position) {
		this(name,station,density, prosperity,maintenanceCost,type,1, position);
	}
	
	public District(Point position,DistrictType type) {
		this.position = position;
		this.type = type;
		density = 0;
		prosperity = 0;
		maintenanceCost = 0;
		level = 1;
		station = null;
		determineMaxCapacity();
	}
	
	public void determineMaxCapacity() {
		switch (this.getLevel()) {
		case 1 : this.setMaxCapacity(districtData.maxInhabitantsCapacityLevel1);
			break;
		case 2 : this.setMaxCapacity(districtData.maxInhabitantsCapacityLevel2);
			break;
		case 3 : this.setMaxCapacity(districtData.maxInhabitantsCapacityLevel3);
			break;
		}
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Station getStation() {
		return station;
	}
	
	public void setStation(Station station) {
		this.station = station;
	}
	
	public int getDensity() {
		return density;
	}
	
	public void setDensity(int density) {
		this.density = density;
	}
	
	public float getProsperity() {
		return prosperity;
	}
	
	public void setProsperity(float prosperity) {
		this.prosperity = prosperity;
	}
	
	public double getMaintenanceCost() {
		return maintenanceCost;
	}
	
	public void setMaintenanceCost(double maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}

	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	public DistrictType getType() {
		return type;
	}

	public void setType(DistrictType type) {
		this.type = type;
	}
	
}