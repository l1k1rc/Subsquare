package gui.menu;

import javax.swing.UIManager;

import gui.menu.PanelMenu;

public class LauncherMenu {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            // select Look and Feel and call the program
    	     UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");        	
    	     PanelMenu.frameMenu();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
