package city;

import java.util.ArrayList;

import engine.Simulation;
import used.Direction;
import used.Point;
import used.Random;

/**
 * this class represent a Citizen object
 * @author MOEs
 */

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
	
	/**
	 * the construct of citizen
	 * @param workDistrict
	 * @param originDistrict
	 * @param position
	 */
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
	
	/**
	 * the construct of citizen
	 * @param originDistrict
	 * @param position
	 */
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

	/**
	 * return the work district of the citizen
	 * @return workDistrict
	 */
	public District getWorkDistrict() {
		return workDistrict;
	}

	/**
	 * set the work district of the citizen
	 * @param workDistrict
	 */
	public void setWorkDistrict(District workDistrict) {
		this.workDistrict = workDistrict;
	}

	/**
	 * return the origin of the citizen
	 * @return originDistrict
	 */
	public District getOriginDistrict() {
		return originDistrict;
	}

	/**
	 * set the origin of the citizen
	 * @param originDistrict
	 */
	public void setOriginDistrict(District originDistrict) {
		this.originDistrict = originDistrict;
	}

	/**
	 * get the position of the citizen
	 * @return position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * set the position of the citizen
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * get the last direction of the citizen
	 * @return lastDirection
	 */
	public Direction getLastDirection() {
		return lastDirection;
	}
	
	/**
	 * set the last direction of the citizen
	 * @param lastDirection
	 */
	public void setLastDirection(Direction lastDirection) {
		this.lastDirection=lastDirection;
	}
	
	/**
	 * check if the citizen is employed
	 * @return employed
	 */
	public boolean employed() {
		return employed;
	}
	
	/**
	 * get the QI of the citizen
	 * @return QI
	 */
	public int getQI() {
		return QI;
	}
	
	/**
	 * set the QI of the citizen
	 * @param QI
	 */
	public void setQI(int QI) {
		this.QI = QI;
	}
	
	/**
	 * set the move boolean of the citizen
	 * @param move
	 */
	public void setMove(boolean move) {
		this.move = move;
	}
	
	/**
	 * check if the citizen is moving
	 * @return move
	 */
	public boolean isMove() {
		return move;
	}
	
	/**
	 * get the path of the citizen
	 * @return path
	 */
	public ArrayList<Point> getPath() {
		return path;
	}
	
	/**
	 * set the path of the citizen
	 * @param path
	 */
	public void setPath(ArrayList<Point> path) {
		this.path = path;
	}
	
	/**
	 * get the district where the citizen didn't find work
	 * @return noWork
	 */
	public ArrayList<District> getNoWork() {
		return noWork;
	}
	
	/**
	 * set the no work places of the citizen
	 * @param noWork
	 */
	public void setNoWork(ArrayList<District> noWork) {
		this.noWork = noWork;
	}

	/**
	 * move the citizen with using the path
	 */
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
				if(work != null) {
					if((work.getType().isPrivate() || work.getType().isPublic())) {
						if(work.getMaxCapacity() > work.getType().getNbWorkers()) {
							setWorkDistrict(work);
							int new_nb = work.getType().getNbWorkers()+1;
							work.getType().setNbWorkers(new_nb);
							employed = true;
						}
						else {
							noWork.add(work);
						}
					}
				}
				else {
					Simulation.citizenGoToHome(this);
				}
			}
		}
	}
	
	/**
	 * check if the citizen is employed
	 * @return employed
	 */
	public boolean isEmployed() {
		return employed;
	}

	/**
	 * set the boolean of employed citizen
	 * @param employed
	 */
	public void setEmployed(boolean employed) {
		this.employed = employed;
	}
	
	/**
	 * get the closest station from the citizen
	 * @return closestStation
	 */
	public Station getClosestStation() {
		return closestStation;
	}
	
	/**
	 * set the closest station from the citizen
	 * @param closestStation
	 */
	public void setClosestStation(Station closestStation) {
		this.closestStation = closestStation;
	}
	
	/**
	 * get the time to search work for the citizen
	 * @return timeToSearchWork
	 */
	public int getTimeToSearchWork() {
		return timeToSearchWork;
	}
	
	/**
	 * set the time to search work for the citizen
	 * @param timeToSearchWork
	 */
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
