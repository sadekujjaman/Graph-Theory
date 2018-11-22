package basic.weighted.path;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdjacencyDijkstrApp {

	public static void main(String[] args) {
		int v = 5;
		AdjacencyDijkstrGraph graph = new AdjacencyDijkstrGraph(v);
		// v = 6;
//		graph.addEdge(0, 1, 4);
//		graph.addEdge(0, 3, 2);
//		graph.addEdge(1, 2, 3);
//		graph.addEdge(1, 4, 3);
//		graph.addEdge(2, 5, 2);
//		graph.addEdge(3, 4, 3);
//		graph.addEdge(4, 5, 1);
		
//		graph.shortPath(0, 5);
//		 v = 8;
//		graph.addEdge(0, 1, 4);
//		graph.addEdge(0, 2, 3);
//		graph.addEdge(1, 2, 2);
//		graph.addEdge(1, 3, 5);
//		graph.addEdge(2, 3, 3);
//		graph.addEdge(2, 4, 6);
//		graph.addEdge(3, 5, 5);
//		graph.addEdge(3, 4, 1);
//		graph.addEdge(4, 6, 5);
//		graph.addEdge(5, 6, 2);
//		graph.addEdge(5, 7, 7);
//		graph.addEdge(6, 7, 4);
		
//		graph.shortPath(0, 7);
		
		graph.addEdge(0, 1, 5);
	    graph.addEdge(0, 2, 2);
	    graph.addEdge(2, 3, 1);
	    graph.addEdge(0, 3, 6);
	    graph.addEdge(2, 4, 5);
	    long start = System.nanoTime();
	    int arr[] = graph.shortPath(0, 4);
	    long end = System.nanoTime();
	    System.out.println((end - start) );
	    for(int i = 1; i < v; i++){
	    	System.out.print(arr[i] + " ");
	    }
	    //graph.shortPath(0, 4);
		
		
	}

}
class AdjacencyDijkstrGraph{
	private int vertices;
	//private int adjList[][];
	private LinkedList<Integer> adjList[];
	private int weightAll[][];
	List<Integer> setAll[];
	public AdjacencyDijkstrGraph(int v) {
		this.vertices = v;
		adjList = new LinkedList[vertices];
		setAll = new ArrayList[v];
		weightAll = new int[vertices][vertices];
		for(int i = 0; i < v; i++){
			setAll[i] = new ArrayList<>();
			adjList[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int start, int end, int weight){
		adjList[start].add(end);
		adjList[end].add(start);
		weightAll[start][end] = weight;
		weightAll[end][start] = weight;
	}
	
	public int[] shortPath(int start, int end){
		//int arr[] = new int[vertices];
		Set<Integer> path = new HashSet<>();
		int weight[] = new int[vertices];
		for(int i = 0; i < vertices; i++){
			weight[i] = Integer.MAX_VALUE;
		}
		
		weight[start] = 0;
		path.add(start);
		//System.out.println(start);
		//setAll[0].add(0);
		while(!path.contains(end)){
			//System.out.println(path.contains(end) + " " + start);
			LinkedList<Integer> adj = adjList[start];
			Iterator<Integer> it = adj.listIterator();
			while(it.hasNext()){
				int v = it.next();
				//System.out.print(v + " ");
				if(weight[v] > weight[start] + weightAll[start][v]){
					weight[v] = weight[start] + weightAll[start][v];
					//setAll[v] = setAll[start];
					//setAll[v].add(start);
					setAll[v].clear();
					setAll[v].addAll(setAll[start]);
					setAll[v].add(start);
					System.out.print(v + ": ");
					for(int a : setAll[v]){
						System.out.print(a + " ");
					}
					System.out.println();
			}
			}
			//System.out.println();
			int index = minIndex(weight, path);
			System.out.println("min Index: " + index);
			//int temp = start;
			start = index;
			path.add(start);
			//setAll[start] = setAll[temp];
		}
		
		for(int a : path){
			System.out.print(a + " ");
		}
		
		System.out.println();
		int sum = 0;
		setAll[end].add(end);
		for(int a : setAll[end]){
			System.out.print(a + " ");
		}
		System.out.println();
		for(int i = 0; i < setAll[end].size() - 1; i++){
//			System.out.println(setAll[end].get(i + 1) + " " +
//		weightAll[setAll[end].get(i)][setAll[end].get(i + 1)]);
//			
			sum += weightAll[setAll[end].get(i)][setAll[end].get(i+1)];
		}
		System.out.println("Length " + sum);
		
		return weight;
	}

	private int minIndex(int[] weight, Set<Integer> path) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i = 0; i < weight.length; i++){
			if(!path.contains(i) && weight[i] <= min){
				min = weight[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
}
