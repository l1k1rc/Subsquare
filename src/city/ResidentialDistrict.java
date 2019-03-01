package city;

import java.util.ArrayList;

public class ResidentialDistrict extends DistrictType
{
	private int nbCitizens;
	private ArrayList<Citizen> citizens;
	
	public ResidentialDistrict() {
		setImage("/images/City/Residential/0.png");
		citizens=new ArrayList<Citizen>();
		nbCitizens = 0;
	}

	public int getNbCitizens() {
		return nbCitizens;
	}

	public void setNbCitizens(int nbCitizens) {
		this.nbCitizens = nbCitizens;
	}

	public ArrayList<Citizen> getCitizens() {
		return citizens;
	}

	public void setCitizens(ArrayList<Citizen> citizens) {
		this.citizens = citizens;
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

}
