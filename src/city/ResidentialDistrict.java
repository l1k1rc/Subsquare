package city;

import java.util.ArrayList;

import used.Point;

public class ResidentialDistrict extends District{
	private int nbCitizens;
	private ArrayList<Citizen> citizens;
	
	public ResidentialDistrict(Point position) {
		super(position);
		citizens=new ArrayList<Citizen>();
		nbCitizens = 0;
	}
	
	public ResidentialDistrict(String name, Station station, int density, float prosperity, double maintenanceCost,String type, int level, Point position, int nbCitizens) {
		super(name, station, density, prosperity, maintenanceCost, type, level, position);
		this.nbCitizens=nbCitizens;
		this.citizens=new ArrayList<Citizen>();
	}

	public int getNbCitizens() {
		return nbCitizens;
	}

	public void setNbCitizens(int nbCitizens) {
		this.nbCitizens = nbCitizens;
	}

	public ArrayList<Citizen> getCitizens() {
		return citizens;
	}

	public void setCitizens(ArrayList<Citizen> citizens) {
		this.citizens = citizens;
	}
}
