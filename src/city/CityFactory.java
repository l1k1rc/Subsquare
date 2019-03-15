package city;

import used.Point;

/**
 *  A features factory of city object
 * @author Raphaël
 *
 */
public class CityFactory {

	/**
	 * create a citizen
	 * @param groundType
	 * @return Citizen
	 */

	public static Citizen createCitizen(String workDistrict, String originDistrict, Point position) {
//		logger.info("Citizen creation at : ("+workDistrict+","+originDistrict+","+position.getAbscisse()+","+position.getOrdonne()+")");
		return new Citizen(workDistrict, originDistrict, position);
	}
	
	public static District creatDistrict(Point position,DistrictType type) {
		return new District(position, type);
	}	
	
	public static Station creatStation(int id) {
		return new Station(1, id);
	}
	
	public static SubwayLine creatSubwayLine(Station begin,Station end) {
		return new SubwayLine(begin,end);
	}
}
