package gui.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import engine.GridParameters;
import engine.Simulation;
import engine.TimeSimulator;
import used.Point;

//import engine.Simulation;

public class MainFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int THREAD_MAP = GridParameters.speed;
	private Simulation simulation;
	private TimeSimulator timeSim;
	private static Scene scene = new Scene();
	private PanelScore pScore = new PanelScore();
	private PanelPrivStat pStat = new PanelPrivStat();
	private PanelAPI api = new PanelAPI();
	private static boolean stop = true;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_game = new JMenu("Game");
	private JMenuItem item_save = new JMenuItem("Save");
	private JMenuItem item_load = new JMenuItem("Load a game");
	private JMenuItem item_manual = new JMenuItem("User's manual");
	private JMenuItem item_leave = new JMenuItem("Leave without save");

	/********* construct *********/
	public MainFrame() {
		super("Subsquare");
		setIconImage(new ImageIcon("subsquare_icon.png").getImage());
		setFocusable(true);
		simulation = new Simulation(GridParameters.getInstance());
		simulation.generatGrid();
		timeSim = TimeSimulator.getInstance();
		scene.setGrid(simulation.getGrid());
		init();
		launchGUI();
	}

	private void launchGUI() {
		stop = false;
		Thread chronoThread = new Thread(this);
		chronoThread.start();
	}

	public void init() {
		setResizable(false);
		getContentPane().setBackground(Color.darkGray);

		setSize(1650,760);

		setSize(1400, 760);

		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(scene);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(205, 5, 1185, 600);
		// TODO initialize your frame here

		scene.setBounds(205, 5, 1185, 600);
		api.setBounds(200, 610, 1200, 125);
		pScore.setBounds(0, 0, 200, 1150);
		pStat.setBounds(1400,0, 250, 1150);

		this.menu_game.add(item_save);
		this.menu_game.add(item_load);
		this.menu_game.add(item_manual);
		this.menu_game.add(item_leave);

		// Action for leave without save
		item_leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		item_manual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new ManualFrame();
			}
		});

		this.menuBar.add(menu_game);
		this.setJMenuBar(menuBar);

		PanelScore.go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (stop) {
					stop = false;
					launchGUI();
					GridParameters.setSpeed(800);
					THREAD_MAP = GridParameters.speed;
				}
			}
		});

		PanelScore.stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stop = true;
			}
		});

		PanelScore.fast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (GridParameters.speed > 0) {
					GridParameters.setSpeed(GridParameters.speed - 50);
					THREAD_MAP = GridParameters.speed;
				}
			}
		});

		scene.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			/*
			 * Method to use when the user wa nts to interact with the map, that is to say,
			 * build a place, a line ...
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Point position = new Point(e.getX() / 28, e.getY() / 28); // to know the exact position
				if (PanelAPI.getbuildPublicDistrict()) {
					simulation.buildDistrict(position, "pub");
					PanelAPI.setbuildPublicDistrict(false);
					setCursorOnScene(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					scene.setDrawGrid(false);
				}
			}
		});
		scene.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				Point position = new Point(e.getX() / 28, e.getY() / 28); // to know the exact position			
				if(PanelAPI.getbuildPublicDistrict()) {
					scene.setDrawGrid(true);
					scene.setPos_gridPoint(position);
				}

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		getContentPane().add(api);
		getContentPane().add(pScore);
		getContentPane().add(pStat);
		getContentPane().add(scrollPane);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void updateGUI() {
		// TODO repaint method of the scene
		// TODO check new statistics ..
		updateTime();
		scene.updateUI();
		scene.repaint();
	}

	public void updateTime() {
		timeSim.update();
		pScore.getDateField().setText(timeSim.getDate());
		pScore.getHourField().setText(timeSim.getTime());
	}

	@Override
	public void run() {
		while (!stop) {
			simulation.simulationNextTurn();
			updateGUI();
			try {
				Thread.sleep(THREAD_MAP);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void setThreadSpeed(int thread) {
		MainFrame.THREAD_MAP = thread;
	}

	public static void setStop(boolean stop) {
		MainFrame.stop = stop;
	}

	public static JPanel getScene() {
		return scene;
	}

	public static void setCursorOnScene(Cursor c) {
		scene.setCursor(c);
	}
	
}