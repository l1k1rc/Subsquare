package engine;

import java.util.Stack;

import city.City;
import city.District;
import city.Station;
import city.SubwayLine;
import used.Point;

public class FloydPathFinding {
	
	private int nbSom;
	private int[][] P;
	private double[][] D;
	private final static int INFINI = Integer.MAX_VALUE;

	public FloydPathFinding(int nbSom, City city) {
		this.nbSom = nbSom;
		initMatrixs(city);
		pathFinding();
	}
	
	public FloydPathFinding(int nbSom, int[][] P, double[][] D) {
		this.nbSom = nbSom;
		this.P = P;
		this.D = D;
		pathFinding();
	}
	
	public void initMatrixs(City city) {
		this.P = new int[nbSom][nbSom];
		this.D = new double[nbSom][nbSom];
		
		for(int s = 0; s < nbSom; s++) {
			for(int t = 0; t < nbSom; t++) {
				D[s][t] = INFINI;
				P[s][t] = -1;
			}
			D[s][s] = 0;
			P[s][s] = s;
		}
		for(District district : city.getDistricts().values()) {
			if(district.hasStation()) {
				Station station = district.getStation();
				int id = station.getId();
				for(SubwayLine subway : station.getSubwayLines()) {
					Station stationEnd = subway.getStationEnd();
					int idEnd = stationEnd.getId();
					P[id][idEnd] = id;
					Point posEnd = city.getPositionById(idEnd);
					double dist = district.getPosition().distance(posEnd);
					D[id][idEnd] = dist;
				}
			}
		}
	}
	
	public int getNbSom() {
		return nbSom;
	}

	public void setNbSom(int nbSom) {
		this.nbSom = nbSom;
	}
	
	public int[][] getP() {
		return P;
	}
	
	public void setMatrix(int[][] P) {
		this.P = P;
	}
	
	public double[][] getValue() {
		return D;
	}
	
	public void setValue(double[][] D) {
		this.D = D;
	}
	
	private void pathFinding() {
		for(int k = 0; k < nbSom; k++) {
			for(int s = 0; s < nbSom; s++) {
				for(int t = 0; t < nbSom; t++) {
					double tmp = D[s][k] + D[k][t];
					if(D[s][t] > tmp) {
						D[s][t] = tmp;
						P[s][t] = P[k][t];
					}
				}
			}
		}
	}
	
	public Stack<Integer> getPath(int begin, int end){
		Stack<Integer> path = new Stack<Integer>();
		path.push(end);
		
		int target = P[begin][end];
		boolean quit = target == begin;
		
		while(!quit) {
			path.push(target);
			
			if(begin >= 0 && target >= 0 && begin <= nbSom && target <= nbSom)
				target = P[begin][target];
			if(target == begin)
				quit = true;
		}
		path.push(begin);
		return path;
	}
}