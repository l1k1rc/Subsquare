package gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import city.City;
import city.District;
import economy.EconomyManager;
import gui.fontElements.Fonts;
import used.Point;

public class PanelScore extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel prosperityLabel = new JLabel("Prosperity Bar", JLabel.CENTER);
	private JLabel densityLabel = new JLabel("Density", JLabel.CENTER);
	private JLabel taxesLabel = new JLabel("Taxes", JLabel.CENTER);
	private JLabel servicingLabel = new JLabel("Servicing", JLabel.CENTER);
	private JLabel budgetLabel = new JLabel("Budget", JLabel.CENTER);
	private JLabel date = new JLabel("",JLabel.CENTER);
	private JLabel hour = new JLabel("",JLabel.CENTER);
	private JLabel budget = new JLabel("",JLabel.CENTER);
	private JLabel taxes = new JLabel("",JLabel.CENTER);
	private JLabel density = new JLabel("",JLabel.CENTER);
	private JLabel servicing = new JLabel("",JLabel.CENTER);
	
	private JPanel timeButtonPanel= new JPanel();
	private JPanel scorePanel = new JPanel();
	private JPanel prosperityPanel = new JPanel();
	private JPanel servicingPanel = new JPanel();
	private JPanel densityPanel = new JPanel();
	private JPanel taxesPanel = new JPanel();
	private JPanel budgetPanel = new JPanel();
	private JPanel statsPanel = new JPanel();
	
	public static JButton stop=new JButton(new ImageIcon("pause.png"));
	public static JButton go=new JButton(new ImageIcon("play.png"));
	public static JButton fast = new JButton(new ImageIcon("fast.png"));
	public static JButton stats = new JButton(new ImageIcon("stats.png"));
	
	private JProgressBar prosperityBar = new JProgressBar();

	public PanelScore() {
		super();
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());
		
		//retrieve data of each city build on the map
		
		prosperityPanel.setLayout(new GridLayout(6, 1));
		servicingPanel.setLayout(new GridLayout(6, 1));
		densityPanel.setLayout(new GridLayout(6, 1));
		taxesPanel.setLayout(new GridLayout(6, 1));
		budgetPanel.setLayout(new GridLayout(6, 1));
		scorePanel.setLayout(new GridLayout(10, 1));
		timeButtonPanel.setLayout(new FlowLayout());
		statsPanel.setPreferredSize(new Dimension(160, 55));
		
		prosperityBar = new JProgressBar(SwingConstants.VERTICAL);
		prosperityBar.setBackground(Color.red);
		
		prosperityLabel.setFont(Fonts.getF3());
		servicingLabel.setFont(Fonts.getF3());
		densityLabel.setFont(Fonts.getF3());
		taxesLabel.setFont(Fonts.getF3());
		budgetLabel.setFont(Fonts.getF3());
		
		go.setBackground(Color.DARK_GRAY);
		stop.setBackground(Color.DARK_GRAY);
		fast.setBackground(Color.DARK_GRAY);
		stats.setBackground(Color.DARK_GRAY);
		
		stats.setPreferredSize(new Dimension(100, 60));
		stats.setToolTipText("Voir les statistiques de la ville");
		
		timeButtonPanel.add(go);
		timeButtonPanel.add(stop);
		timeButtonPanel.add(fast);
		
		prosperityPanel.add(date);
		prosperityPanel.add(hour);
		prosperityPanel.add(prosperityBar);
		prosperityPanel.add(prosperityLabel);
		
		densityPanel.add(densityLabel);
		densityPanel.add(density);
		
		taxesPanel.add(taxesLabel);
		taxesPanel.add(taxes);
		
		servicingPanel.add(servicingLabel);
		servicingPanel.add(servicing);
		
		budgetPanel.add(budgetLabel);
		budgetPanel.add(budget);
		
		statsPanel.add(stats);
		
		scorePanel.add(prosperityPanel);
		scorePanel.add(densityPanel);
		scorePanel.add(taxesPanel);
		scorePanel.add(servicingPanel);
		scorePanel.add(budgetPanel);
		scorePanel.add(statsPanel);
		
		add(timeButtonPanel, BorderLayout.NORTH);
		add(scorePanel, BorderLayout.CENTER);
		
		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new StatsFrame();
			}
		});
	}
	
	public JProgressBar getProsperityBar() {
		return prosperityBar;
	}
	
	public JLabel getDateField() {
		return date;
	}
	
	public JLabel getHourField() {
		return hour;
	}
	public JLabel getBudgetField() {
		return budget;
	}
	public JLabel getTaxesField() {
		return taxes;
	}
	public JLabel getDensityField() {
		return density;
	}
	public JLabel getServicingField() {
		return servicing;
	}
}