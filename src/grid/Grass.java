package grid;

import javax.swing.ImageIcon;

/**
 * 
 * @author ishak
 *
 */

public class Grass extends Ground{
	
	public Grass(int abscisse, int ordonne, String ground, boolean containsTree) {
		super(abscisse, ordonne);
		setImage(ground);
		this.containsTree=containsTree;
	}
	
	private void setImage(String ground) {
		degre = used.Random.randomInt(1,3);
		treeType = used.Random.randomInt(1,2);

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
