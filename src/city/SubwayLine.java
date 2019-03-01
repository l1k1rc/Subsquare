package city;

public class SubwayLine {
	private Station stationFrom;
	private Station stationEnd;
	
	public SubwayLine(Station stationFrom, Station stationEnd) {
		this.stationFrom = stationFrom;
		this.stationEnd = stationEnd;
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
