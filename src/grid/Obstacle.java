package grid;

import javax.swing.ImageIcon;

/**
 * 
 * @author ishak
 *
 */

public class Obstacle extends Ground{

	/**********		attributs		**********/
	private int cout;
	/**********		construct		**********/
	public Obstacle(int abscisse, int ordonne, int cout) {
		super(abscisse, ordonne);
		
		String dist="";
		this.cout=cout;
		
		if(cout >= 100)
			dist="super";
		else if(cout >= 80)
			dist="hight";
		else if(cout >= 60)
			dist="medium+";
		else if(cout >= 40)
			dist="medium";
		else if(cout >= 20)
			dist="low";
		else
			dist="low+";
			
		ImageIcon img = new ImageIcon(getClass().getResource("/images/Obstacle/"+dist+".png"));
		image = img.getImage();
	}
	/**********		methodes		**********/
	public boolean isGrass() {
		return false;
	}
	@Override
	public boolean isWall() {
		return false;
	}
	@Override
	public boolean isObstacle() {
		return true;
	}
	@Override
	public boolean isLake() {
		return false;
	}
	
	public int getCout() {
		return cout;
	}
	
	public void setCout(int cout) {
		this.cout = cout;
	}
}