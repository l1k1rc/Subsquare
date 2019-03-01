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

import city.City;
import engine.GridParameters;
import engine.Simulation;
import engine.TimeSimulator;
import used.Point;

//import engine.Simulation;
/**
 * Class that brings together the elements of the main window. This is where
 * events related to user actions are managed.
 * 
 * @author l1k1
 *
 */
public class MainFrame extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private static int THREAD_MAP = GridParameters.speed;
	private City city = City.getInstance();
	private static Scene scene = new Scene();
	private Simulation simulation;
	private boolean buildMetroLine_click = false;
	private static boolean stop = true;

	private PanelScore pScore = new PanelScore();

	private PanelAPI api = new PanelAPI();
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
		setSize(1650, 760);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		scene.setBounds(205, 5, 1185, 600);
		api.setBounds(200, 610, 1200, 125);
		pScore.setBounds(0, 0, 200, 1150);
		// pStat.setBounds(1400, 0, 250, 1150);

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
		/* When the user click, enter, exit, release or presse the mouse */
		scene.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				/*
				 * When the user click on the panel Scene and if he press the API associated, he
				 * can draw a line on the map. If he click a second time, the builder is over
				 */
				if (buildMetroLine_click == false && PanelAPI.getbuildMetroLine() == true)
					buildMetroLine_click = true;
				else {
					buildMetroLine_click = false;
					PanelAPI.setbuildMetroLine(false);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			/*
			 * Method to use when the user wants to interact with the map, that is to say,
			 * build a place, a line ...
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Point position = new Point(e.getX() / 28, e.getY() / 28);
				/*
				 * If the user wants to build a public district, the cursor appearance is
				 * changed, an area is built on the cursor location and if the user click, a
				 * district is build
				 */
				if (PanelAPI.getbuildPublicDistrict()) {
					simulation.buildDistrict(position, "pub");
					PanelAPI.setbuildPublicDistrict(false);
					setCursorOnScene(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					scene.setDrawGrid(false);
				} else if (PanelAPI.getbuildPrivateDistrict()) {
					simulation.buildDistrict(position, "prv");
					PanelAPI.setbuildPrivateDistrict(false);
					setCursorOnScene(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					scene.setDrawGrid(false);
				} else if (PanelAPI.getbuildResidentialDistrict()) {
					simulation.buildDistrict(position, "res");
					PanelAPI.setbuildResidentialDistrict(false);
					setCursorOnScene(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					scene.setDrawGrid(false);
				} else if (e.getClickCount() == 2) {
					/*
					 * When the user double click on a position, a JPanel is displayed beside the
					 * map and show to the user the information about the position
					 */
					scene.setDrawGrid(true);
					scene.setPos_gridPoint(position);
					PanelPrivStat pStat = new PanelPrivStat();
					pStat.setBounds(1400, 0, 250, 1150);
					pStat.setLabel("Position de ce quartier : " + position);
					getContentPane().add(pStat);
					/*
					 * In a nutshell, the user gotta pay a price if the place isn't free and have an
					 * obstacle ORDONNE // ABSCISSE
					 */
					if (!scene.getGrid().getBoxAt(position.getOrdonne(), position.getAbscisse()).getIsFree()) {
						pStat.setLabel("Attention : cette place est occupée");
						pStat.setPriceInformation("Prix de la zone : 200g");
						/* To draw a line between 2 points */
						/*
						 * if (buildLine_A == false && buildLine_B == false) { buildLine_A = true;
						 * if(buildLineA = true) }
						 */
					}
				}
			}
		});
		/* When the user move or drag the mouse */
		scene.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				/* To draw the grid around an area and manage the line builder */
				Point position = new Point(e.getX() / 28, e.getY() / 28); // to know the exact position
				if (PanelAPI.getbuildPublicDistrict() || PanelAPI.getbuildPrivateDistrict()
						|| PanelAPI.getbuildResidentialDistrict()) {
					scene.setDrawGrid(true);
					scene.setPos_gridPoint(position);
				} else if (PanelAPI.getbuildMetroLine() == true && buildMetroLine_click == true) {
					/* Pour la partie line metro */
					Point line_position = new Point(e.getX() / 28, e.getY() / 28);
					scene.setLine(true);
				}

			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		/* Add content here to the panel */
		getContentPane().add(api);
		getContentPane().add(scene);
		getContentPane().add(pScore);
		// getContentPane().add(pStat);

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
		TimeSimulator timeSim = city.getTimeSimulator();
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

	/* to change the cursor when an API is selected */
	public static void setCursorOnScene(Cursor c) {
		scene.setCursor(c);
	}

}