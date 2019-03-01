package engine;

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
	
	public Simulation(GridParameters parameters) {
		this.parameters=parameters;
		simulationNumberOfTurn=1;
	}
	
	public void generatGrid() {
		GridBuilder buildGrid = new GridBuilder(parameters);
		grid = buildGrid.getGrid();
	}
	public void simulationNextTurn() {
		// TODO the next turn of the simulation
		simulationNumberOfTurn++;
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
	
	public void buildDistrict(Point position,DistrictType type) {
		Box box = grid.getBoxAt(position.getOrdonne(),position.getAbscisse());
		if(box.getIsFree()) {
			District ds = CityFactory.creatDistrict(position, type);
			city.addDistrict(position,ds);
			if(type.isPublic()){
				float cost = box.getGroundType().getDegre()*districtData.constructionCost;
				city.spendMoney(cost);
			}
			box.setIsFree(false);
		}	
	}
	
	public void buildStation(Point pos) {
		District d = city.getDistrictByPosition(pos);
		if(!d.equals(null)) {
			if(d.getStation().equals(null)) {
				Station st = CityFactory.creatStation();
				d.setStation(st);
				city.spendMoney(StationData.constructStationCost);
			}
		}
	}
	
	public void buildSubwayLine(Point begin,Point end) {
		District d1 = city.getDistrictByPosition(begin);
		District d2 = city.getDistrictByPosition(end);
		if(!d1.equals(null) && !d2.equals(null)) {
			if(!d1.getStation().equals(null) && !d2.getStation().equals(null)) {
				SubwayLine line = CityFactory.creatSubwayLine(d1.getStation(),d2.getStation());
				city.addSubwayLine(line);
				city.spendMoney(StationData.constructLineCost);
			}
		}
	}
	
}
