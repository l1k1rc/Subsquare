package city;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class DistrictType
{	
	protected Image image;

	public abstract boolean isPublic();
	public abstract boolean isPrivate(); 
	public abstract boolean isResidential();
	
	public abstract float getConstructionCost();
	public abstract void setConstructionCost(float constCost);
	
	public abstract float getTaxes();
	public abstract void setTaxes(float taxes);	
	
	public abstract float getMaintenanceCost();
	public abstract void setMaintenanceCost(float taxes);
	
	public abstract int getNbCitizens();
	public abstract void setNbCitizens(int nbCtz);
	
	public abstract int getNbWorkers();
	public abstract void setNbWorkers(int nbWorkers);
	
	public void setImage(String path) {
		ImageIcon img = new ImageIcon(getClass().getResource(path));
		image = img.getImage();
	}
	
	public Image getImage() {
		return image;
	}
	
	public String toString() {
		if(isPrivate()) {
			return "Quartier privé";
		}else if(isPublic()) {
			return "Service public";
		}else if(isResidential()){
			return "Quartier résidentiel";
		}else {
			return "No";
		}
	}
}
