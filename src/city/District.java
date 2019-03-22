package city;

import java.util.ArrayList;

import engine.Simulation;
import staticData.districtData;
import used.Point;

public class District
{	
	private String name;
	private DistrictType type;
	private Point position;
	private Station station;
	private boolean hasStation;
	
	private int density;
	private float prosperity;
	private int level;
	private int maxCapacity;
	private District nearestPublicDistrict;

	
	public District(String name, Station station, int density, float prosperity
			, double maintenanceCost, String type,int level, Point position) {
		this.name = name;
		this.station = station;
		this.density = density;
		this.prosperity = prosperity;
		this.level= level;
		this.position = position;
		this.hasStation = false;
		this.determineMaxCapacity();
	}
	
	public District(String name, Station station, int density, float prosperity, double maintenanceCost, String type, Point position, int id) {
		this(name,station,density, prosperity,maintenanceCost,type,1, position);
	}
	
	public District(Point position,DistrictType type, String name) {
		this.position = position;
		this.type = type;
		density = 0;
		prosperity = 0;
		level = 2;
		station = null;
		hasStation = false;
		this.name = name;
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
	
	public void defineNearestPublicDistrict(City city) {
		ArrayList<District> publicDistrictsList = city.getDistrictByType("pub");
		District nearestPub = Simulation.getClosestDistrict(this.getPosition(), publicDistrictsList);
		this.setNearestPublicDistrict(nearestPub);
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
		hasStation = true;
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
	
	public boolean hasStation() {
		return hasStation;
	}

	public District getNearestPublicDistrict() {
		return nearestPublicDistrict;
	}

	public void setNearestPublicDistrict(District nearestPublicDistrict) {
		this.nearestPublicDistrict = nearestPublicDistrict;
	}
	
	
}