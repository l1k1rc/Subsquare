package city;

import java.util.ArrayList;

import used.Direction;
import used.Point;
import used.Random;

public class Citizen {
	private District workDistrict;
	private District originDistrict;
	private Direction lastDirection;
	private Point position;
	private boolean employed;
	private int QI;
	private boolean move;
	private ArrayList<Point> path = new ArrayList<Point>();
	
	public Citizen(District workDistrict, District originDistrict, Point position) {
		this.workDistrict = workDistrict;
		this.originDistrict = originDistrict;
		lastDirection=Direction.randomDirection();
		this.position = position;
		employed = true;
		move = false;
		QI = Random.randomInt(90, 200);
	}
	
	public Citizen(District originDistrict, Point position) {
		this.originDistrict = originDistrict;
		this.position = position;
		lastDirection = Direction.randomDirection();
		employed = false;
		move = false;
		QI = Random.randomInt(90, 200);
	}

	public District getWorkDistrict() {
		return workDistrict;
	}

	public void setWorkDistrict(District workDistrict) {
		this.workDistrict = workDistrict;
	}

	public District getOriginDistrict() {
		return originDistrict;
	}

	public void setOriginDistrict(District originDistrict) {
		this.originDistrict = originDistrict;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Direction getLastDirection() {
		return lastDirection;
	}
	
	public void setLastDirection(Direction lastDirection) {
		this.lastDirection=lastDirection;
	}
	
	public boolean employed() {
		return employed;
	}
	
	public int getQI() {
		return QI;
	}
	
	public void setQI(int QI) {
		this.QI = QI;
	}
	
	public void setMove(boolean move) {
		this.move = move;
	}
	
	public boolean isMove() {
		return move;
	}
	
	public ArrayList<Point> getPath() {
		return path;
	}
	
	public void setPath(ArrayList<Point> path) {
		this.path = path;
	}

	public void move() {
		Point newPos = path.get(0);
		if(newPos.getOrdonne() > position.getOrdonne())
			setLastDirection(Direction.Up);
		else if(newPos.getOrdonne() < position.getOrdonne())
			setLastDirection(Direction.Down);
		else if(newPos.getAbscisse() > position.getAbscisse())
			setLastDirection(Direction.Right);
		else
			setLastDirection(Direction.Left);
		setPosition(newPos);
		path.remove(newPos);
		if(path.isEmpty()) {
			move = false;
			if(!employed) {
				District work = City.getInstance().getDistrictByPosition(newPos);
				if(work.getType().isPrivate() || work.getType().isPublic()) {
					if(work.getMaxCapacity() - work.getType().getNbWorkers() > 10) {
						setWorkDistrict(work);
						employed = true;
					}
				}
			}
		}
	}
}
