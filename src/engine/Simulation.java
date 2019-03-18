package engine;

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
	
	public Simulation(GridParameters parameters) {
		this.parameters=parameters;
		simulationNumberOfTurn=1;
	}
	
	public void generatGrid() {
		GridBuilder buildGrid = new GridBuilder(parameters);
		grid = buildGrid.getGrid();
	}
	
	public void simulationNextTurn() {
		
		for(Citizen citizen : city.getCitizens())
			citizenToDo(citizen);
		
		simulationNumberOfTurn++;
	}
	
	public void citizenToDo(Citizen citizen) {
		//TODO act of the citizen
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
				Station st = CityFactory.creatStation(idStation);
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
		if(!d1.equals(null) && !d2.equals(null)) {
			if(d1.hasStation() && d2.hasStation()) {
				SubwayLine line1 = CityFactory.creatSubwayLine(d1.getStation(),d2.getStation());
				SubwayLine line2 = CityFactory.creatSubwayLine(d2.getStation(),d1.getStation());
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
}
