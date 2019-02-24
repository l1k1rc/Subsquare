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
	
	public static void createDistrict(Point position,String type,City city) {
		if(type.equals("pub")) {
			PublicDistrict pub = new PublicDistrict(position);
			city.addDistrictPub(pub);
		}else if(type.equals("prv")) {
			PrivateDistrict pri = new PrivateDistrict(position);
			city.addDistrictPri(pri);
		}else if(type.equals("res")) {
			ResidentialDistrict res = new ResidentialDistrict(position);
			city.addDistrictRes(res);
		}
	}
}
