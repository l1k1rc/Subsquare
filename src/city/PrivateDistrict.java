package city;

import staticData.districtData;

public class PrivateDistrict extends DistrictType
{
	private int nbWorkers;
	private float taxes;

	
	public PrivateDistrict() {
		nbWorkers = 0;
		taxes = 0;
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

	@Override
	public float getTaxes() {
		return taxes;
	}

	@Override
	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}

	@Override
	public float getMaintenanceCost() {
		return 0;
	}

	@Override
	public void setMaintenanceCost(float taxes) {
		
	}

	


	
}
