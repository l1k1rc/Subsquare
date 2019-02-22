package city;

import used.Point;

public class PublicDistrict extends District{
	private int nbWorkers;
	private int nbVisitors;
	
	public PublicDistrict(Point position) {
		super(position);
		nbVisitors =0 ;
		nbVisitors = 0;
	}
	
	public PublicDistrict(String name, Station station, int density, float prosperity, double maintenanceCost,String type, int level, Point position, int nbWorkers, int nbVisitors) {
		super(name, station, density, prosperity, maintenanceCost, type, level, position);
		this.nbWorkers=nbWorkers;
		this.nbVisitors=nbVisitors;
	}

	public int getNbWorkers() {
		return nbWorkers;
	}

	public void setNbWorkers(int nbWorkers) {
		this.nbWorkers = nbWorkers;
	}

	public int getNbVisitors() {
		return nbVisitors;
	}

	public void setNbVisitors(int nbVisitors) {
		this.nbVisitors = nbVisitors;
	}
}
