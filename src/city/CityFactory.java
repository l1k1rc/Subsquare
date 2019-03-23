package city;

import java.awt.Color;

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

	public static Citizen createCitizen(District workDistrict, District originDistrict, boolean unknowWork) {
//		logger.info("Citizen creation at : ("+workDistrict+","+originDistrict+","+position.getAbscisse()+","+position.getOrdonne()+")");
		if(unknowWork)
			return new Citizen(originDistrict, originDistrict.getPosition());
		else
			return new Citizen(workDistrict, originDistrict, originDistrict.getPosition());
	}
	
	public static District creatDistrict(Point position,DistrictType type,String name) {
		return new District(position, type, name,1);
	}	
	
	public static Station creatStation(int id, Point pos) {
		return new Station(1, id, pos);
	}
	
	public static SubwayLine creatSubwayLine(Station begin,Station end, Color colorLine) {
		return new SubwayLine(begin,end,colorLine);
	}
}
