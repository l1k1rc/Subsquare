package city;

import java.util.ArrayList;
import java.util.Random;

import engine.TimeSimulator;

public class City {
	
	private static City instance = new City();
	
	private TimeSimulator timeSim;
	
	private ArrayList<PublicDistrict> publicDistricts;
	private ArrayList<PrivateDistrict> privateDistricts;
	private ArrayList<ResidentialDistrict> residentialDistricts;
	
	Random rnd = new Random();

	private int budget=50000;
	private int taxes=1000;
	private int density=rnd.nextInt(5)+1;
	private int servicing=500;
	
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

	
	public String getTaxesField() {
		return taxes+" €/month";
	}
	
	public String getBudgetField() {
		return budget+" €";
	}
	
	public String getDensityField() {
		return density+" inhabitants";
	}
	public String getServicingField() {
		return servicing+" €/month";
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public void setTaxes(int taxes) {
		this.taxes=taxes;
	}
	
	public void setDensity(int density) {
		this.density=density;
	}
	public void setServicing(int servicing) {
		this.servicing=servicing;
	}
	public static City getInstance() {
		return instance;
	}
}