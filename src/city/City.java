package city;

import java.util.ArrayList;
import java.util.HashMap;
import engine.TimeSimulator;
import used.Point;

public class City{
	
	private static City instance = new City();
	
	private TimeSimulator timeSim;
	
	private HashMap<Point,District> districts;
	
	private ArrayList<SubwayLine> subwayLines;
	
	private float budget = 1000;
	
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
	
	public static City getInstance() {
		return instance;
	}
	
}