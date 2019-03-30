package gui.frame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import city.City;
import city.District;
import engine.GridParameters;
import grid.Grid;
import used.Point;

public class Scene extends JPanel {

	private static final long serialVersionUID = 1L;
	private City city = City.getInstance();
	private Grid grid;
	private Graphics g2;
	private boolean drawGrid = false;
	private static boolean stationView = false;
	private Point pos_gridPoint;

	public Scene() {
		super();
		setPreferredSize(new Dimension(GridParameters.WIDTH * 28, GridParameters.HEIGHT * 28));
		setBorder(BorderFactory.createEtchedBorder());
		setBackground(Color.DARK_GRAY);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintGlobalGrid(g);
	}

	/*
	 * This method allow to draw the map with each obstacle and district. Moreover,
	 * this method implements a drawGrid boolean object which allows to the user,
	 * when an API is selected, to draw a rectangle beside the cursor to detect with a
	 * color if the position is free or not. 
	 * On the second part, the stationView
	 * boolean allows to see another view which display the subway lines and the
	 * districts.
	 */
	public void paintGlobalGrid(Graphics g) {
		g2 = (Graphics2D) g;
		// Anti-aliasing given to the graphics2D object
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		((Graphics2D) g2).setRenderingHints(rh);
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
		if (drawGrid) {
			int x = pos_gridPoint.getAbscisse();
			int y = pos_gridPoint.getOrdonne();
			if (!grid.getBoxAt(y, x).getIsFree()) {
				g2.setColor(Color.RED);
			}
			g2.drawRect(x * 28, y * 28, 28, 28);
		}
		if (stationView) {
			for (int y = 0; y < grid.height; y++) {
				for (int x = 0; x < grid.width; x++) {
					Point p = new Point(x, y);
					if (city.isDistrictPosition(p)) {
						if (city.getDistrictByPosition(p).hasStation()) {
							for (int i = 0; i < city.getDistrictByPosition(p).getStation().getSubwayLines()
									.size(); i++) {
								g2.setColor(city.getDistrictByPosition(p).getStation().getSubwayLines().get(i)
										.getColorLine());
								((Graphics2D) g2).setStroke(new BasicStroke(6));
								g2.drawLine( // NEED POSITION FROM A STATION
										city.getDistrictByPosition(p).getStation().getSubwayLines().get(i)
												.getStationFrom().getStationPos().getAbscisse() * 28,
										city.getDistrictByPosition(p).getStation().getSubwayLines().get(i)
												.getStationFrom().getStationPos().getOrdonne() * 28,
										city.getDistrictByPosition(p).getStation().getSubwayLines().get(i)
												.getStationEnd().getStationPos().getAbscisse() * 28,
										city.getDistrictByPosition(p).getStation().getSubwayLines().get(i)
												.getStationEnd().getStationPos().getOrdonne() * 28);
							}
						}
					}
				}
			}
		}
		drawCity(g2);
	}

	private void drawCity(Graphics g) {
		for (Iterator<District> it = city.getDistricts().values().iterator(); it.hasNext();) {
			District d = it.next();
			g.drawImage(d.getType().getImage(), d.getPosition().getAbscisse() * 28, d.getPosition().getOrdonne() * 28,
					null);
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
		this.pos_gridPoint = pos_gridPoint;
	}
}
