package engine;

import city.City;
import city.CityFactory;
import city.District;
import city.PrivateDistrict;
import city.PublicDistrict;
import city.ResidentialDistrict;
import grid.Box;
import grid.Grid;
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
	
	public void buildDistrict(Point position,String type) {
		Box box = grid.getBoxAt(position.getOrdonne(),position.getAbscisse());
		if(box.getIsFree()) {
			float cost = box.getGroundType().getDegre()*districtData.constructionCost;
			switch (type) {
			case "pub":
				PublicDistrict d1 = CityFactory.creatPublicDistrict(position);
				d1.setConstructionCosts(cost);
				city.addPublicDistrict(d1);
				break;
			case "prv":
				PrivateDistrict d2 = CityFactory.creatPrivateDistrict(position);
				d2.setConstructionCosts(cost);
				city.addPrivateDistrict(d2);
				break;
			case "res":
				ResidentialDistrict d3 = CityFactory.creatResidentialDistrict(position);
				d3.setConstructionCosts(cost);
				city.addResidentialDistrict(d3);
			}
			box.setIsFree(false);
		}	
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
