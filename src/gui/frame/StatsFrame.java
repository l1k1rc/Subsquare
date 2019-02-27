package gui.frame;

import java.awt.*;
import javax.swing.*;

public class StatsFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatsFrame(){
		this.setTitle("Statistics");
		setIconImage(new ImageIcon("subsquare_icon.png").getImage());
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		setContentPane(manual());
		this.setVisible(true);
	}
	
	private JPanel manual() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Welcome to the statistics's window !");
		panel.add(label);
		return panel;
	}
}
