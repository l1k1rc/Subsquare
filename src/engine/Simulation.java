package engine;

import city.City;
import city.CityFactory;
import city.District;
import grid.BoxFactory;
import grid.Grid;
import used.Point;

public class Simulation {
	
	private City city;
	private Grid grid;
	private GridParameters parameters;
	private static int simulationNumberOfTurn;
	
	public Simulation(GridParameters parameters) {
		city = new City();
		this.parameters=parameters;
		simulationNumberOfTurn=1;
	}
	
	public void buildDistrict(Point center,String type)
	{
		int radius = 1;
		int x = center.getAbscisse()-radius;
		int y = center.getOrdonne()-radius;
		
		for(int i = x; i<x+2*radius+1; i++) {
			for(int j = y; j<y+2*radius+1; j++) {
				grid.setBox(j,i, BoxFactory.creatBuildZone(j,i));
			}
		}
		
		District d = CityFactory.createDistrict(center,type);
		city.addDistrict(d);
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
