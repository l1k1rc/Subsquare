package city;

public class PublicDistrict extends DistrictType
{
	private int nbWorkers;
	private int nbVisitors;
	private float constructionCosts;
	private float maintenanceCost;
	
	public PublicDistrict() {
		setImage("/images/City/Public/0.png");
		nbVisitors =0 ;
		nbWorkers = 0;
		maintenanceCost = 0;
	}

	public int getNbWorkers() {
		return nbWorkers;
	}

	public void setNbWorkers(int nbWorkers) {
		this.nbWorkers = nbWorkers;
	}

	public int getNbVisitors() {
		return nbVisitors;
	}

	public void setNbVisitors(int nbVisitors) {
		this.nbVisitors = nbVisitors;
	}
	
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isPrivate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResidential() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getTaxes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTaxes(float taxes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaintenanceCost() {
		// TODO Auto-generated method stub
		return maintenanceCost;
	}

	@Override
	public void setMaintenanceCost(float maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}

	@Override
	public float getConstructionCost() {
		// TODO Auto-generated method stub
		return constructionCosts;
	}

	@Override
	public void setConstructionCost(float constCost) {
		this.constructionCosts = constCost;
		
	}

	@Override
	public int getNbCitizens() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNbCitizens(int nbCtz) {
		// TODO Auto-generated method stub
		
	}

	

}
