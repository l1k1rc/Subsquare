package city;

import java.util.*;

public class City {
	private String name;
	private ArrayList<District> districts;
	private int budget;
	private Date date;
	
	public City() {
		districts = new ArrayList<District>();
	}
	
	public void addDistrict(District d) {
		districts.add(d);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<District> getDistricts() {
		return districts;
	}

	public void setDistricts(ArrayList<District> districts) {
		this.districts = districts;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}