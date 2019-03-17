package city;

import java.util.ArrayList;

import staticData.StationData;

public class Station {
	private ArrayList<SubwayLine> subwayLines;
	private int level;
	private int maxCapacity;
	private int id;
	
	public Station(int level, int id) {
		this.level = level;
		this.id = id;
		subwayLines = new ArrayList<SubwayLine>();
		this.determineMaxCapacity();
	}
	
	public void determineMaxCapacity() {
		switch (this.getLevel()) {
		case 1 : this.setMaxCapacity(StationData.maxInhabitantsCapacityLevel1);
			break;
		case 2 : this.setMaxCapacity(StationData.maxInhabitantsCapacityLevel2);
			break;
		case 3 : this.setMaxCapacity(StationData.maxInhabitantsCapacityLevel3);
			break;
		}
	}
	
	public ArrayList<SubwayLine> getSubwayLines() {
		return subwayLines;
	}
	
	public void setSubwayLines(ArrayList<SubwayLine> subwayLines) {
		this.subwayLines = subwayLines;
	}
	
	public void addSubwayLine(SubwayLine line) {
		subwayLines.add(line);
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
