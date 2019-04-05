package engine;

import java.util.ArrayList;
import java.util.ListIterator;
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

/**
 * Management of travel and data calculation operations The simulationNextTurn
 * is launched each time the thread call it to calculates data.
 * 
 * @author MOEs, CHEF, QA
 *
 */
public class Simulation {

	private static City city = City.getInstance();
	private Grid grid;
	private GridParameters parameters;
	private static int simulationNumberOfTurn;
	private int idStation = 0;
	private static FloydPathFinding floyd = new FloydPathFinding(city.nbStations(), city);
	private static AStarPathFinding aStar;

	private EconomyManager ecoMan = new EconomyManager(city);

	public Simulation(GridParameters parameters) {
		this.parameters = parameters;
		simulationNumberOfTurn = 1;
	}

	public void generatGrid() {
		GridBuilder buildGrid = new GridBuilder(parameters);
		grid = buildGrid.getGrid();
		aStar = new AStarPathFinding(grid);
	}

	public void simulationNextTurn() {

		if (city.getTimeSimulator().getHour() == 0 && city.getTimeSimulator().AM_PM()) {
			for (Citizen citizen : city.getCitizens()) {
				if (!citizen.employed()) {
					citizen.setTimeToSearchWork(Random.randomInt(9, 13));
				}
			}
		}

		for (Citizen citizen : city.getCitizens()) {
			if (!citizen.isMove()) {
				if (city.getTimeSimulator().isWeekEnd()) {
					if (citizen.getQI() < 90) {
						if (citizen.employed()) {
							if (city.getTimeSimulator().getHour() == 9 && city.getTimeSimulator().AM_PM()
									&& citizen.employed()) {
								citizenGoToWork(citizen);
							} else if (city.getTimeSimulator().getHour() == 9 && !city.getTimeSimulator().AM_PM()) {
								citizenGoToHome(citizen);
							}
						}
					} else {
						if (city.getTimeSimulator().getHour() == 9 && city.getTimeSimulator().AM_PM()) {
							citizenWeekEnd(citizen);
						} else if (city.getTimeSimulator().getHour() == 9 && !city.getTimeSimulator().AM_PM()) {
							citizenGoToHome(citizen);
						}
					}
				} else {
					if (citizen.employed()) {
						if (city.getTimeSimulator().getHour() == 9 && city.getTimeSimulator().AM_PM()) {
							citizenGoToWork(citizen);
						} else if (city.getTimeSimulator().getHour() == 9 && !city.getTimeSimulator().AM_PM()) {
							citizenGoToHome(citizen);
						}
					} else {
						if (citizen.getTimeToSearchWork() == city.getTimeSimulator().getHour()
								&& city.getTimeSimulator().AM_PM()) {
							citizenSearchWork(citizen);
						} else if (citizen.isSearchArrive()) {
							citizenGoToHome(citizen);
						}
					}
				}
			} else {
				citizen.move();
			}
		}

		if (simulationNumberOfTurn > 40)
			ecoMan.updateData();

		DistrictLevelUp.automatedLevelUpperPublicPrivate(city);
		simulationNumberOfTurn++;
	}

	public static void citizenGoToHome(Citizen citizen) {
		if (!citizen.getPosition().equals(citizen.getOriginDistrict().getPosition())) {
			ArrayList<Point> path = calculPath(citizen, citizen.getOriginDistrict());
			if (path.size() > 0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}

	public void citizenGoToWork(Citizen citizen) {
		if (!citizen.getPosition().equals(citizen.getWorkDistrict().getPosition())) {
			ArrayList<Point> path = calculPath(citizen, citizen.getWorkDistrict());
			if (path.size() > 0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}

	public void citizenSearchWork(Citizen citizen) {
		ArrayList<District> searchWork = city.getDistrictByType((citizen.getQI() > 120) ? "pri" : "pub");
		District closest = getClosestDistrictWork(citizen.getPosition(), searchWork, citizen);
		if (closest != null) {
			ArrayList<Point> path = calculPath(citizen, closest);
			if (path.size() > 0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}

	public void citizenWeekEnd(Citizen citizen) {
		ArrayList<District> weekEnd = city.getDistrictByType("pub");
		District closest = getClosestPublicDistrict(weekEnd, citizen);
		if (closest != null) {
			ArrayList<Point> path = calculPath(citizen, closest);
			if (path.size() > 0) {
				citizen.setPath(path);
				citizen.setMove(true);
			}
		}
	}

	public static ArrayList<Point> calculPath(Citizen citizen, District toGo) {
		ArrayList<Point> path = new ArrayList<Point>();
		District distIn = city.getDistrictByPosition(citizen.getPosition());

		if (distIn != null) {
			if (distIn.hasStation()) {
				if (toGo.hasStation()) {
					int begin = distIn.getStation().getId();
					int end = toGo.getStation().getId();

					path = getStationsPosByFloyd(begin, end);
					double floyd_dist = calculateTravelDistance(path, false);
					citizen.icreaseTravelSubWay(floyd_dist);
					if (path == null || path.size() == 0) {
						path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
						double dist = calculateTravelDistance(path, true);
						citizen.increaseTravelFoot(dist);
					}
				} else {
					District closest = getClosestDistrictStation(toGo.getPosition());
					if (closest != null) {
						int begin = distIn.getStation().getId();
						int end = closest.getStation().getId();

						path = getStationsPosByFloyd(begin, end);
						double floyd_dist = calculateTravelDistance(path, false);
						citizen.icreaseTravelSubWay(floyd_dist);
						if (path == null || path.size() == 0) {
							path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
							double dist = calculateTravelDistance(path, true);
							citizen.increaseTravelFoot(dist);
						} else {
							ArrayList<Point> path_tmp = aStar.aStart(closest.getPosition(), toGo.getPosition());
							double dist = calculateTravelDistance(path, true);
							citizen.increaseTravelFoot(dist);
							if (path_tmp != null)
								path.addAll(path_tmp);
						}
					} else {
						path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
						double dist = calculateTravelDistance(path, true);
						citizen.increaseTravelFoot(dist);
					}
				}
			} else {
				if (toGo.hasStation()) {
					District closest = getClosestDistrictStation(citizen.getPosition());
					if (closest != null) {
						int begin = closest.getStation().getId();
						int end = toGo.getStation().getId();

						path = aStar.aStart(citizen.getPosition(), closest.getPosition());
						double dist = calculateTravelDistance(path, true);
						citizen.increaseTravelFoot(dist);
						ArrayList<Point> path_tmp = getStationsPosByFloyd(begin, end);
						double floyd_dist = calculateTravelDistance(path, false);
						citizen.icreaseTravelSubWay(floyd_dist);
						if (path_tmp != null)
							path.addAll(path_tmp);
						else {
							path_tmp = aStar.aStart(closest.getPosition(), toGo.getPosition());
							double dist1 = calculateTravelDistance(path, true);
							citizen.increaseTravelFoot(dist1);
						}
					} else {
						path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
						double dist = calculateTravelDistance(path, true);
						citizen.increaseTravelFoot(dist);
					}
				} else {
					path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
					double dist = calculateTravelDistance(path, true);
					citizen.increaseTravelFoot(dist);
				}
			}
		} else {
			District closest = getClosestDistrictStation(citizen.getPosition());
			if (closest != null) {
				path = aStar.aStart(citizen.getPosition(), closest.getPosition());
				double dist = calculateTravelDistance(path, true);
				citizen.increaseTravelFoot(dist);
				if (toGo.hasStation()) {
					int begin = closest.getStation().getId();
					int end = toGo.getStation().getId();

					ArrayList<Point> tmp_path = getStationsPosByFloyd(begin, end);
					double floyd_dist = calculateTravelDistance(path, false);
					citizen.icreaseTravelSubWay(floyd_dist);
					if (tmp_path == null || tmp_path.size() == 0) {
						tmp_path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
						double dist1 = calculateTravelDistance(path, true);
						citizen.increaseTravelFoot(dist1);
					}
					if (tmp_path != null && tmp_path.size() > 0) {
						path.addAll(tmp_path);
					}
				} else {
					District closest1 = getClosestDistrictStation(toGo.getPosition());
					if (closest1 != null) {
						int begin = closest.getStation().getId();
						int end = closest1.getStation().getId();

						ArrayList<Point> tmp_path = getStationsPosByFloyd(begin, end);
						double floyd_dist = calculateTravelDistance(path, false);
						citizen.icreaseTravelSubWay(floyd_dist);
						if (tmp_path == null || tmp_path.size() == 0) {
							tmp_path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
							double dist1 = calculateTravelDistance(path, true);
							citizen.increaseTravelFoot(dist1);
						}
						if (tmp_path != null && tmp_path.size() > 0) {
							path.addAll(tmp_path);
						}

						ArrayList<Point> tmp_path1 = aStar.aStart(closest1.getPosition(), toGo.getPosition());
						double dist1 = calculateTravelDistance(path, true);
						citizen.increaseTravelFoot(dist1);
						if (tmp_path1 != null && tmp_path1.size() > 0) {
							path.addAll(tmp_path1);
						}
					}
				}
			} else {
				path = aStar.aStart(citizen.getPosition(), toGo.getPosition());
				double dist = calculateTravelDistance(path, true);
				citizen.increaseTravelFoot(dist);
			}
		}

		return path;
	}

	public static double calculateTravelDistance(ArrayList<Point> path, boolean isFoot) {
		double dist = 0d;
		if (path != null) {
			for (int i = 0; i < path.size(); i++) {
				if (i != path.size() - 1) {
					dist += path.get(i).distance(path.get(i + 1));
					if (isFoot)
						dist *= EcoData.travelOnFoot_Cost;
					else
						dist *= EcoData.travelInTrain_Cost;
				}
			}
		}
		return dist;
	}

	public static District getClosestDistrictStation(Point position) {
		District result = null;
		double min = Double.MAX_VALUE;
		for (District dist : city.getDistricts().values()) {
			double tmp = position.distance(dist.getPosition());
			if (tmp < min && dist.hasStation()) {
				result = dist;
				min = tmp;
			}
		}
		return result;
	}

	public static District getClosestPublicDistrict(ArrayList<District> publicDis, Citizen citizen) {
		District result = null;
		double min = Double.MAX_VALUE;
		for (District dist : publicDis) {
			double tmp = citizen.getPosition().distance(dist.getPosition());
			if (tmp < min) {
				min = tmp;
				result = dist;
			}
		}
		return result;
	}

	public static District getClosestDistrictWork(Point position, ArrayList<District> searchWork, Citizen citizen) {
		District result = null;
		double min = Double.MAX_VALUE;
		for (District dist : searchWork) {
			double tmp = position.distance(dist.getPosition());
			if (tmp < min && tmp < 10d && !citizen.getNoWork().contains(dist)) {
				min = tmp;
				result = dist;
			}
		}
		return result;
	}

	public static ArrayList<Point> getStationsPosByFloyd(int begin, int end) {
		ArrayList<Point> result = new ArrayList<Point>();
		Stack<Integer> sommets = floyd.getPath(begin, end);
		
		ListIterator<Integer> iterator = sommets.listIterator(sommets.size());
		while(iterator.hasPrevious()) {
			Integer som = iterator.previous();
			if(som != begin) {
				Point pos = city.getPositionById(som);
				if (pos != null)
					result.add(pos);
			}
		}
		return result;
	}

	// getters:
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

	public void buildDistrict(Point position, DistrictType type, String name) {
		Box box = grid.getBoxAt(position.getOrdonne(), position.getAbscisse());
		if (box.getIsFree()) {
			District ds = CityFactory.creatDistrict(position, type, name);
			city.addDistrict(position, ds);
			if (box.getGroundType().containsTree)
				box.getGroundType().setContainsTree(true);
			if (type.isPublic()) {
				float cost = box.getGroundType().getDegre() * EcoData.CONST_DISTRICT;
				if (box.getGroundType().containsTree)
					cost = cost * 4;
				ecoMan.setMoney(cost, "const");
			} else if (type.isResidential()) {
				creatCitizens(null, ds, true, 5);
			}
			box.setIsFree(false);
		}
	}

	public void buildStation(Point pos) {
		District d = city.getDistrictByPosition(pos);
		if (!d.equals(null)) {
			if (!d.hasStation()) {
				Station st = CityFactory.creatStation(idStation, pos);
				idStation++;
				city.addStation();
				d.setStation(st);
				ecoMan.setMoney(EcoData.CONST_STATION, "const");
				setFloyd(new FloydPathFinding(city.nbStations(), city));
			}
		}
	}

	public void buildSubwayLine(Point begin, Point end) {
		District d1 = city.getDistrictByPosition(begin);
		District d2 = city.getDistrictByPosition(end);
		if (!d1.equals(null) && !d2.equals(null)) {
			if (d1.hasStation() && d2.hasStation()) {
				SubwayLine line1 = CityFactory.creatSubwayLine(d1.getStation(), d2.getStation());
				SubwayLine line2 = CityFactory.creatSubwayLine(d2.getStation(), d1.getStation());
				d1.getStation().addSubwayLine(line1);
				d2.getStation().addSubwayLine(line2);
				city.addSubwayLine(line1);
				city.addSubwayLine(line2);
				ecoMan.setMoney(EcoData.CONST_SBLINE, "const");
				setFloyd(new FloydPathFinding(city.nbStations(), city));
			}
		}
	}

	public void creatCitizens(District workDistrict, District originDistrict, boolean unknowWork, int nbCitizens) {
		for (int i = 0; i < nbCitizens; i++) {
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		Simulation.city = city;
	}
}
