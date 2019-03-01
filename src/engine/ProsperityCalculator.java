package engine;

import java.util.ArrayList;


/**
 * @author raphael
 *
 */
public class ProsperityCalculator {
	
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
	
	public static String prosperityInterpretor(float prosperity) throws Exception{
		String interpretation;
		if (prosperity>=0 && prosperity<=0.25) {
			interpretation = "Critical";
		}else if (prosperity>0.25 && prosperity<=0.50) {
			interpretation = "Low";
		}else if (prosperity>0.50 && prosperity<=0.75) {
			interpretation = "Good";
		}else if (prosperity>0.75 && prosperity<=1) {
			interpretation = "Excelent";
		}else {
			throw new Exception("Prosperity out of bound");
		}
		return interpretation;	
	}
}
