package grid;

import engine.GridParameters;

/**
 * This class contains the grid where the simulation will operate.
 * @author ishak
 */

public class Grid {
	
	public int height;
	public int width;
	
	/**********		attributes		*************/
	private Box[][] boxs;
	private GridParameters parameters;
	/**********		Construct		************/
	/**
	 * creat a new Grid.
	 */
	public Grid() {
		height = GridParameters.HEIGHT;
		width = GridParameters.WIDTH;
		boxs = new Box[height][width];
	}
	
	public Grid(int height, int width) {
		this.height = height;
		this.width = width;
		boxs = new Box[height][width];
	}

	/**********		methodes		**********/
	
				//getters
	
	/**
	 * return the Box at the position abscisse,ordonne
	 * @param abscisse
	 * @param ordonne
	 * @return Box
	 */
	public Box getBoxAt(int abscisse,int ordonne) {
		return boxs[abscisse][ordonne];
	}
	
	/**
	 * set the Box.
	 * @param abscisse
	 * @param ordonne
	 * @param box
	 */
	public void setBoxAt(int abscisse ,int ordonne,Box box) {
		boxs[abscisse][ordonne] = box;
		
	}
	/**
	 * set the accessibility of the Box
	 * @param x
	 * @param y
	 * @param bl
	 */
	public void setBoxAtFree(int x, int  y, boolean bl) {
		boxs[x][y].setIsFree(bl);
	}
	
	public void setBox(int x , int y ,Ground gr ) {
		boxs[x][y].setGroundType(gr);
	}
	
	public Box[][] getBoxs(){
		return boxs;
	}
	
	public void setBoxs(Box[][] boxs){
		this.boxs =boxs;
	}

	/**
	 * gives us the Grid parameters
	 * @return GridParameters
	 */
	public GridParameters getGridParameters() {
		return parameters;
	}
	
				//setters
	public void setGridParameters(GridParameters parameters) {
		this.parameters=parameters;
	}
				//others
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\t\t\t\t\t\t\t\t\t");
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				Ground ground = getBoxAt(i, j).getGroundType();
					if(ground.isWall()) {
						sb.append(".");
					}else if(ground.isGrass()) {
						sb.append(" ");
					}
			}
			sb.append("\n");
			sb.append("\t\t\t\t\t\t\t\t\t");
		}
		return sb.toString();
	}
}