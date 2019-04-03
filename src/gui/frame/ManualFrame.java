package gui.frame;

import java.awt.*;
import javax.swing.*;

public class ManualFrame extends JFrame {
	/**
	 * Allows to open a JinternalFrame to show the user's guide.
	 */
	private static final long serialVersionUID = 1L;

	public ManualFrame() {
		this.setTitle("User's Manual");
		setIconImage(new ImageIcon("subsquare_icon.png").getImage());
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		setContentPane(manual());
		this.setVisible(true);
	}

	private JPanel manual() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel(new ImageIcon("guide.png"));
		panel.add(label);
		return panel;
	}
}