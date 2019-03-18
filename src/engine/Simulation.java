package engine;

import java.awt.Color;
import java.util.ArrayList;

import city.Citizen;
import city.City;
import city.CityFactory;
import city.District;
import city.DistrictType;
import city.Station;
import city.SubwayLine;
import grid.Box;
import grid.Grid;
import staticData.StationData;
import staticData.districtData;
import used.Point;

public class Simulation {
	
	private City city = City.getInstance();
	private Grid grid;
	private GridParameters parameters;
	private static int simulationNumberOfTurn;
	private int idStation = 0;
	private FloydPathFinding floyd = new FloydPathFinding(city.nbStations(), city);
	private AStarPathFinding aStar;
	
	public Simulation(GridParameters parameters) {
		this.parameters=parameters;
		simulationNumberOfTurn=1;
	}
	
	public void generatGrid() {
		GridBuilder buildGrid = new GridBuilder(parameters);
		grid = buildGrid.getGrid();
		aStar = new AStarPathFinding(grid);
	}
	
	public void simulationNextTurn() {
		
		for(Citizen citizen : city.getCitizens())
			citizenToDo(citizen);
		
		simulationNumberOfTurn++;
	}
	
	public void citizenToDo(Citizen citizen) {
		if(!citizen.isMove()) {
			if(citizen.employed()) {
				//TODO citizen go to work
			}
			else {
				ArrayList<District> searchWork = city.getDistrictByType((citizen.getQI() > 120) ? "pri" : "pub");
				District closest = getClosestDistrict(citizen.getPosition(), searchWork);
				if(closest != null) {
					ArrayList<Point> path = aStar.aStart(citizen.getPosition(), closest.getPosition());
					if(path.size()>0) {
						citizen.setPath(path);
						citizen.setMove(true);
					}
				}
			}
		}
		else {
			citizen.move();
		}
	}
	
	private District getClosestDistrict(Point position, ArrayList<District> searchWork) {
		District result = null;
		double min = Double.MAX_VALUE;
		for(District dist : searchWork) {
			double tmp = position.distance(dist.getPosition());
			if(tmp < min && tmp < 10d) {
				min = tmp;
				result = dist;
			}
		}
		return result;
	}
	
	//getters:
	public static int getSimulationTurn() {
		return simulationNumberOfTurn;
	}
	
	
	public GridParameters getParameters() {
		return parameters;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	// builders:
	
	public void buildDistrict(Point position,DistrictType type,String name) {
		Box box = grid.getBoxAt(position.getOrdonne(),position.getAbscisse());
		if(box.getIsFree()) {
			District ds = CityFactory.creatDistrict(position, type, name);
			city.addDistrict(position,ds);
			if(box.getGroundType().containsTree)
				box.getGroundType().setContainsTree(false);
			if(type.isPublic()){
				float cost = box.getGroundType().getDegre()*districtData.constructionCost;
				if(box.getGroundType().containsTree)
					cost = cost*2;
				city.spendMoney(cost);
			}
			box.setIsFree(false);
		}	
	}
	
	public void buildStation(Point pos) {
		District d = city.getDistrictByPosition(pos);
		if(!d.equals(null)) {
			if(!d.hasStation()) {
				Station st = CityFactory.creatStation(idStation, pos);
				idStation++;
				city.addStation();
				d.setStation(st);
				city.spendMoney(StationData.constructStationCost);
				setFloyd(new FloydPathFinding(city.nbStations(), city));
			}
		}
	}
	
	public void buildSubwayLine(Point begin,Point end) {
		District d1 = city.getDistrictByPosition(begin);
		District d2 = city.getDistrictByPosition(end);
		Color colorLine = new Color((int)(Math.random() * 0x1000000));
		if(!d1.equals(null) && !d2.equals(null)) {
			if(d1.hasStation() && d2.hasStation()) {
				SubwayLine line1 = CityFactory.creatSubwayLine(d1.getStation(),d2.getStation(),colorLine);
				SubwayLine line2 = CityFactory.creatSubwayLine(d2.getStation(),d1.getStation(),colorLine);
				d1.getStation().addSubwayLine(line1);
				d2.getStation().addSubwayLine(line2);
				city.addSubwayLine(line1);
				city.addSubwayLine(line2);
				city.spendMoney(StationData.constructLineCost);
				setFloyd(new FloydPathFinding(city.nbStations(), city));
			}
		}
	}
	
	public void creatCitizens(District workDistrict, District originDistrict, boolean unknowWork, int nbCitizens) {
		for(int i = 0; i <= nbCitizens; i++) {
			Citizen ctz = CityFactory.createCitizen(workDistrict, originDistrict, unknowWork);
			city.addCitizen(ctz);
		}
	}
	
	public FloydPathFinding getFloyd() {
		return floyd;
	}
	
	public void setFloyd(FloydPathFinding floyd) {
		this.floyd = floyd;
	}
	
	public AStarPathFinding getaStar() {
		return aStar;
	}
	
	public void setaStar(AStarPathFinding aStar) {
		this.aStar = aStar;
	}
}
