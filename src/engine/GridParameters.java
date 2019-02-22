package engine;

public class GridParameters {
	
	/**
	 * This class is used to stock informations about the grid.
	 * @author ishak
	 *
	 */
	
	/**********		constants		**********/
	public static int HEIGHT=60;//DEFAULT 22
	public static int WIDTH=120;//DEFAULT 43
	public static int DIMENSION=5;
	public static int speed=300;
	
	/**********		attributs		**********/
	private int freqObstacle;
	private String ground;
	
	private static GridParameters instance = new GridParameters(25, "Grass");
	/**********		construct		**********/
	
	/**
	 * This contructor initializes the parameters to specified values.
	 * @param freqObstacle obstacle frequency
	 * @param freqFood food frequency
	 * @param nbMouses number of mice
	 * @param dimension dimension of grid
	 * @param ground ground type
	 */
	
	private GridParameters(int freqObstacle, String ground) {
		this.freqObstacle=freqObstacle;
		this.ground=ground;
	}
	/**********		methodes		**********/
			//getters
	public String getGround() {
		return ground;
	}
	public int getFreqObstacle() {
		return freqObstacle;
	}
	
	/**
	 * return the unique instance of this class
	 * @return GridParameters
	 */
	
	public static GridParameters getInstance() {
		return instance;
	}
			//setters
	public void setFreqObstacle(int freqObstacle) {
		this.freqObstacle = freqObstacle;
	}
	public void setGroung(String ground) {
		this.ground = ground;
	}
	
	/**
	 * set all parameters of the grid
	 * @param freqObstacle
	 * @param ground
	 */
	
	public void setAll(int freqObstacle,String ground) {
		this.freqObstacle = freqObstacle;
		this.ground = ground;
	}
	
	public static void setSpeed(int speed) {
		GridParameters.speed=speed;
	}
}
