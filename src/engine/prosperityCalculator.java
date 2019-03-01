package engine;

import java.util.ArrayList;

/**
 * @author raphael
 *
 */
public class prosperityCalculator {
	
	public static float districtProsperityCalc(int inhabitant, int travelTime) {
			
			
		return 0;
		
	}
	
	public static float cityProsperityCalc(ArrayList<Float> districtsProsperity) {
		float scoreProsperity = 0;
		int i;
		for(i=0; i<=districtsProsperity.size(); i++) {
			scoreProsperity+= districtsProsperity.get(i);
		}
		
		return scoreProsperity/= districtsProsperity.size();
	}
}
