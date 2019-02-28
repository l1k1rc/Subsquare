package city;

import java.util.ArrayList;

import engine.TimeSimulator;

public class City {
	
	private static City instance = new City();
	
	private TimeSimulator timeSim;
	
	private ArrayList<PublicDistrict> publicDistricts;
	private ArrayList<PrivateDistrict> privateDistricts;
	private ArrayList<ResidentialDistrict> residentialDistricts;

	private int budget;
	
	private City() {
		timeSim = new TimeSimulator();
		publicDistricts = new ArrayList<PublicDistrict>();
		privateDistricts = new ArrayList<PrivateDistrict>();
		residentialDistricts = new ArrayList<ResidentialDistrict>();
	}
	
	public void addPublicDistrict(PublicDistrict d) {
		publicDistricts.add(d);
	}
	
	public void addPrivateDistrict(PrivateDistrict d) {
		privateDistricts.add(d);
	}
	
	public void addResidentialDistrict(ResidentialDistrict d) {
		residentialDistricts.add(d);
	}
		
	public ArrayList<PublicDistrict> getPublicDistricts() {
		return publicDistricts;
	}

	public ArrayList<PrivateDistrict> getPrivateDistricts() {
		return privateDistricts;
	}

	public ArrayList<ResidentialDistrict> getResidentialDistricts() {
		return residentialDistricts;
	}

	public TimeSimulator getTimeSimulator() {
		return timeSim;
	}

	
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	
	public static City getInstance() {
		return instance;
	}
}