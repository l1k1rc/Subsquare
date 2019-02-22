package gui.frame;

import java.awt.*;
import javax.swing.*;

public class ManualFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManualFrame(){
		this.setTitle("User's Manual");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		setContentPane(manual());
		this.setVisible(true);
	}
	
	private JPanel manual() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Welcome to the user's manual !");
		panel.add(label);
		return panel;
	}
}