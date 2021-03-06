package tests.engine;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

import engine.FloydPathFinding;

public class TestFloyd {
	private final static int INFINI = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		int nbSom = 4;
		int P[][] = {{0, 0, -1, 0}, 
					 {-1, 1, 1, -1}, 
					 {-1, 2, 2, 2}, 
					 {3, 3, -1, 3}};
		double D[][] = {{0, 2, INFINI, 6}, 
						{INFINI, 0, -2, INFINI}, 
						{INFINI, 5, 0, 5}, 
						{-4, -1, INFINI, 0}};
		
		FloydPathFinding floyd = new FloydPathFinding(nbSom, P, D);
		
		System.out.println("matrix of P");
		for(int s = 0; s < nbSom; s++) {
			for(int t= 0; t < nbSom; t++) {
				System.out.print(P[s][t]+" ");
			}
			System.out.println("");
		}
		
		System.out.println("--------------");
		System.out.println("matrix of D");
		for(int s = 0; s < nbSom; s++) {
			for(int t= 0; t < nbSom; t++) {
				System.out.print(D[s][t]+" ");
			}
			System.out.println("");
		}
		
		System.out.println("------- P.C.C(0, 3) -------");
		Stack<Integer> path = floyd.getPath(0, 3);
		while(!path.isEmpty()){
			Integer som = path.pop();
			System.out.print(som+" ");
		}
		
		System.out.println();
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<Integer> sommets = floyd.getPath(0, 3);
		
		ListIterator<Integer> iterator = sommets.listIterator(sommets.size());
		while(iterator.hasPrevious()) {
			Integer it = iterator.previous();
			if(it != 0)
				result.add(it);
		}
		
		for(Integer som : result)
			System.out.print(som+" ");
		
	}
}
