package grid;

import javax.swing.ImageIcon;

/**
 * 
 * @author ishak
 *
 */

public class Grass extends Ground{
	
	
	public Grass(int abscisse, int ordonne, String ground) {
		super(abscisse, ordonne);
		setImage(ground);
	}
	
	private void setImage(String ground) {
		degre = used.Random.randomInt(1,3);
		ImageIcon img = new ImageIcon(getClass().getResource("/images/terrain/"+ground+degre+".png"));
		image = img.getImage();
	}
	
	/**********		methodes		**********/
				//getters
	public int getQuantity() {
		return 0;
	}

				//others
	@Override
	public boolean isGrass() {
		return true;
	}

	@Override
	public boolean isWall() {
		return false;
	}

}
