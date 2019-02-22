package engine;

import grid.BoxFactory;
import grid.Grid;
import grid.Obstacle;
import used.Point;
import used.Random;

public class GridBuilder {
	/**
	 * This class initializes the grid's settings for the first time.
	 * @author ishak
	 *
	 */
	private Grid grid;
	private int height,width;
	
	public GridBuilder(GridParameters parameters) {
		grid=new Grid();
		height=GridParameters.HEIGHT;
		width=GridParameters.WIDTH;
		buildeGrid(parameters.getGround());
		grid.setGridParameters(parameters);
	}
	/**********		methodes		**********/
	//getters

	/**
	* Returns the grid.
	* @return Grid
	*/
	
	public Grid getGrid() {
		return grid;
	}
	
	//others
	
	/**
	* This method places obstacles and the ground,
	* @param ground
	*/

	public void buildeGrid(String ground) {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				if(i==0 || i==height-1 || j==0 || j==width-1) {
					grid.setBoxAt(i, j,  BoxFactory.creatBox(BoxFactory.creatWall(i, j)));
					grid.setBoxAtFree(i, j, false);
				}else {
					grid.setBoxAt(i, j,  BoxFactory.creatBox(BoxFactory.creatGrass(i, j, ground)));
				}
			}
		}
		randomPositionObstacle();
	}
	
	/**
	 * this methode random obstacle positions
	 */
	
	public void randomPositionObstacle() {
		int x,y;
		Point p;
		for(int i = 0 ; i< GridParameters.getInstance().getFreqObstacle() ; i++ ) {
			do
			{
				 x= Random.randomInt(2,height-3);
				 y= Random.randomInt(2,width-3);
				 p= new Point(x,y);
			}
			while(!grid.getBoxAt(x, y).getIsFree() || !grid.prefDistanceObstacle(p));
			Obstacle o = BoxFactory.creatObstacle(x, y);
			grid.addObstacle(o);
			grid.setBox(x,y,o);
			grid.setBoxAtFree(x, y, false);
		}	
	}
	
	public void creatLake() {
		
	}
}
