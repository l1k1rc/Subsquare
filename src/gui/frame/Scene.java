package gui.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import city.Citizen;
import city.City;
import city.District;
import city.Station;
import city.SubwayLine;
import engine.GridParameters;
import grid.Grid;
import gui.fontElements.Fonts;
import used.Point;

/**
 * Allow to manage the map and build it with some sprites with paintComponent
 * method.
 * 
 * @author l1k1
 *
 */
public class Scene extends JPanel {

	private static final long serialVersionUID = 1L;
	private City city = City.getInstance();
	private Grid grid;
	private Graphics g2;
	private boolean drawGrid = false;
	private static boolean stationView = false;
	private Point pos_gridPoint;
	
	private JLabel game_over = new JLabel("GAME OVER");

	public Scene() {
		super();
		setPreferredSize(new Dimension(GridParameters.WIDTH * 28, GridParameters.HEIGHT * 28));
		setBorder(BorderFactory.createEtchedBorder());
		setBackground(Color.DARK_GRAY);
		game_over.setForeground(Color.RED);
		game_over.setOpaque(false);
		int delay = 200;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if ((game_over.getForeground() == (Color.RED)) ) {
					game_over.setForeground(Color.GREEN);
				} else if ((game_over.getForeground() == (Color.GREEN))) {
					game_over.setForeground(new Color(30, 170, 255, 60));
				} else {
					game_over.setForeground(Color.RED);
				}
				repaint();
			}
		};
		Timer t1 = new Timer(delay, taskPerformer);
		t1.start();
		game_over.setBounds(50,50,200,200);
		game_over.setFont(Fonts.getF4().deriveFont(150.0f));
		game_over();
	}
	public void game_over() {
		add(game_over);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(stationView)
			paintMap(g);
		else
			paintGlobalGrid(g);
	}

	/**
	 * This method allows to draw the map with each obstacle and district. Moreover,
	 * this method implements a drawGrid boolean object which allows to the user,
	 * when an API is selected, to draw a rectangle beside the cursor to detect with
	 * a color if the position is free or not. On the second part, the stationView
	 * boolean allows to see another view which display the subway lines and the
	 * districts.
	 * 
	 * @param g
	 */
	public void paintGlobalGrid(Graphics g) {
		g.setColor(Color.BLACK);
		g2 = (Graphics2D) g;
		// the dimension of the grid
		for (int y = 0; y < grid.height; y++) {
			for (int x = 0; x < grid.width; x++) {
				Image img = grid.getBoxAt(y, x).getGroundType().getImage();
				g.drawImage(img, x * 28, y * 28, null);
				if (grid.getBoxAt(y, x).getGroundType().isContainsTree()) {
					ImageIcon tree = new ImageIcon(getClass().getResource(
							"/images/Obstacle/arbre" + grid.getBoxAt(y, x).getGroundType().getTreeType() + ".png"));
					g.drawImage(tree.getImage(), x * 28, y * 28, null);
				}
			}
		}
		if (drawGrid && pos_gridPoint != null) {
			@SuppressWarnings("unused")
			final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			int x = pos_gridPoint.getAbscisse();
			int y = pos_gridPoint.getOrdonne();
			if (!grid.getBoxAt(y, x).getIsFree())
				g2.setColor(Color.RED);
			g2.drawRect(x * 28, y * 28, 28, 28);
		}
		drawCity(g2);
	}
	
	public void paintMap(Graphics g) {
		g2 = (Graphics2D) g;
		for (int y = 0; y < grid.height; y++) {
			for (int x = 0; x < grid.width; x++) {
					
				if(grid.getBoxAt(y, x).getGroundType().containsTree)
					g2.setColor(Color.GREEN);
				else
					g2.setColor(Color.WHITE);
			
				g2.fillRect(x * 28, y * 28, 28, 28);
			}
		}
		for (District dist : city.getDistricts().values()) {
			int x = dist.getPosition().getAbscisse();
			int y = dist.getPosition().getOrdonne();
			if(dist.getType().isPrivate())
				g2.setColor(Color.RED);
			else if(dist.getType().isPublic())
				g2.setColor(Color.BLUE);
			else
				g2.setColor(Color.YELLOW);
			
			g2.fillRect(x * 28, y * 28, 28, 28);
			if(dist.hasStation()) {
				g2.setColor(Color.BLACK);			
				for(SubwayLine sub : dist.getStation().getSubwayLines()) {
					Station from = sub.getStationFrom();
					Station end = sub.getStationEnd();
					g2.drawLine(from.getStationPos().getAbscisse() * 28, from.getStationPos().getOrdonne() * 28, 
								end.getStationPos().getAbscisse() * 28, end.getStationPos().getOrdonne() * 28);
				}
			}
		}
	}
	private void drawCity(Graphics g) {
		for (Iterator<District> it = city.getDistricts().values().iterator(); it.hasNext();) {
			District d = it.next();
			g.drawImage(d.getType().getImage(), d.getPosition().getAbscisse() * 28, d.getPosition().getOrdonne() * 28, null);
		}
		for (Citizen ctzn : city.getCitizens()) {
			g.fillOval(ctzn.getPosition().getAbscisse() * 28, ctzn.getPosition().getOrdonne() * 28, 5, 5);
		}
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setDrawGrid(boolean drawGrid) {
		this.drawGrid = drawGrid;
	}

	public boolean isDrawGrid() {
		return drawGrid;
	}

	public static void setStationView(boolean stationViewS) {
		stationView = stationViewS;
	}

	public static boolean isStationView() {
		return stationView;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setPos_gridPoint(Point pos_gridPoint) {
		if(pos_gridPoint.getAbscisse() < GridParameters.WIDTH 
					&& pos_gridPoint.getOrdonne() < GridParameters.HEIGHT) {
			this.pos_gridPoint = pos_gridPoint;
		}
	}
}
