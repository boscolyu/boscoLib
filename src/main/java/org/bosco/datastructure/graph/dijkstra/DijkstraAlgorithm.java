package org.bosco.datastructure.graph.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;


public class DijkstraAlgorithm {

	
	
	public int[] Dijkstra(int [][]graph, int start, int end, int []vertexList) {
		int []distance = new int[vertexList.length];
		int []previous = new int[vertexList.length];
		
		int []S = new int[vertexList.length];
		List<Integer> Q = new ArrayList<Integer>();
		
		for (int i = 0; i < vertexList.length; i++) {
			distance[i] = Integer.MAX_VALUE;
			Q.add(new Integer(vertexList[i]));
		}
		distance[start] = 0;
		S[start] = 1;
		
		while (Q.size() != 0) {
			int minVertex = extractMinVertexFromQ(Q, S, distance);

			for (int j = 0; j < vertexList.length; j++) {
				int dist = graph[minVertex][j];
				if (S[j] == 1) {
					continue;
				}
				if (distance[j] > distance[minVertex] + dist) {
					distance[j] = distance[minVertex] + dist;
					previous[j] = minVertex;
				}
			}
		}
		return previous;
	}
	
	private int extractMinVertexFromQ(List<Integer> Q, int[] s, int []distance) {
		int min = Integer.MAX_VALUE;
		int next = -1;
		for (int i =0; i < s.length; i++) {
			if (s[i] == 1) {
				continue;
			}
			if ((min > distance[i])) {
				min = distance[i];
				next = i;
			}			
		}
		s[next] = 1;
		return next;
	}

	
	public static int MAX_VERTICES	= 7;
	public static int MAXCOST			= 1000;
	
	public static void main(String args[]) {
		DijkstraAlgorithm dkjkstra = new DijkstraAlgorithm();
		int start = 1;
		int dest  = 3;
		int vertexList[] = { 0, 1, 2, 3, 4, 5, 6};
		
		int [][]cost = {
				{       0,      20,      30, MAXCOST, MAXCOST, MAXCOST, MAXCOST},
				{      20,       0,      20,      10,      30, MAXCOST, MAXCOST},
				{      30,      20,       0,      15, MAXCOST, MAXCOST,       5},
				{ MAXCOST,      10,      15,       0,      15, MAXCOST, MAXCOST},
				{ MAXCOST,      30, MAXCOST,      15,       0,      30, MAXCOST},
				{ MAXCOST, MAXCOST, MAXCOST, MAXCOST,      30,       0,       5},
				{ MAXCOST, MAXCOST,       5, MAXCOST, MAXCOST,       5,       0}
		};
		int []previous = dkjkstra.Dijkstra(cost, start, dest, vertexList);
		int pos = previous[dest];
		LinkedBlockingDeque<Integer> path = new LinkedBlockingDeque<Integer>(MAX_VERTICES);		
		while (dest != start) {
			path.addFirst(new Integer(previous[pos]));			
			pos = previous[pos];
		}


		
	}

	

}
