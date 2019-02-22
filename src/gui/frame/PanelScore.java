package gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import gui.fontElements.Fonts;

public class PanelScore extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel prosperityLabel = new JLabel("Prosperity Bar", JLabel.CENTER);
	private JLabel densityLabel = new JLabel("Density", JLabel.CENTER);
	private JLabel taxesLabel = new JLabel("Taxes", JLabel.CENTER);
	private JLabel servicingLabel = new JLabel("Servicing", JLabel.CENTER);
	private JLabel budgetLabel = new JLabel("Budget", JLabel.CENTER);
	private JLabel date = new JLabel("",JLabel.CENTER);
	private JLabel hour = new JLabel("",JLabel.CENTER);
	
	/**Thoses are temporary Labels waiting for dynamic labels **/
	private JLabel tempDensityLabel = new JLabel("Density", JLabel.CENTER);
	private JLabel tempTaxesLabel = new JLabel("Taxes", JLabel.CENTER);
	private JLabel tempServicingLabel = new JLabel("Servicing", JLabel.CENTER);
	private JLabel tempBudgetLabel = new JLabel("Budget", JLabel.CENTER);	
	
	private JPanel timeButtonPanel= new JPanel();
	private JPanel scorePanel = new JPanel();
	private JPanel prosperityPanel = new JPanel();
	private JPanel servicingPanel = new JPanel();
	private JPanel densityPanel = new JPanel();
	private JPanel taxesPanel = new JPanel();
	private JPanel budgetPanel = new JPanel();
	
	public static JButton stop=new JButton(new ImageIcon("pause.png"));
	public static JButton go=new JButton(new ImageIcon("play.png"));
	public static JButton fast = new JButton(new ImageIcon("fast.png"));
	public static JButton stats = new JButton(new ImageIcon("stats.png"));
	
	private JProgressBar prosperityBar = new JProgressBar();
	
	public PanelScore() {
		super();
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());
		
		prosperityPanel.setLayout(new GridLayout(6, 1));
		servicingPanel.setLayout(new GridLayout(6, 1));
		densityPanel.setLayout(new GridLayout(6, 1));
		taxesPanel.setLayout(new GridLayout(6, 1));
		budgetPanel.setLayout(new GridLayout(6, 1));
		scorePanel.setLayout(new GridLayout(10, 1));
		timeButtonPanel.setLayout(new FlowLayout());
		
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
		
		timeButtonPanel.add(go);
		timeButtonPanel.add(stop);
		timeButtonPanel.add(fast);
		
		prosperityPanel.add(date);
		prosperityPanel.add(hour);
		prosperityPanel.add(prosperityBar);
		prosperityPanel.add(prosperityLabel);
		
		densityPanel.add(densityLabel);
		densityPanel.add(tempDensityLabel);
		
		taxesPanel.add(taxesLabel);
		taxesPanel.add(tempTaxesLabel);
		
		servicingPanel.add(servicingLabel);
		servicingPanel.add(tempServicingLabel);
		
		budgetPanel.add(budgetLabel);
		budgetPanel.add(tempBudgetLabel);
		
		scorePanel.add(prosperityPanel);
		scorePanel.add(densityPanel);
		scorePanel.add(taxesPanel);
		scorePanel.add(servicingPanel);
		scorePanel.add(budgetPanel);
		scorePanel.add(stats);
		
		
		
		add(timeButtonPanel, BorderLayout.NORTH);
		add(scorePanel, BorderLayout.CENTER);
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
}