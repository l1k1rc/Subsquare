package city;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class DistrictType
{	
	protected Image image;

	public abstract boolean isPublic();
	public abstract boolean isPrivate(); 
	public abstract boolean isResidential(); 
	
	public void setImage(String path) {
		ImageIcon img = new ImageIcon(getClass().getResource(path));
		image = img.getImage();
	}
	
	public Image getImage() {
		return image;
	}
}
