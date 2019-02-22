package city;

public class SubwayLine {
	private String name;
	private Station stationFrom;
	private Station stationEnd;
	
	public SubwayLine(String name, Station stationFrom, Station stationEnd) {
		this.name = name;
		this.stationFrom = stationFrom;
		this.stationEnd = stationEnd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Station getStationFrom() {
		return stationFrom;
	}
	
	public void setStationFrom(Station stationFrom) {
		this.stationFrom = stationFrom;
	}
	
	public Station getStationEnd() {
		return stationEnd;
	}
	
	public void setStationEnd(Station stationEnd) {
		this.stationEnd = stationEnd;
	}
}
