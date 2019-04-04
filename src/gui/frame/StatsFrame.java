package gui.frame;

import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import city.District;
import used.Point;

public class StatsFrame extends JFrame {
	/**
	 * Allow to launch a JinternalFrame when the user click on statistic button and
	 * launch a piechart from JFreechart .jar.
	 * 
	 * @see BuildChart {@link BuildChart}
	 * @author CHEF, RVR
	 */
	private static final long serialVersionUID = 1L;

	public StatsFrame(HashMap<Point, District> hmData) {
		this.setTitle("Statistics");
		setIconImage(new ImageIcon("subsquare_icon.png").getImage());
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);

		// setContentPane(manual());
		setContentPane(new BuildChart(hmData).getContentPane());
		this.setVisible(true);
	}

	@SuppressWarnings("unused")
	private JPanel manual() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		return panel;
	}
}
