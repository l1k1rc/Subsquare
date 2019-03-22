package engine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

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
	
	private EconomyManager ecoMan = new EconomyManager(city);
	
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
		
		for(Citizen citizen : city.getCitizens()) {
			if(city.getTimeSimulator().getHour()==9 && city.getTimeSimulator().AM_PM())
				citizenGoToWork(citizen);
			else if(city.getTimeSimulator().getHour()==6 && !city.getTimeSimulator().AM_PM())
				citizenGoToHome(citizen);
		}
		
		ecoMan.updateData();
		simulationNumberOfTurn++;
	}
	
	private void citizenGoToHome(Citizen citizen) {
		// TODO citizen go to home
		
	}

	public void citizenGoToWork(Citizen citizen) {
		if(!citizen.isMove()) {
			if(citizen.employed()) {
				if(!citizen.getPosition().equals(citizen.getWorkDistrict().getPosition())) {
					int begin = city.getIdByPosition(citizen.getOriginDistrict().getPosition());
					int end = city.getIdByPosition(citizen.getWorkDistrict().getPosition());
					ArrayList<Point> path = getStationsPosByFloyd(begin, end);
					if(path.size()>0) {
						citizen.setPath(path);
						citizen.setMove(true);
					}
				}
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
		else
			citizen.move();
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
	
	public ArrayList<Point> getStationsPosByFloyd(int begin, int end){
		ArrayList<Point> result = new ArrayList<Point>();
		Stack<Integer> sommets = floyd.getPath(begin, end);
		for(Integer som : sommets) {
			Point pos = city.getPositionById(som);
			if(pos != null)
				result.add(pos);
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
	
	public EconomyManager getEcoManager() {
		return ecoMan;
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
				ecoMan.spendMoney(cost);
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
				ecoMan.spendMoney(StationData.constructStationCost);
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
				ecoMan.spendMoney(StationData.constructLineCost);
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
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
