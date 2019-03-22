package city;

public class ResidentialDistrict extends DistrictType
{
	private int nbCitizens;
	
	public ResidentialDistrict() {
		setImage("/images/City/Residential/0.png");
		nbCitizens = 0;
	}

	public int getNbCitizens() {
		return nbCitizens;
	}

	public void setNbCitizens(int nbCitizens) {
		this.nbCitizens = nbCitizens;
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

}
