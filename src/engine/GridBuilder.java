package engine;

import grid.BoxFactory;
import grid.Grid;

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
	}	

}
