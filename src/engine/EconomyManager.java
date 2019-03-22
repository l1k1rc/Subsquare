package engine;

import city.City;
import city.District;

public class EconomyManager
{
	private City city;
	
	private float budget=50000;
	private float totalExpenses;
	private float totalIncomes;
	
	private float taxes;
	private float maintenanceCost;
	

	public EconomyManager(City city) {
		this.city = city;
	}
	
	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public float getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(float totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public float getTotalIncomes() {
		return totalIncomes;
	}

	public void setTotalIncomes(float totalIncomes) {
		this.totalIncomes = totalIncomes;
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
	
	public void earnMoney(float money) {
		budget+=money;
		totalIncomes+=money;
	}
	
	public void spendMoney(float money) {
		budget-=money;
		totalExpenses+=money;
	}
	
	private void loadValues() {
		float mc=0, tx=0,pr=0, ds =0;
		for(District dist : city.getDistricts().values()) {
			mc += dist.getType().getMaintenanceCost();
			ds += dist.getDensity();
			pr += dist.getProsperity();
			tx += dist.getType().getTaxes();
		}
		pr/=city.getNbDistricts();
		
		maintenanceCost = mc;
		taxes = tx;
		
		city.setDensity(ds);
		city.setProsperity(pr);

	}
	
	private void doExpenseIncome() {
		if(city.getTimeSimulator().isEndOfMonth()) {
			spendMoney(maintenanceCost);
			earnMoney(taxes);
		}
	}
	
	public void updateData() {
		loadValues();
		doExpenseIncome();
	}
		
	public float calculDistrictProsperity(District district)
	{	
		float p = district.getProsperity();
		
		district.setProsperity(p);
		return p;
	}
	
	@Override
	public String toString() {
		return 	"Budget=" + budget +"\n"+
				"totalExpenses=" + totalExpenses+"\n"+
				"totalIncomes=" + totalIncomes+"\n"+
				"taxes=" + taxes +"\n"+
				"maintenanceCost="+ maintenanceCost +"\n";
	}

}
