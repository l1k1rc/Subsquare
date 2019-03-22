package tests.engine;

import java.util.ArrayList;

import engine.AStarPathFinding;
import engine.GridBuilder;
import engine.GridParameters;
import grid.Grid;
import used.Point;

public class TestAStar {
	public static void main(String[] args) {
		
		GridParameters parameters = GridParameters.getInstance();
		
		GridBuilder buildGrid = new GridBuilder(parameters);
		
		Grid grid = buildGrid.getGrid();
		
		Point p1 = new Point(16,13);
		Point p2 = new Point(19,12);
		
		System.out.println("the box "+p1+" is "+(grid.getBoxAt(p1.getAbscisse(), p1.getOrdonne()).getIsFree() ? "free":"not free"));
		System.out.println("the box "+p2+" is "+(grid.getBoxAt(p2.getAbscisse(), p2.getOrdonne()).getIsFree() ? "free":"not free"));
		
		System.out.println("the box "+p1+" "+
				(grid.getBoxAt(p1.getAbscisse(), p1.getOrdonne()).getGroundType().isContainsTree() 
						? "contains":"not contains")+" tree");
		System.out.println("the box "+p2+" "+
				(grid.getBoxAt(p2.getAbscisse(), p2.getOrdonne()).getGroundType().isContainsTree() 
						? "contains":"not contains")+" tree");
		
		AStarPathFinding a_star = new AStarPathFinding(grid);
		ArrayList<Point> path = a_star.aStart(p1, p2);

		for(int i = 0; i < path.size(); i++)
			System.out.println(path.get(i));
	}
}
