package city;

import java.awt.Image;

import javax.swing.ImageIcon;

import used.Point;

public class PrivateDistrict extends District{
	private int nbWorkers;
	private Image PrivateDistrictImage;
	
	public PrivateDistrict(Point position) {
		super(position);
		ImageIcon img;
		
		img = new ImageIcon(getClass().getResource("/images/City/Private/"+getLevel()+".png"));
		PrivateDistrictImage = img.getImage();
		
		nbWorkers = 0;
	}
	
	public PrivateDistrict(String name, Station station, int density, float prosperity, double maintenanceCost,String type, int level, Point position, int nbWorkers) {
		super(name, station, density, prosperity, maintenanceCost, type, level, position);
		this.nbWorkers=nbWorkers;
	}

	public int getNbWorkers() {
		return nbWorkers;
	}

	public void setNbWorkers(int nbWorkers) {
		this.nbWorkers = nbWorkers;
	}
	
	public Image getPrivateDistrictImage() {
		return PrivateDistrictImage;
	}
	
	public void setPrivateDistrictImage(Image privateDistrictImage) {
		PrivateDistrictImage = privateDistrictImage;
	}
}
