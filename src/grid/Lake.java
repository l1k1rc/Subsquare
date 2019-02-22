package grid;

import javax.swing.ImageIcon;

/**
 * this class specified a Wall type
 * @author ishak
 */

public class Lake extends Ground{

	/***********		construct		***********/
	
	public Lake(int abscisse, int ordonne) {
		super(abscisse, ordonne);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/images/terrain/Lake.png"));
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
		return false;
	}
	@Override
	public boolean isObstacle() {
		return false;
	}
	@Override
	public boolean isLake() {
		return true;
	}

	@Override
	public boolean isDistrictBox() {
		// TODO Auto-generated method stub
		return false;
	}
}
