package city;

/**
 * implements a residential district with an associated picture.
 * 
 * @author MOEs
 *
 */
public class ResidentialDistrict extends DistrictType {
	private int nbCitizens;
	private float taxes;

	public ResidentialDistrict() {
		nbCitizens = 0;
		taxes = 0;
		setImage("/images/City/Residential/1.png");
	}

	@Override
	public int getNbCitizens() {
		return nbCitizens;
	}

	@Override
	public void setNbCitizens(int nbCtz) {
		this.nbCitizens = nbCtz;
	}

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrivate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResidential() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getNbWorkers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNbWorkers(int nbWorkers) {
		// TODO Auto-generated method stub
	}

	@Override
	public float getTaxes() {
		// TODO Auto-generated method stub
		return taxes;
	}

	@Override
	public void setTaxes(float taxes) {
		// TODO Auto-generated method stub
		this.taxes = taxes;
	}

	@Override
	public float getMaintenanceCost() {
		return 0;
	}

	@Override
	public void setMaintenanceCost(float taxes) {

	}

	@Override
	public float getConstructionCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setConstructionCost(float constCost) {
		// TODO Auto-generated method stub

	}

}
