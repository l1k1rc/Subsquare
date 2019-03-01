package city;

public class PrivateDistrict extends DistrictType
{
	private int nbWorkers;	
	
	public PrivateDistrict() {
		nbWorkers = 0;
		setImage("/images/City/Private/0.png");
	}

	public int getNbWorkers() {
		return nbWorkers;
	}

	public void setNbWorkers(int nbWorkers) {
		this.nbWorkers = nbWorkers;
	}

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrivate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isResidential() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
