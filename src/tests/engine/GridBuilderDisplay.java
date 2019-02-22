package tests.engine;

import engine.GridBuilder;
import engine.GridParameters;
import grid.Grid;

public class GridBuilderDisplay {
	
	public static void main(String[] args) {
		
		GridParameters parameters = GridParameters.getInstance();
		
		GridBuilder buildGrid = new GridBuilder(parameters);
		
		Grid grid = buildGrid.getGrid();
		
		System.out.println(grid);
		
	}
}
