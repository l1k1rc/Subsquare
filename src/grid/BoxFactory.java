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
	public static Grass creatGrass(int abscisse,int ordonne,String ground,boolean containsTree) {
//		logger.info("Grass Ground creat at : ("+abscisse+","+ordonne+")");
		return new Grass(abscisse,ordonne,ground,containsTree);
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
}
