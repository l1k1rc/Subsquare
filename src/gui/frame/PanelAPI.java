
package gui.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.fontElements.Fonts;

/**
 * Regroup each API displayed in the MainFrame.
 * 
 * @author l1k1
 *
 */
public class PanelAPI extends JPanel {

	private static final long serialVersionUID = 1L;

	private static boolean buildPublicDistrict = false, buildPrivateDisctrict = false, buildResidentialDistrict = false,
			buildMetroLine = false;
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelAPI() {
		super();

		JButton[] tabButtonAPI = { new JButton(new ImageIcon("resid_icon.png")),
				new JButton(new ImageIcon("commercial_icon.png")), new JButton(new ImageIcon("townhall_icon.png")),
				new JButton(new ImageIcon("subicon.png")), new JButton(new ImageIcon("railicon.png")) };

		JPanel[] tabCells = { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
				new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
				new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
				new JPanel(), new JPanel(), new JPanel() };

		setLayout(new GridBagLayout());
		// ---------------------------Setting and placement of the cells from the
		// GridBagLayout---------
		for (int index = 0; index < tabCells.length; index++) {
			tabCells[index].setPreferredSize(new Dimension(160, 55));
		}
		gbc.gridx = 0;
		gbc.gridy = 0;

		for (int index = 0; index < tabButtonAPI.length; index++) {
			add(tabButtonAPI[index], gbc);
			add(tabCells[index], gbc);

			gbc.gridx++;
			if (gbc.gridx % 5 == 0) {
				gbc.gridy++;
				gbc.gridx = 0;
			}
		}
		// ------------------------Setting JButton with a special font and
		// toolTipPex--------------------
		for (int index = 0; index < tabButtonAPI.length; index++) {
			tabButtonAPI[index].setPreferredSize(new Dimension(100, 60));
			tabButtonAPI[index].setFont(Fonts.getF2());
			tabButtonAPI[index].setBackground(Color.DARK_GRAY);
		}
		tabButtonAPI[0].setToolTipText("Construire un quartier résidentiel");
		tabButtonAPI[1].setToolTipText("Construire un quartier commercial (privé)");
		tabButtonAPI[2].setToolTipText("Construire un quartier des services publics");
		tabButtonAPI[3].setToolTipText("COnstruire une station de métro");
		tabButtonAPI[4].setToolTipText("Construire une ligne de métro");

		/* BUTTON :: build a residential area */
		tabButtonAPI[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// use variable to action the mouse listener, if this one is true, when the user
				// click, the clicklistener is launched
				// the mouse is changed to notificate this
				// at the end, the var is false
				buildResidentialDistrict = true;
				/* Method to change the icon to notify the user he's building a new place */

				MainFrame.setCursorOnScene(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(getClass().getResource("/images/cursor/build_rsd.png")).getImage(),
						new Point(0, 0), "custom cursor"));
			}
		});
		/* BUTTON :: build a private area */
		tabButtonAPI[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildPrivateDisctrict = true;

				MainFrame.setCursorOnScene(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(getClass().getResource("/images/cursor/build_prv.png")).getImage(),
						new Point(0, 0), "custom cursor"));
			}
		});
		/* BUTTON :: build a public service district */
		tabButtonAPI[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (buildPublicDistrict) {
					buildPublicDistrict = false;
					MainFrame.setCursorOnScene(Cursor.getDefaultCursor());
				} else {
					buildPublicDistrict = true;
					MainFrame.setCursorOnScene(Toolkit.getDefaultToolkit().createCustomCursor(
							new ImageIcon(getClass().getResource("/images/cursor/build_srvp.png")).getImage(),
							new Point(0, 0), "custom cursor"));
				}

			}
		});
		// BUTTON :: Build station
		tabButtonAPI[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Scene.isStationView())
					Scene.setStationView(false);
				else
					Scene.setStationView(true);
			}
		});
		// BUTTON :: Metro line
		tabButtonAPI[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildMetroLine = true;
				MainFrame.setCursorOnScene(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(getClass().getResource("/images/Obstacle/rail.png")).getImage(), new Point(0, 0),
						"custom cursor"));
			}
		});

	}

	public static boolean getbuildPublicDistrict() {
		return buildPublicDistrict;
	}

	public static void setbuildPublicDistrict(boolean b) {
		buildPublicDistrict = b;
	}

	public static boolean getbuildPrivateDistrict() {
		return buildPrivateDisctrict;
	}

	public static void setbuildPrivateDistrict(boolean b) {
		buildPrivateDisctrict = b;
	}

	public static boolean getbuildResidentialDistrict() {
		return buildResidentialDistrict;
	}

	public static void setbuildResidentialDistrict(boolean b) {
		buildResidentialDistrict = b;
	}

	public static boolean getbuildMetroLine() {
		return buildMetroLine;
	}

	public static void setbuildMetroLine(boolean b) {
		buildMetroLine = b;
	}

}