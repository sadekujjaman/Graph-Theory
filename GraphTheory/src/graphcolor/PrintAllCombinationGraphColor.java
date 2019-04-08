package graphcolor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
*
* @author Sadekujjaman Saju
* 
*/

public class PrintAllCombinationGraphColor {

	static int nodes;
	static int color;
	static int colorTrack[];
	static int res;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		color = sc.nextInt();
		nodes = sc.nextInt();
		int edges = sc.nextInt();
		
		Graph graph = new Graph(nodes);
		for(int i = 0; i < edges; i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.addEdge(u, v);
		}
		colorTrack = new int[nodes + 1];
		res = 0;
		findAllColorCombination(graph, 1);
		
		if(res == 0){
			System.out.println("-1");
		}
		else{
			System.out.println(res);
		}
		
		sc.close();
	}
	
	private static void findAllColorCombination(Graph graph, int u) {
		
		if(u > nodes){
			res++;
			return;
		}
		
		for(int c = 1; c <= color; c++){
			if(isSafe(graph, u, c)){
				colorTrack[u] = c;
				
				findAllColorCombination(graph, u + 1);
				
				colorTrack[u] = 0;
			}
		}
		
	}

	private static boolean isSafe(Graph graph, int u, int color) {
		for(int v : graph.adjList[u]){
			if(colorTrack[v] == color){
				return false;
			}
		}
		return true;
	}

	private static class Graph{
	    int nodes;
		List<Integer> adjList[];
		
		public Graph(int nodes){
			this.nodes = nodes;
			
			adjList = new ArrayList[this.nodes + 1];
			for(int i = 0; i <= this.nodes; i++){
				adjList[i] = new ArrayList<>();
			}
		
		}
		
		public void addEdge(int u, int v){
			adjList[u].add(v);
			adjList[v].add(u);
			
		}
		
	}
	
	private static class Edge{
		
		int source;
		int dest;
		
		public Edge(int source, int dest){
			this.source = source;
			this.dest = dest;
		}
	}

}
