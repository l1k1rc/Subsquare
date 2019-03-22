package city;

import java.awt.Color;

public class SubwayLine {
	private Station stationFrom;
	private Station stationEnd;
	private Color colorLine;

	public SubwayLine(Station stationFrom, Station stationEnd, Color colorLine) {
		this.stationFrom = stationFrom;
		this.stationEnd = stationEnd;
		this.setColorLine(colorLine);
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

	public Color getColorLine() {
		return colorLine;
	}

	public void setColorLine(Color colorLine) {
		this.colorLine = colorLine;
	}
}
