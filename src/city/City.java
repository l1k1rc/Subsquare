package city;

import java.util.*;

public class City {
	private String name;
	private ArrayList<PublicDistrict> districtsPublic;
	private ArrayList<PrivateDistrict> districtsPrivate;
	private ArrayList<ResidentialDistrict> districtsResidential;
	private int budget;
	private Date date;
	
	public City() {
		districtsPublic = new ArrayList<PublicDistrict>();
		districtsPrivate = new ArrayList<PrivateDistrict>();
		districtsResidential = new ArrayList<ResidentialDistrict>();
	}
	
	public void addDistrictPub(PublicDistrict d) {
		districtsPublic.add(d);
	}
	
	public void addDistrictPri(PrivateDistrict d) {
		districtsPrivate.add(d);
	}
	
	public void addDistrictRes(ResidentialDistrict d) {
		districtsResidential.add(d);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PublicDistrict> getDistrictsPublic() {
		return districtsPublic;
	}

	public void setDistrictsPublic(ArrayList<PublicDistrict> districts) {
		this.districtsPublic = districts;
	}
	
	public ArrayList<PrivateDistrict> getDistrictsPrivate() {
		return districtsPrivate;
	}

	public void setDistrictsPrivate(ArrayList<PrivateDistrict> districts) {
		this.districtsPrivate = districts;
	}
	
	public ArrayList<ResidentialDistrict> getDistrictsResidential() {
		return districtsResidential;
	}

	public void setDistrictsResidential(ArrayList<ResidentialDistrict> districts) {
		this.districtsResidential = districts;
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