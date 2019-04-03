package gui.frame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Class which allows to build the PieChat graphics.
 * @see MainFrame {@link MainFrame}
 * @author l1k1
 *
 */
public class BuildChart extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which send the HashMap containing countries informations.
	 */
	public BuildChart(HashMap<String, int[]> data) {
		super("");
		// TODO Auto-generated constructor stub
		PieChart_AWT("Classement des [************]", data);
		RefineryUtilities.centerFrameOnScreen(this);
	}

	/**
	 * Allows to initialize the graphics ( here it's a pie ).
	 * 
	 * @param windTitle : title of the window
	 * @param hmRanking : HashMap which contains N countries with their values in
	 *                  the "country ranking"
	 */
	public void PieChart_AWT(String windTitle, HashMap<String, int[]> data) { // 2
		setContentPane(createDemoPanel(data));
	}

	/**
	 * Process which allows to create the different values of the pie : that is to
	 * say, country's name and country's value
	 * 
	 * @param hmRanking : HashMap which contains N countries with their values in
	 *                  the "country ranking"
	 * @return dataset
	 */
	private static PieDataset createDataset(HashMap<String, int[]> data) { // 5
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (@SuppressWarnings("rawtypes")
		Map.Entry mapentry : data.entrySet()) {
			/* Here you can set your values in the label of your frame */
			
			 dataset.setValue("val",
			 data.get(mapentry.getKey())[0]);
			 dataset.setValue("val2",
			 data.get(mapentry.getKey())[1]);
			 dataset.setValue("val3",
			 data.get(mapentry.getKey())[2]);

			 
		}

		return dataset;
	}

	/**
	 * Initialize the JFreeChat window
	 * 
	 * @param dataset : A PieDataset variable from JFreeChart package
	 * @see : JFreeChart
	 * @return chart
	 */
	private static JFreeChart createChart(PieDataset dataset) { // 4
		JFreeChart chart = ChartFactory.createPieChart("Classement des [********************]", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	/**
	 * 
	 * @param hmRanking : HashMap which contains N countries with their values in
	 *                  the "country ranking"
	 * @return ChartPanel with the variable "chart" ( the panel associated )
	 */
	public static JPanel createDemoPanel(HashMap<String, int[]> data) { // 3
		JFreeChart chart = createChart(createDataset(data));
		return new ChartPanel(chart);
	}

}
