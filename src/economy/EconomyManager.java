package economy;

import java.util.Collection;
import city.City;
import city.District;
import engine.TimeSimulator;

public class EconomyManager
{
	private City city;
	
	private float budget = 50000;
	
	// values in each end of month:
	private float constructionCost = 0;
	private float maintenanceCost = 0;
	private float taxes = 0;
	
	// accumulated values:
	private float totalConstructionCost = 0;
	private float totalMaintenanceCost = 0;
	private float totalTaxes = 0;
	
	public EconomyManager(City city) {
		this.city = city;
	}
	
	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public float getTotalConstructionCost() {
		return totalConstructionCost;
	}

	public void setTotalConstructionCost(float totalConstructionCost) {
		this.totalConstructionCost = totalConstructionCost;
	}

	public float getTotalMaintenanceCost() {
		return totalMaintenanceCost;
	}

	public void setTotalMaintenanceCost(float totalMaintenanceCost) {
		this.totalMaintenanceCost = totalMaintenanceCost;
	}

	public float getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(float totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	public float getTotalExpenses() {
		return totalConstructionCost+totalMaintenanceCost;
	}

	public float getTotalIncomes() {
		return totalTaxes;
	}

	public float getConstructionCost() {
		return constructionCost;
	}

	public void setConstructionCost(float constructionCost) {
		this.constructionCost = constructionCost;
	}

	public float getTaxes() {
		return taxes;
	}

	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}

	public float getMaintenanceCost() {
		return maintenanceCost;
	}

	public void setMaintenanceCost(float maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}
		
	public void setMoney(float money,String src) {
		switch (src) {
			case "const":
				 budget-=money;
				 totalConstructionCost+=money;
				 break;

			case "maint":
				 budget-=money;
				 totalMaintenanceCost+=money;
				 break;
				 
			case "tax":
				 budget+=money;
				 totalTaxes+=money;
				break;
		}
	}
	
	public void updateData() 
	{	
		TimeSimulator time = city.getTimeSimulator();
		Collection<District> districts = city.getDistricts().values();
		
		float mc=0, tx=0,pr=0,emp=0;
		
		if(time.isEndOfDay())
		{
			for(District dist : districts) {
				EcoCalculator.calcDistUnemployement(city,dist);
				mc += EcoCalculator.calcMaintenanceCost(dist);
				tx += EcoCalculator.calcTaxes(dist);
				pr += EcoCalculator.calcProsperity(city, dist);
			}
			pr/=city.getNbDistricts();
			city.setProsperity(pr);
			
			emp = EcoCalculator.calcUnemployement(city.getCitizens());
			city.setUnemployement(emp);
			
			setMoney(mc,"maint");
			setMoney(tx,"tax");
		}

	}		
	
	@Override
	public String toString()
	{
		return 	"Budget = " + budget +"\n"+
	
				"constructionCost = " + constructionCost +"\n"+
				"maintenanceCost = " + maintenanceCost +"\n"+
				"taxes = " + taxes +"\n"+
				
				"totalConstructionCost = "+ totalConstructionCost +"\n"+
				"totalMaintenanceCost = " + totalMaintenanceCost +"\n"+
				"totalTaxes = " + totalTaxes +"\n"+

				"totalExpenses=" + getTotalExpenses()+"\n"+
				"totalIncomes=" +getTotalIncomes()+"\n";
	}

}
