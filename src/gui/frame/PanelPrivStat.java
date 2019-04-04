package gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import gui.fontElements.Fonts;

public class PanelPrivStat extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel scorepan = new JPanel();
	private JPanel prosperity = new JPanel();
	private JProgressBar prosperityBar;
	private GridBagConstraints gbc = new GridBagConstraints();

	private ArrayList<JLabel[]> arrayListJLabelPriv = new ArrayList<JLabel[]>();

	/**
	 * This class is the right panel of the frame which appears when the user clicks
	 * on a position on the map. The purpose of this class is to displays each data
	 * about a position, its location, if it's free or not, its price, and if its a
	 * district, it will displays data about it as population, name, workers (...) .
	 * 
	 * @see MainFrame {@link MainFrame}
	 * @author CHEF, MOA
	 */
	public PanelPrivStat() {
		super();

		setBorder(BorderFactory.createEtchedBorder());

		JLabel[] infoDistrict = { new JLabel("District Prosperity", JLabel.CENTER), new JLabel("1"), new JLabel("2"),
				new JLabel("3"), new JLabel("4"), new JLabel("5"), new JLabel("6"), new JLabel("7"), new JLabel("8") };
		JPanel[] tabCells = { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
				new JPanel(), new JPanel(), new JPanel() };

		setLayout(new BorderLayout());
		// ---------------------------Setting and placement of the cells from the
		// GridBagLayout---------
		prosperity.setLayout(new GridBagLayout());
		prosperityBar = new JProgressBar(SwingConstants.VERTICAL);
		prosperityBar.setBackground(Color.DARK_GRAY);

		for (int i = 0; i < infoDistrict.length; i++) {
			infoDistrict[i].setFont(Fonts.getF3());
		}

		gbc.gridx = 0;
		gbc.gridy = 0;
		// gbc.fill = GridBagConstraints.VERTICAL;
		prosperityBar.setPreferredSize(new Dimension(200, 35));
		prosperity.add(prosperityBar, gbc);
		gbc.gridy = 1;
		infoDistrict[0].setPreferredSize(new Dimension(150, 50));
		prosperity.add(infoDistrict[0], gbc);
		gbc.gridy = 2;
		prosperity.add(infoDistrict[1], gbc);
		gbc.gridy = 3;
		prosperity.add(infoDistrict[2], gbc);
		gbc.gridy = 4;
		prosperity.add(infoDistrict[3], gbc);
		gbc.gridy = 5;
		prosperity.add(infoDistrict[4], gbc);
		gbc.gridy = 6;
		prosperity.add(infoDistrict[5], gbc);
		gbc.gridy = 7;
		prosperity.add(infoDistrict[6], gbc);

		// prosperityLabel.setFont(Fonts.getF3());
		for (int index = 0; index < tabCells.length; index++) {
			tabCells[index].setPreferredSize(new Dimension(200, 30));
		}
		infoDistrict[1].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		gbc.gridx = 0;
		gbc.gridy = 0;
		prosperity.add(tabCells[0], gbc);
		gbc.gridy = 1;
		prosperity.add(tabCells[1], gbc);
		gbc.gridy = 2;
		prosperity.add(tabCells[2], gbc);
		gbc.gridy = 3;
		prosperity.add(tabCells[3], gbc);
		gbc.gridy = 4;
		prosperity.add(tabCells[4], gbc);
		gbc.gridy = 5;
		prosperity.add(tabCells[5], gbc);
		gbc.gridy = 6;
		prosperity.add(tabCells[6], gbc);

		scorepan.add(prosperity);
		add(scorepan, BorderLayout.CENTER);

		arrayListJLabelPriv.add(infoDistrict);
	}

	public void setLabelDistrict(int position, String text) {
		arrayListJLabelPriv.get(0)[position].setText(text);
	}

	public JProgressBar getProsperityBar() {
		return prosperityBar;
	}
}