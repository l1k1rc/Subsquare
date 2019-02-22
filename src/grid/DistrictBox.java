package grid;

import javax.swing.ImageIcon;

/**
 * this class specified a Wall type
 * @author ishak
 */

public class DistrictBox extends Ground{

	/***********		construct		***********/
	
	public DistrictBox(int abscisse, int ordonne) {
		super(abscisse, ordonne);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/images/terrain/DistrictBox.png"));
		image = img.getImage();
	}
	
	/***********		methodes		***********/
				//getters
	
				//setters

				//others
	@Override
	public boolean isGrass() {
		return false;
	}
	@Override
	public boolean isWall() {
		return true;
	}
	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public boolean isLake() {
		return false;
	}

	@Override
	public boolean isDistrictBox() {
		// TODO Auto-generated method stub
		return true;
	}
}
