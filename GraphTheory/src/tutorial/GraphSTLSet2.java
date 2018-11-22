package tutorial;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class GraphSTLSet2 {

	public static void main(String[] args) {
		int vertices = 5;
		Vector<Map<Integer, Integer>> adjList[] = new Vector[vertices];
		for(int i = 0; i < vertices; i++){
			adjList[i] = new Vector<>();
		}
		
		addEdge(adjList, 0, 1, 10);
	    addEdge(adjList, 0, 4, 20);
	    addEdge(adjList, 1, 2, 30);
	    addEdge(adjList, 1, 3, 40);
	    addEdge(adjList, 1, 4, 50);
	    addEdge(adjList, 2, 3, 60);
	    addEdge(adjList, 3, 4, 70);
		

		printGraph(adjList);
	}

	private static void printGraph(Vector<Map<Integer, Integer>>[] adjList) {
		
		for(int i = 0; i < adjList.length; i++){
			System.out.println("Node " + i + " makes an edge with ");
			for(Map<Integer, Integer> map : adjList[i]){
			//Map<Integer, Integer> map = adjList[i];
			Set<Integer> set = map.keySet();
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){
				int v = it.next();
				int w = map.get(v);
				System.out.println("\t Node " + v + " with edge weight " + w);
			}
			
				
		}
	}
	}
// 0 1 10
// 1 0 10
// 1 2 30
/*
 * adjList[0] = map->{1,10}
 * adjList[1] = map->{0,10}
 * adjList[1] = map->{2,30}
 * */
	private static void addEdge(Vector<Map<Integer, Integer>>[] adjList, int start, int end, int weight) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(end, weight);
		adjList[start].addElement(map);
		
		Map<Integer, Integer> map1 = new HashMap<>();
		map1.put(start, weight);
		adjList[end].addElement(map1);
	}
	
}
