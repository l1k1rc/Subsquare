package city;

import engine.Simulation;
import staticData.DistrictData;
import used.Point;

public class District
{	
	private DistrictType type;

	private String name;
	private Point position;
	private Station station;
	private boolean hasStation;
	
	private float unemployement;
	private float prosperity;
	private int level;
	private int maxCapacity;

	/**
	 * Create a new district on the map.
	 * @see Simulation {@link Simulation}
	 * @param position = @see {@link Point}, district position in the matrix on the scene
	 * @param type = residential, private or public
	 * @param name = district name generates randomly
	 * @param level = level of district which defines the max capacity for the density
	 */
	public District(Point position,DistrictType type, String name,int level)
	{
		this.name = name;
		this.type = type;
		this.position = position;
		this.level= level;
		
		this.prosperity = 50;
		this.station = null;
		this.hasStation = false;
		
		determineMaxCapacity();
	}
	
	public void determineMaxCapacity() {
		switch (this.getLevel()) {
		case 1 : this.setMaxCapacity(DistrictData.maxInhabitantsCapacityLevel1);
			break;
		case 2 : this.setMaxCapacity(DistrictData.maxInhabitantsCapacityLevel2);
			break;
		case 3 : this.setMaxCapacity(DistrictData.maxInhabitantsCapacityLevel3);
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
		hasStation = true;
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
	
	public float getUnemployement() {
		return unemployement;
	}

	public void setUnemployement(float unemployement) {
		this.unemployement = unemployement;
	}

	
	
}