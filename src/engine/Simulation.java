package engine;

import java.util.ArrayList;
import java.util.Stack;

import city.Citizen;
import city.City;
import city.CityFactory;
import city.District;
import city.DistrictType;
import city.Station;
import city.SubwayLine;
import economy.EcoData;
import economy.EconomyManager;
import grid.Box;
import grid.Grid;
import used.Point;
import used.Random;

public class Simulation {
	
	private static City city = City.getInstance();
	private Grid grid;
	private GridParameters parameters;
	private static int simulationNumberOfTurn;
	private int idStation = 0;
	private static FloydPathFinding floyd = new FloydPathFinding(city.nbStations(), city);
	private AStarPathFinding aStar;
	
	private EconomyManager ecoMan = new EconomyManager(city);
	
	public Simulation(GridParameters parameters) {
		this.parameters=parameters;
		simulationNumberOfTurn=1;
	}
	
	public void generatGrid() {
		GridBuilder buildGrid = new GridBuilder(parameters);
		grid = buildGrid.getGrid();
		aStar = new AStarPathFinding(grid);
	}
	
	public void simulationNextTurn() {
		
		if(city.getTimeSimulator().getHour() == 0 && city.getTimeSimulator().AM_PM()) {
			for(Citizen citizen : city.getCitizens()) {
				if(!citizen.employed()) {
					citizen.setTimeToSearchWork(Random.randomInt(9, 13));
				}
			}
		}
		
		for(Citizen citizen : city.getCitizens()) {
			if(!citizen.isMove()) {
				if(city.getTimeSimulator().isWeekEnd()) {
					if(citizen.getQI() < 90) {
						if(citizen.employed()) {
							if(city.getTimeSimulator().getHour() == 9 && city.getTimeSimulator().AM_PM() && citizen.employed()) {
								citizenGoToWork(citizen);
							}
							else if(city.getTimeSimulator().getHour() == 5 && !city.getTimeSimulator().AM_PM()){
								citizenGoToHome(citizen);
							}						
						}
					}
					else {
						if(city.getTimeSimulator().getHour() == 9 && city.getTimeSimulator().AM_PM()) {
							citizenWeekEnd(citizen);
						}
						else {
							citizenGoToHome(citizen);
						}
					}
				}
				else {
					if(citizen.employed()) {
						if(city.getTimeSimulator().getHour() == 9 && city.getTimeSimulator().AM_PM()) {
							citizenGoToWork(citizen);
						}
						else if(city.getTimeSimulator().getHour() == 5 && !city.getTimeSimulator().AM_PM()){
							citizenGoToHome(citizen);
						}
					}
					else {
						if(citizen.getTimeToSearchWork() == city.getTimeSimulator().getHour() 
								&& city.getTimeSimulator().AM_PM()) {
							citizenSearchWork(citizen);
						}
						else if(citizen.isSearchArrive()) {
							citizenGoToHome(citizen);
						}
					}	
				}
			}
			else {
				citizen.move();
			}
		}
		
		//ecoMan.updateData();
		simulationNumberOfTurn++;
	}
	
	public void citizenGoToHome(Citizen citizen) {
		if(!citizen.getPosition().equals(citizen.getOriginDistrict().getPosition())) {
			ArrayList<Point> path = calculPath(citizen, citizen.getOriginDistrict());
			if(path.size()>0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}

	public void citizenGoToWork(Citizen citizen) {
		if(!citizen.getPosition().equals(citizen.getWorkDistrict().getPosition())) {
			ArrayList<Point> path = calculPath(citizen, citizen.getWorkDistrict());
			if(path.size()>0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}
	
	public void citizenSearchWork(Citizen citizen) {
		ArrayList<District> searchWork = city.getDistrictByType((citizen.getQI() > 120) ? "pri" : "pub");
		District closest = getClosestDistrictWork(citizen.getPosition(), searchWork, citizen);
		if(closest != null) {
			ArrayList<Point> path = calculPath(citizen, closest);
			if(path.size() > 0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}
	
	public void citizenWeekEnd(Citizen citizen) {
		ArrayList<District> weekEnd = city.getDistrictByType("pub");
		District closest = getClosestPublicDistrict(weekEnd, citizen);
		if(closest != null) {
			ArrayList<Point> path = calculPath(citizen, closest);
			if(path.size() > 0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}
	
	public ArrayList<Point> calculPath(Citizen citizen, District toGo){
		ArrayList<Point> path = new ArrayList<Point>();
		District distIn = city.getDistrictByPosition(citizen.getPosition());
		
		if(distIn != null) {
			if(distIn.hasStation()) {
				if(toGo.hasStation()) {
					int begin = distIn.getStation().getId();
					int end = toGo.getStation().getId();
					
					path = getStationsPosByFloyd(begin, end);
					if(path == null || path.size() == 0) {
						path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
					}
				}
				else {
					District closest = getClosestDistrictStation(toGo.getPosition());
					if(closest != null) {
						int begin = distIn.getStation().getId();
						int end = closest.getStation().getId();
						
						path = getStationsPosByFloyd(begin, end);
						if(path == null || path.size() == 0) {
							path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
						}
						else {
							ArrayList<Point> path_tmp = aStar.aStart(closest.getPosition(), toGo.getPosition());
							if(path_tmp != null)
								path.addAll(path_tmp);	
						}
					}
					else {
						path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
					}
				}
			}
			else {
				if(toGo.hasStation()) {
					District closest = getClosestDistrictStation(citizen.getPosition());
					if(closest != null) {
						int begin = closest.getStation().getId();
						int end = toGo.getStation().getId();
						
						path = aStar.aStart(citizen.getPosition(), closest.getPosition());
						ArrayList<Point> path_tmp = getStationsPosByFloyd(begin, end);
							if(path_tmp != null)
								path.addAll(path_tmp);
							else {
								path_tmp = aStar.aStart(closest.getPosition(), toGo.getPosition());
							}
					}
					else {
						path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
					}
				}
				else {
					path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
				}
			}
		}
		else {
			District closest = getClosestDistrictStation(citizen.getPosition());
			if(closest != null) {
				path = aStar.aStart(citizen.getPosition(), closest.getPosition());
				if(toGo.hasStation()) {
					int begin = closest.getStation().getId();
					int end = toGo.getStation().getId();
						
					ArrayList<Point>tmp_path = getStationsPosByFloyd(begin, end);
					if(tmp_path == null || tmp_path.size() == 0) {
						tmp_path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
					}
					if(tmp_path != null && tmp_path.size() > 0) {
						path.addAll(tmp_path);
					}
				}
				else {
					District closest1 = getClosestDistrictStation(toGo.getPosition());
					if(closest1 != null) {
						int begin = closest.getStation().getId();
						int end = closest1.getStation().getId();
						
						ArrayList<Point>tmp_path = getStationsPosByFloyd(begin, end);
						if(tmp_path == null || tmp_path.size() == 0) {
							tmp_path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
						}
						if(tmp_path != null && tmp_path.size() > 0) {
							path.addAll(tmp_path);
						}
						
						ArrayList<Point>tmp_path1 = aStar.aStart(closest1.getPosition(), toGo.getPosition());
						if(tmp_path1 != null && tmp_path1.size() > 0) {
							path.addAll(tmp_path1);
						}
					}
				}
			}
			else {
				path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
			}
		}
				
		return path;
	}

	public District getClosestDistrictStation(Point position) {
		District result = null;
		double min = Double.MAX_VALUE;
		for(District dist : city.getDistricts().values()) {
			double tmp = position.distance(dist.getPosition());
			if(tmp < min && dist.hasStation()) {
				result = dist;
				min = tmp;
			}
		}
		return result;
	}
	
	public static District getClosestPublicDistrict(ArrayList<District> publicDis, Citizen citizen) {
		District result = null;
		double min = Double.MAX_VALUE;
		for(District dist : publicDis) {
			double tmp = citizen.getPosition().distance(dist.getPosition());
			if(tmp < min) {
				min = tmp;
				result = dist;
			}
		}
		return result;
	}
	
	public static District getClosestDistrictWork(Point position, ArrayList<District> searchWork, Citizen citizen) {
		District result = null;
		double min = Double.MAX_VALUE;
		for(District dist : searchWork) {
			double tmp = position.distance(dist.getPosition());
			if(tmp < min && tmp < 10d && !citizen.getNoWork().contains(dist)) {
				min = tmp;
				result = dist;
			}
		}
		return result;
	}
	
	public static ArrayList<Point> getStationsPosByFloyd(int begin, int end){
		ArrayList<Point> result = new ArrayList<Point>();
		Stack<Integer> sommets = floyd.getPath(begin, end);
		for(Integer som : sommets) {
			Point pos = city.getPositionById(som);
			if(pos != null)
				result.add(pos);
		}
		return result;
	}
	
	//getters:
	public static int getSimulationTurn() {
		return simulationNumberOfTurn;
	}
	
	
	public GridParameters getParameters() {
		return parameters;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public EconomyManager getEcoManager() {
		return ecoMan;
	}
	
	// builders:
	
	public void buildDistrict(Point position,DistrictType type,String name) {
		Box box = grid.getBoxAt(position.getOrdonne(),position.getAbscisse());
		if(box.getIsFree()) {
			District ds = CityFactory.creatDistrict(position, type, name);
			city.addDistrict(position,ds);
			if(box.getGroundType().containsTree)
				box.getGroundType().setContainsTree(true);
			if(type.isPublic()){
				float cost = box.getGroundType().getDegre()*EcoData.CONST_DISTRICT;
				if(box.getGroundType().containsTree)
					cost = cost*4;
				ecoMan.setMoney(cost,"const");
			}else if(type.isResidential()){
				creatCitizens(null, ds, true, 5);
			}
			box.setIsFree(false);
		}	
	}
	
	public void buildStation(Point pos) {
		District d = city.getDistrictByPosition(pos);
		if(!d.equals(null)) {
			if(!d.hasStation()) {
				Station st = CityFactory.creatStation(idStation, pos);
				idStation++;
				city.addStation();
				d.setStation(st);
				ecoMan.setMoney(EcoData.CONST_STATION,"const");
				setFloyd(new FloydPathFinding(city.nbStations(), city));
			}
		}
	}
	
	public void buildSubwayLine(Point begin,Point end) {
		District d1 = city.getDistrictByPosition(begin);
		District d2 = city.getDistrictByPosition(end);
		if(!d1.equals(null) && !d2.equals(null)) {
			if(d1.hasStation() && d2.hasStation()) {
				SubwayLine line1 = CityFactory.creatSubwayLine(d1.getStation(),d2.getStation());
				SubwayLine line2 = CityFactory.creatSubwayLine(d2.getStation(),d1.getStation());
				d1.getStation().addSubwayLine(line1);
				d2.getStation().addSubwayLine(line2);
				city.addSubwayLine(line1);
				city.addSubwayLine(line2);
				ecoMan.setMoney(EcoData.CONST_SBLINE,"const");
				setFloyd(new FloydPathFinding(city.nbStations(), city));
			}
		}
	}
	
	public void creatCitizens(District workDistrict, District originDistrict, boolean unknowWork, int nbCitizens) {
		for(int i = 0; i < nbCitizens; i++) {
			Citizen ctz = CityFactory.createCitizen(workDistrict, originDistrict, unknowWork);
			city.addCitizen(ctz);
		}
		originDistrict.getType().setNbCitizens(nbCitizens);
	}
	
	public FloydPathFinding getFloyd() {
		return floyd;
	}
	
	public void setFloyd(FloydPathFinding floyd) {
		Simulation.floyd = floyd;
	}
	
	public AStarPathFinding getaStar() {
		return aStar;
	}
	
	public void setaStar(AStarPathFinding aStar) {
		this.aStar = aStar;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		Simulation.city = city;
	}
}

