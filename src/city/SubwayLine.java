package city;

/**
 * Object class to implements a subwayline between two district which have
 * station.
 * 
 * @author l1k1
 *
 */
public class SubwayLine {
	private Station stationFrom;
	private Station stationEnd;

	/**
	 * To creates a line between 2 districts.
	 * 
	 * @param stationFrom = the first position
	 * @param stationEnd  = the second position
	 */
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
