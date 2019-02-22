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
	
	public static District createDistrict(Point position,String type) {
		if(type == "pub") {
			return new PublicDistrict(position);
		}
		if(type == "prv") {
			return new PrivateDistrict(position);
		}
		if(type == "res") {
			return new ResidentialDistrict(position);
		}
		return null;
	}
}
