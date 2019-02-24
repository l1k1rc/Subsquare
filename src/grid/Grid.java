package grid;

import java.util.ArrayList;

import city.City;
import city.PrivateDistrict;
import city.PublicDistrict;
import city.ResidentialDistrict;
import engine.GridParameters;
import used.Point;

/**
 * This class contains the grid where the simulation will operate.
 * @author ishak
 */

public class Grid {
	
	public int height;
	public int width;
	
	/**********		attributes		*************/
	private Box[][] boxs;
	private ArrayList<Obstacle> obstacle;
	private ArrayList<Lake> lake;
	private City city;
	private GridParameters parameters;
	/**********		Construct		************/
	/**
	 * creat a new Grid.
	 */
	public Grid() {
		height = GridParameters.HEIGHT;
		width = GridParameters.WIDTH;
		boxs = new Box[height][width];
		obstacle=new ArrayList<Obstacle>();
		lake=new ArrayList<Lake>();
		city = new City();
	}

	/**********		methodes		**********/
	
				//getters
	
	public City getCity() {
		return city;
	}
	
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
	public ArrayList<Obstacle> getObstacle(){
		return obstacle;
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
	
	public void setCity(City city) {
		this.city = city;
	}
				//others
	public boolean isDistricPos(Point pos) {
		boolean isDistrict=false;
			for(PublicDistrict pub : city.getDistrictsPublic()) {
				if(pub.getPosition().equals(pos))
					isDistrict=true;
			}
			if(!isDistrict)
				for(PrivateDistrict pri : city.getDistrictsPrivate()) {
					if(pri.getPosition().equals(pos))
						isDistrict=true;
				}
			if(!isDistrict)
				for(ResidentialDistrict res : city.getDistrictsResidential()) {
					if(res.getPosition().equals(pos))
						isDistrict=true;
				}
		return isDistrict;
	}
	public boolean prefDistanceObstacle(Point p) {
		
		boolean isInGoodPlace=true;
		
		for(int i=0; i<getObstacle().size(); i++) {
			Point p1 = getObstacle().get(i).position;
			if(p1.distance(p) <= 2d)
				isInGoodPlace = false;
		}
		return isInGoodPlace;
	}
	/**
	 * add a new obstacle to the grid
	 * @param o
	 */
	public void addObstacle(Obstacle o) {
		if(!obstacle.contains(o))
			obstacle.add(o);
	}
	/**
	 * add a new lake to the grid
	 * @param o
	 */
	public void addLake(Lake l) {
		if(!lake.contains(l))
			lake.add(l);
	}
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
					}else if(ground.isLake()) {
						sb.append('l');
					}else if(ground.isObstacle()) {
						sb.append("o");
					}
			}
			sb.append("\n");
			sb.append("\t\t\t\t\t\t\t\t\t");
		}
		return sb.toString();
	}
}