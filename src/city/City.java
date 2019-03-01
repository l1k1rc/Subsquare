package city;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import engine.TimeSimulator;
import used.Point;

public class City{
	
	private static City instance = new City();
	
	private TimeSimulator timeSim;
	
	Random rnd = new Random();

	private int budget=50000;
	private int taxes=1000;
	private int density=rnd.nextInt(5)+1;
	private int servicing=500;
	private HashMap<Point,District> districts;
	
	private ArrayList<SubwayLine> subwayLines;
	
	private float prosperity;
	
	private City() {
		timeSim = new TimeSimulator();
		districts = new HashMap<Point, District>();
	}
	
	public void addDistrict(Point position,District district) {
		districts.put(position,district);
	}
	
	public void addSubwayLine(SubwayLine line) {
		subwayLines.add(line);
	}
		
	public HashMap<Point,District> getDistricts() {
		return districts;
	}
	
	public ArrayList<SubwayLine> getSubwayLines(){
		return subwayLines;
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
	
	public float getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public void earnMoney(float money) {
		budget+=money;
	}
	
	public void spendMoney(float money) {
		budget-=money;
	}
	
	public District getDistrictByPosition(Point pos){	
		return districts.get(pos);
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
	public float getProsperity() {
		return prosperity;
	}

	public void setProsperity(float prosperity) {
		this.prosperity = prosperity;
	}
}