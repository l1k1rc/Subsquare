package grid;

import javax.swing.ImageIcon;

/**
 * this class specified a Wall type
 * 
 * @author MOEs
 */

public class Wall extends Ground {

	/*********** construct ***********/

	public Wall(int abscisse, int ordonne) {
		super(abscisse, ordonne);
		ImageIcon img = new ImageIcon(getClass().getResource("/images/terrain/Wall.png"));
		image = img.getImage();
		degre = 0;
	}

	/*********** methodes ***********/
	// getters

	// setters

	// others
	@Override
	public boolean isGrass() {
		return false;
	}

	@Override
	public boolean isWall() {
		return true;
	}

}
