package gui.fontElements;

import java.awt.Color;
import java.awt.Font;

/**
 * Class which allows to regroup every fonts and colors in the program.
 * @author MATTHIEU
 *
 */
public class Fonts{

	private static Font f1=new Font("Arial rounded MT Bold", Font.PLAIN, 14);
	private static Font f2 = new Font("C", Font.PLAIN, 15);
    private static Font fMenu = new Font("Times New Roman", Font.PLAIN, 25);
    private static Font f3 = new Font("Times New Roman", Font.BOLD, 15);
    private static Font f4 = new Font("Gamer", Font.PLAIN, 60);

    private static Color pop=new Color( 228, 89, 163); //to use later
    
	public Fonts() {}
	
	public static Color getPop() {
		return pop;
	}

	public static Font getF1() {
		return f1;
	}
	public static Font getF2() {
		return f2;
	}
	
	public static Font getF3() {
		return f3;
	}
	
	public static Font getF4() {
		return f4;
	}

	public static Font getfMenu() {
		return fMenu;
	}
	
}
