package city;

public class PublicDistrict extends DistrictType
{
	private int nbWorkers;
	private int nbVisitors;
	private float constructionCosts;
	
	public PublicDistrict() {
		setImage("/images/City/Public/0.png");
		nbVisitors =0 ;
		nbVisitors = 0;
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
	
	public void setConstructionCosts(float costs) {
		constructionCosts = costs;
	}
	
	public float getConstructionCosts() {
		return constructionCosts;
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

}
