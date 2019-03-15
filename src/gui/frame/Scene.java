package gui.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
	private Grid grid;;
	private Graphics g2;
	private boolean drawGrid = false, drawLine = false;

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

	public void paintGlobalGrid(Graphics g) {
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
		if (drawGrid) {
			int x = pos_gridPoint.getAbscisse();
			int y = pos_gridPoint.getOrdonne();
			if (!grid.getBoxAt(y, x).getIsFree()) {
				g2.setColor(Color.RED);
			}
			g2.drawRect(x * 28, y * 28, 28, 28);
		}
		drawLine(g2);
		drawCity(g2);
	}

	private void drawLine(Graphics g) {
		if (drawLine) {

			int x_A = MainFrame.getPosition_districtA().getAbscisse() * 28;
			int x_B = MainFrame.getPosition_dicstrictB().getAbscisse() * 28;
			int y_A = MainFrame.getPosition_districtA().getOrdonne() * 28;
			int y_B = MainFrame.getPosition_dicstrictB().getOrdonne() * 28;
			g2.drawLine(x_A, y_A, x_B, y_B);
			/* TO SEND TO THE ENGINE TO UPDATE THE GRAPHICS */
			
		}
	}

	private void drawCity(Graphics g) {
		for (Iterator<District> it = city.getDistricts().values().iterator(); it.hasNext();) {
			District d = it.next();
			g.drawImage(d.getType().getImage(), d.getPosition().getAbscisse() * 28, d.getPosition().getOrdonne() * 28,null);
		}
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setDrawGrid(boolean drawGrid) {
		this.drawGrid = drawGrid;
	}

	public void setDrawLine(boolean drawLine) {
		this.drawLine = drawLine;
	}

	public boolean isDrawGrid() {
		return drawGrid;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setPos_gridPoint(Point pos_gridPoint) {
		this.pos_gridPoint = pos_gridPoint;
	}

	public void setLine(boolean line) {
		this.drawLine = line;
	}
}
