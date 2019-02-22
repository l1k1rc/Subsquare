package grid;

/**
 * this is a simple factory of Box.
 * @author ishak
 *
 */

public class BoxFactory {
	
	/********** 	attributs		**********/
//	private static Logger logger = LoggerUtility.getLogger(BoxFactory.class);
	/*********		constaruct methodes		***********/
	
	/**
	 * creat an Box.
	 * @param groundType
	 * @return Box.
	 */
	public static Box creatBox(Ground groundType) {
//		logger.info("Box creation at : ("+groundType.getAbscisse()+","+groundType.getOrdonne()+")");
		return new Box(groundType);
	}
	
	/**
	 * creat an Grass
	 * @param abscisse
	 * @param ordonne
	 * @param ground
	 * @return Grass
	 */
	public static Grass creatGrass(int abscisse,int ordonne,String ground) {
//		logger.info("Grass Ground creat at : ("+abscisse+","+ordonne+")");
		return new Grass(abscisse,ordonne,ground);
	}
	
	/**
	 * creat an Wall
	 * @param abscisse
	 * @param ordonne
	 * @return Wall
	 */
	public static Wall creatWall(int abscisse,int ordonne) {
//		logger.info("Wall Ground creat at : ("+abscisse+","+ordonne+")");
		return new Wall(abscisse,ordonne);
	}
	
	/**
	 * creat an obstacle
	 * @param abscisse
	 * @param ordonne
	 * @return Obstacle
	 */
	public static Obstacle creatObstacle(int abscisse,int ordonne) {
//		logger.info("Obstacle Ground creat at : ("+abscisse+","+ordonne+")");
		return new Obstacle(abscisse,ordonne);
	}
	
	public static Obstacle creatObstacle(int abscisse,int ordonne,int index) {
//		logger.info("Obstacle Ground creat at : ("+abscisse+","+ordonne+")");
		return new Obstacle(abscisse,ordonne,index);
	}
	
	public static DistrictBox creatBuildZone(int abscisse,int ordonne) {
//		logger.info("DistrictBox creat at : ("+abscisse+","+ordonne+")");
		return new DistrictBox(abscisse,ordonne);
	}
}
