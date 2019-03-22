package city;

import java.awt.Point;
import java.util.ArrayList;

import engine.Simulation;

public class ResidentialDistrict extends DistrictType
{
	private int nbCitizens;
	private float taxes;
	private float utilityCost;
	
	public ResidentialDistrict() {
		nbCitizens = 0;
		taxes = 0;
		utilityCost = 0;
		setImage("/images/City/Residential/0.png");
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

	@Override
	public float getTaxes() {
		// TODO Auto-generated method stub
		return taxes;
	}

	@Override
	public void setTaxes(float taxes) {
		// TODO Auto-generated method stub
		this.taxes=taxes;
	}

	@Override
	public float getMaintenanceCost() {
		return 0;
	}

	@Override
	public void setMaintenanceCost(float taxes) {
		
	}
	

}
