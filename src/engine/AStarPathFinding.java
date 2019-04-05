package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import grid.Grid;
import used.Point;

public class AStarPathFinding {
	
	private Grid grid;
	private final static Double INFINI = Double.MAX_VALUE;
	
	public AStarPathFinding(Grid grid) {
		this.grid = grid;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public ArrayList<Point> reconstruct_path(HashMap<Point, Point> cameFrom, Point current, boolean order){
		ArrayList<Point> total_path = new ArrayList<Point>();
		total_path.add(current);
		
		for(Point curr : cameFrom.keySet())
			if(!total_path.contains(cameFrom.get(curr)))
				total_path.add(cameFrom.get(curr));
		
		Collections.sort(total_path, new PointCmp(order));
		return total_path;
	}
	
	public Point getLowestValuePoint(ArrayList<Point> openSet, HashMap<Point, Double> fScore) {
		Point lowest_point = null;
		Double lowest_score = INFINI;
		
		for(Point pos : fScore.keySet()) {
			if(lowest_score > fScore.get(pos) && openSet.contains(pos)) {
				lowest_score = fScore.get(pos);
				lowest_point = pos;
			}	
		}
		return lowest_point;
	}
	
	public ArrayList<Point> aStart(Point start, Point gool){
		ArrayList<Point> path = new ArrayList<Point>();
		// The set of nodes already evaluated
		ArrayList<Point> closedSet = new ArrayList<Point>();
		
	    // The set of currently discovered nodes that are not evaluated yet.
	    // Initially, only the start node is known.
		ArrayList<Point> openSet = new ArrayList<Point>();
		openSet.add(start);
		
	    // For each node, which node it can most efficiently be reached from.
	    // If a node can be reached from many nodes, cameFrom will eventually contain the
	    // most efficient previous step.
		HashMap<Point, Point> cameFrom = new HashMap<Point, Point>();
		
		// For each node, the cost of getting from the start node to that node.
		HashMap<Point, Double> gScore = new HashMap<Point, Double>();
		for(int s = 0; s < grid.height; s++) {
			for(int t = 0; t < grid.width; t++) {
				if(grid.getBoxAt(s, t).getIsFree()) {
					Point pos = new Point(t, s);
					if(pos.equals(start))
						// The cost of going from start to start is zero.
						gScore.put(pos, 0d);
					else
						gScore.put(pos, INFINI);
				}
			}
		}
		
	    // For each node, the total cost of getting from the start node to the goal
	    // by passing by that node. That value is partly known, partly heuristic.
		HashMap<Point, Double> fScore = new HashMap<Point, Double>();
		for(int s = 0; s < grid.height; s++) {
			for(int t = 0; t < grid.width; t++) {
				if(!grid.getBoxAt(s, t).getGroundType().isWall()) {
					Point pos = new Point(t, s);
					if(pos.equals(start))
						// For the first node, that value is completely heuristic.
						fScore.put(pos, start.distance(gool));
					else
						fScore.put(pos, INFINI);
				}
			}
		}
		
		while(!openSet.isEmpty()) {
			Point current = getLowestValuePoint(openSet, fScore);
			if(current.equals(gool)) {
				path = reconstruct_path(cameFrom, current, new PointCmp(true).compare(start, gool) < 0);
				break;
			}
			openSet.remove(current);
			closedSet.add(current);
			
			for(int i = current.getOrdonne()-1; i <= current.getOrdonne()+1; i++) {
				for(int j = current.getAbscisse()-1; j <= current.getAbscisse()+1; j++) {
					if(!grid.getBoxAt(i, j).getGroundType().isWall()) {
						Point neighbor = new Point(j, i);
						// Ignore the neighbor which is already evaluated.
						if(closedSet.contains(neighbor))
							continue;
						
							// The distance from start to a neighbor
							double distS0 = 0d;
							for(Point pos : gScore.keySet())
								if(pos.equals(current))
									distS0 = gScore.get(pos);
							double tentative_gScore = distS0 + current.distance(neighbor);
							
							// Discover a new node
							double distS1 = 0d;
							for(Point pos : gScore.keySet())
								if(pos.equals(neighbor))
									distS1 = gScore.get(pos);
							if(!openSet.contains(neighbor))
								openSet.add(neighbor);
							else if(tentative_gScore >= distS1)
								continue;
							
								// This path is the best until now. Record it!
								cameFrom.put(neighbor, current);
							//	System.out.println(neighbor);
								gScore.put(neighbor, tentative_gScore);
								fScore.put(neighbor, neighbor.distance(gool));
					}
				}
			}
		}
		return path;
	}
}

class PointCmp implements Comparator<Point> {
	private boolean order;
	public PointCmp(boolean order) {
		this.order = order;
	}
    public int compare(Point a, Point b) {
        int xComp = Integer.compare(a.getAbscisse(), b.getAbscisse());
        int signe = order ? 1 : -1;
        if(xComp == 0)
            return signe*Integer.compare(a.getOrdonne(), b.getOrdonne());
        else
            return signe*xComp;
    }
}
