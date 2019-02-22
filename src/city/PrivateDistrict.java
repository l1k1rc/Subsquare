package city;

import used.Point;

public class PrivateDistrict extends District{
	private int nbWorkers;
	
	public PrivateDistrict(Point position) {
		super(position);
		nbWorkers = 0;
	}
	
	public PrivateDistrict(String name, Station station, int density, float prosperity, double maintenanceCost,String type, int level, Point position, int nbWorkers) {
		super(name, station, density, prosperity, maintenanceCost, type, level, position);
		this.nbWorkers=nbWorkers;
	}

	public int getNbWorkers() {
		return nbWorkers;
	}

	public void setNbWorkers(int nbWorkers) {
		this.nbWorkers = nbWorkers;
	}
}
