package city;

import java.util.ArrayList;

import used.Direction;
import used.Point;
import used.Random;

public class Citizen {
	private District workDistrict;
	private District originDistrict;
	private Direction lastDirection;
	private Station closestStation;
	private Point position;
	private boolean employed,SearchArrive;
	private int QI, timeToSearchWork;
	private boolean move;
	private double travelFoot, travelSubWay;
	private ArrayList<Point> path = new ArrayList<Point>();
	private ArrayList<District> noWork = new ArrayList<District>();
	
	public Citizen(District workDistrict, District originDistrict, Point position) {
		this.workDistrict = workDistrict;
		this.originDistrict = originDistrict;
		lastDirection=Direction.randomDirection();
		this.position = position;
		employed = true;
		move = false;
		SearchArrive = false;
		timeToSearchWork = Random.randomInt(9, 13);
		QI = Random.randomInt(90, 200);
		if(originDistrict.hasStation())
			this.closestStation = originDistrict.getStation();
		else
			this.closestStation = City.getInstance().getClosestStation(position);
	}
	
	public Citizen(District originDistrict, Point position) {
		this.originDistrict = originDistrict;
		this.position = position;
		lastDirection = Direction.randomDirection();
		employed = false;
		move = false;
		SearchArrive = false;
		QI = Random.randomInt(90, 200);
		timeToSearchWork = Random.randomInt(9, 13);
		if(originDistrict.hasStation())
			this.closestStation = originDistrict.getStation();
		else
			this.closestStation = City.getInstance().getClosestStation(position);
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
	
	public ArrayList<District> getNoWork() {
		return noWork;
	}
	
	public void setNoWork(ArrayList<District> noWork) {
		this.noWork = noWork;
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
				SearchArrive = true;
				if(work != null && (work.getType().isPrivate() || work.getType().isPublic())) {
					if(work.getMaxCapacity() - work.getType().getNbWorkers() > 10) {
						setWorkDistrict(work);
						work.getType().setNbWorkers(work.getType().getNbWorkers()+1);
						employed = true;
					}
					else {
						noWork.add(work);
					}
				}
			}
		}
	}
	
	public boolean isEmployed() {
		return employed;
	}

	public void setEmployed(boolean employed) {
		this.employed = employed;
	}
	
	public Station getClosestStation() {
		return closestStation;
	}
	
	public void setClosestStation(Station closestStation) {
		this.closestStation = closestStation;
	}
	
	public int getTimeToSearchWork() {
		return timeToSearchWork;
	}
	
	public void setTimeToSearchWork(int timeToSearchWork) {
		this.timeToSearchWork = timeToSearchWork;
	}
	
	public boolean isSearchArrive() {
		return SearchArrive;
	}
	
	public void setSearchArrive(boolean searchArrive) {
		SearchArrive = searchArrive;
	}
	
	public double getTravelFoot() {
		return travelFoot;
	}
	
	public void increaseTravelFoot(double travel) {
		this.travelFoot += travel;
	}
	
	public double getTravelSubWay() {
		return travelSubWay;
	}
	
	public void icreaseTravelSubWay(double travel) {
		this.travelSubWay += travel;
	}
}
