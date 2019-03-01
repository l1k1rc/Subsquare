package engine;

import city.District;

/**
 * @author raphael
 *
 */
public class DistrictGrowth {
	
	private District district;
	private String prosperity;
	
	public DistrictGrowth(District district) throws Exception{
		this.district = district;
		this.prosperity = ProsperityCalculator.prosperityInterpretor(district.getProsperity());
	}
	
	/*public int populationGrowth(String prosperity) {
		
	}*/
}
