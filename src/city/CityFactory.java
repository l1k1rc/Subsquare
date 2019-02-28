package city;

import used.Point;

/**
 *  A features factory of city object
 * @author Raphaël
 *
 */
public class CityFactory {
	
	/**
	 * create a Point
	 * @param abscisse
	 * @param ordonne
	 * @return Point
	 */
	public static Point createPoint(int abscisse, int ordonne){
//		logger.info("Point creation at : ("+this.abscisse+","+this.ordonne+")");
		 return new Point(abscisse, ordonne);
	}

	/**
	 * create a citizen
	 * @param groundType
	 * @return Citizen
	 */
	public static Citizen createCitizen(String workDistrict, String originDistrict, Point position) {
//		logger.info("Citizen creation at : ("+workDistrict+","+originDistrict+","+position.getAbscisse()+","+position.getOrdonne()+")");
		return new Citizen(workDistrict, originDistrict, position);
	}
	
	public static PublicDistrict creatPublicDistrict(Point position) {
		return new PublicDistrict(position);
	}
	
	public static PrivateDistrict creatPrivateDistrict(Point position) {
		return new PrivateDistrict(position);
	}
	
	public static ResidentialDistrict creatResidentialDistrict(Point position) {
		return new ResidentialDistrict(position);
	}


}
