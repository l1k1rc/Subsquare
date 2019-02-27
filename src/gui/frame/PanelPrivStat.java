package gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import gui.fontElements.Fonts;

public class PanelPrivStat extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel prosperityLabel = new JLabel("Prosperity of the district", JLabel.CENTER);
	
	private JPanel scorePanel = new JPanel();
	private JPanel prosperity = new JPanel();
	
	
	private JProgressBar prosperityBar = new JProgressBar();
	
	public PanelPrivStat() {
		super();
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());
		
		prosperity.setLayout(new GridBagLayout());
		prosperityBar = new JProgressBar(SwingConstants.VERTICAL);
		prosperityBar.setBackground(Color.DARK_GRAY);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;

		c.gridy = 0;
		prosperityBar.setPreferredSize(new Dimension(150, 35));
		prosperity.add(prosperityBar, c);

		
		scorePanel.setLayout(new GridLayout(10, 1));
		
		prosperityLabel.setFont(Fonts.getF3());
		//prosperity.add(prosperityLabel);
		scorePanel.add(prosperity);
		
		add(scorePanel, BorderLayout.CENTER);
	}
	
	public JProgressBar getProsperityBar() {
		return prosperityBar;
	}

}