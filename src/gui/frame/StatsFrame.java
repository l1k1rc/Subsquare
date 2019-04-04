package gui.frame;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

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
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Point, District> hmData = new HashMap<Point, District>();

	public StatsFrame(HashMap<Point, District> hmData) {
		for(Map.Entry mapentry :hmData.entrySet()) {
			System.out.println(hmData.get(mapentry.getKey()).getProsperity());
		}
		this.setTitle("Statistics");
		setIconImage(new ImageIcon("subsquare_icon.png").getImage());
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		int[] data1 = { 101, 4, 97 };
		int[] data2 = { 46, 1, 59 };
		int[] data3 = { 71, 0, 86 };

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
