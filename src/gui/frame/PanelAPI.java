
package gui.frame;

import java.awt.Color;
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

public class PanelAPI extends JPanel {

	private static final long serialVersionUID = 1L;

	private static boolean buildPublicDistrict = false;
	private GridBagConstraints gbc = new GridBagConstraints();

	public PanelAPI() {
		super();

		JButton[] tabButtonAPI = { new JButton(new ImageIcon("resid_icon.png")),
				new JButton(new ImageIcon("commercial_icon.png")), new JButton(new ImageIcon("townhall_icon.png")),
				new JButton(new ImageIcon("subicon.png")), new JButton(new ImageIcon("railicon.png")),
				new JButton(new ImageIcon("zoomin.png")), new JButton(new ImageIcon("zoomless.png")) };

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
			if (gbc.gridx % 7 == 0) {
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
		tabButtonAPI[0].setToolTipText("TODO");
		tabButtonAPI[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// use variable to action the mouse listener, if this one is true, when the user
				// click, the clicklistener is launched
				// the mouse is changed to notificate this
				// at the end, the var is false
				buildPublicDistrict = true;
				/* Method to change the icon to notify the user he's building a new place */

				MainFrame.setCursorOnScene(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(getClass().getResource("/images/cursor/click_build_house.png")).getImage(), new Point(0, 0), "custom cursor"));
			}
		});
		
		tabButtonAPI[6].setToolTipText("Zoom Less");
		tabButtonAPI[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				
				
				
				
				
				System.out.println("Zoom less");
			}
			
		});
	}
	public static boolean getbuildPublicDistrict() {
		return buildPublicDistrict;
	}
	public static void setbuildPublicDistrict(boolean b) {
		buildPublicDistrict= b;
	}
}