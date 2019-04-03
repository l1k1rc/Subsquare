package economy;

public class EcoData
{	
	// construction:
	public static final float CONST_DISTRICT = 40;
	public static final float CONST_STATION = 10;
	public static final float CONST_SBLINE = 20;
	
	// taxes:
	public static final float TAX_RES = 10;
	public static final float TAX_PRV = 15;
	
	// maintenance:
	public static final float MAINT_COST = 5;
	
	// travel cost
	public static final float travelOnFoot_Cost = 5;
	public static final float travelInTrain_Cost = 1;
	
	//Min Max for each average
	public static final float minTravelTime = 1;
	public static final float maxTravelTime = 47;  //Pythagore Heigh & Width
	
	public static final float minUnemployementRate = 0;	// %
	public static final float maxUnemployementRate = 1; // %
	
	public static final float minStationOverload = 0;	// %
	public static final float maxStationOverload = 1; 	// %
	
	public static final float minProsperity = 0;
	public static final float maxProsperity = 100;


	
}
