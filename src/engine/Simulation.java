package engine;

import city.CityFactory;
import grid.Grid;
import used.Point;

public class Simulation {
	
	private Grid grid;
	private GridParameters parameters;
	private static int simulationNumberOfTurn;
	
	public Simulation(GridParameters parameters) {
		this.parameters=parameters;
		simulationNumberOfTurn=1;
	}
	
	public void buildDistrict(Point center,String type){
		if(!grid.isDistricPos(center))
			CityFactory.createDistrict(center,type,grid.getCity());
	}
	
	//getters
	public static int getSimulationTurn() {
		return simulationNumberOfTurn;
	}
	
	public GridParameters getParameters() {
		return parameters;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * generer of grid and upload statistics
	 */
	
	public void generatGrid() {
		GridBuilder buildGrid = new GridBuilder(parameters);
		grid = buildGrid.getGrid();
	}
	
	public void simulationNextTurn() {
		// TODO the next turn of the simulation
		simulationNumberOfTurn++;
	}
}
