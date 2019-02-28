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
import city.PrivateDistrict;
import city.PublicDistrict;
import city.ResidentialDistrict;
import engine.GridParameters;
import grid.Box;
import grid.Grid;
import used.Point;

public class Scene extends JPanel {

	private static final long serialVersionUID = 1L;
	private City city = City.getInstance();
	private Grid grid;
	private ImageIcon pics;
	private Graphics g2;
	private Box box;
	private boolean drawGrid=false;
	private Point pos_gridPoint;

	public Scene() {
		super();
		setPreferredSize(new Dimension(GridParameters.WIDTH*28,GridParameters.HEIGHT*28));
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
				g.drawImage(img,x* 28,y* 28, null);
			}
		}
				
		if(drawGrid) {
			int x = pos_gridPoint.getAbscisse();
			int y = pos_gridPoint.getOrdonne();
			if(!grid.getBoxAt(y,x).getIsFree()) {
				g2.setColor(Color.RED);				
			}
			g2.drawRect(x*28, y*28, 28, 28);
		}
		drawCity(g2);
	}

	private void drawCity(Graphics g) {
		
		for(Iterator<PublicDistrict>it = city.getPublicDistricts().iterator(); it.hasNext(); ) {
			PublicDistrict dist = it.next();
			g.drawImage(dist.getImage(), dist.getPosition().getAbscisse() * 28, dist.getPosition().getOrdonne() * 28, null);
		}
		
		for(Iterator<PrivateDistrict>it = city.getPrivateDistricts().iterator(); it.hasNext(); ) {
			PrivateDistrict dist = it.next();
			g.drawImage(dist.getImage(), dist.getPosition().getAbscisse() * 28, dist.getPosition().getOrdonne() * 28, null);
		}
		
		for(Iterator<ResidentialDistrict>it = city.getResidentialDistricts().iterator(); it.hasNext(); ) {
			ResidentialDistrict dist = it.next();
			g.drawImage(dist.getImage(), dist.getPosition().getAbscisse() * 28, dist.getPosition().getOrdonne() * 28, null);
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
	
	public void setPos_gridPoint(Point pos_gridPoint) {
		this.pos_gridPoint = pos_gridPoint;
	}
}
