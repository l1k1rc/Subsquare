package city;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import engine.TimeSimulator;
import used.Point;

public class City
{
	private static City instance = new City();
	
	private TimeSimulator timeSim;
	
	private HashMap<Point,District> districts;
	private ArrayList<SubwayLine> subwayLines;
	private ArrayList<Citizen> citizens;

	private int nbStation;
	private int servicing=500;
	private float prosperity;
	private float unemployement;

	private City() {
		timeSim = new TimeSimulator();
		districts = new HashMap<Point, District>();
		subwayLines = new ArrayList<SubwayLine>();
		citizens=new ArrayList<Citizen>();
		nbStation = 0;
	}
	
	public static City getInstance() {
		return instance;
	}

	public int getServicing() {
		return servicing;
	}

	public void setServicing(int servicing) {
		this.servicing = servicing;
	}

	public float getProsperity() {
		return prosperity;
	}

	public void setProsperity(float prosperity) {
		this.prosperity = prosperity;
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
	
	public int getNbDistricts() {
		return districts.size();
	}
	
	public District getDistrictByPosition(Point pos){	
		District district = null;
		
		for(District dist : districts.values()) {
			if(dist.getPosition().equals(pos)) {
				district = dist;
				break;
			}
		}	
		return district;
	}
	
	public boolean isDistrictPosition(Point position) {
		boolean contains = false;
		Set<Point> pos = districts.keySet();
		
		for(Point p : pos) {
			if(p.equals(position)) {
				contains = true;
				 break;
			}
		}	
		return contains;
	}

	public void displayPositions() {
		for (Iterator<District> it = getDistricts().values().iterator(); it.hasNext();) {
			District d = it.next();
			System.out.println("Hashmap:("+d.getPosition().getAbscisse()+":"+d.getPosition().getOrdonne()+")");
		}
	}
	
	public Point getPositionById(int id) {
		Point pos = null;
		for(District district : districts.values()) {
			if(district.hasStation()) {
				if(district.getStation().getId() == id)
					pos = district.getPosition();
			}
		}
		return pos;
	}
	
	public int getIdByPosition(Point position) {
		int id = 0;
		for(District district : districts.values()) {
			if(district.hasStation()) {
				if(district.getPosition().equals(position))
					id = district.getStation().getId();
			}
		}
		return id;
	}
	
	public ArrayList<Citizen> getCitizensByDistrict(District dist){
		ArrayList<Citizen> result = new ArrayList<Citizen>();
		for(Citizen c : citizens) {
			if(c.getOriginDistrict().equals(dist)) {
				result.add(c);
			}
		}
		return result;
	}
	
	public int getNbCitizensOfDistrict(District dist) {
		int count = 0;
		for(Citizen c : citizens) {
			if(c.getOriginDistrict().equals(dist)) {
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<District> getDistrictByType(String type){
		ArrayList<District> result = new ArrayList<District>();
			for(District dis : districts.values()) {
				switch (type) {
				case "pri":
					if(dis.getType().isPrivate())
						result.add(dis);
					break;
				case "pub":
					if(dis.getType().isPublic())
						result.add(dis);	
					break;			
				case "res":
					if(dis.getType().isResidential())
						result.add(dis);
					break;
				}
			}
		return result;
	}

	public int nbStations() {
		return nbStation;
	}
	
	public void addStation() {
		this.nbStation++;
	}
	
	public int getNbSubwayLines() {
		return subwayLines.size();
	}
	
	public void addCitizen(Citizen citizen) {
		if(!citizens.contains(citizen))
			citizens.add(citizen);
	}
	
	public ArrayList<Citizen> getCitizens() {
		return citizens;
	}

	public void setCitizens(ArrayList<Citizen> citizens) {
		this.citizens = citizens;
	}

	public int getNbStation() {
		return nbStation;
	}

	public void setNbStation(int nbStation) {
		this.nbStation = nbStation;
	}
	
	public int getNbCitizens() {
		return citizens.size();
	}

	public float getUnemployement() {
		return unemployement;
	}

	public void setUnemployement(float unemployement) {
		this.unemployement = unemployement;
	}
	
	public String interpretProsprerity()
	{
		if (prosperity<=0.25) {
			return "Critical";
		}
		else if (prosperity>0.25 && prosperity<=0.50) {
			return "Low";
		}
		else if (prosperity>0.50 && prosperity<=0.75) {
			return "Good";
		}
		else{
			return "Excelent";
		}
	}
	
	@Override
	public String toString() {
		return  "Date=" + timeSim.getTime() +"\n"+
				"nbDistricts=" + getNbDistricts() + "\n"+
				"SubwayLines=" + subwayLines +"\n"+
				"nbStation=" + nbStation +"\n"+
				"Citizens=" + getNbCitizens() +"\n"+
				"Prosperity=" + prosperity;
	}
	
	
}