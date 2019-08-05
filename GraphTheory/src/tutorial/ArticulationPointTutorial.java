package tutorial;

import java.util.*;

public class ArticulationPointTutorial {

	public static void main(String[] args) {
	
		int v = 8;
		Graph graph = new Graph(v);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(6, 7);
		
		
		graph.bridge();
	}

	static class Graph 
	{ 
	    private int V;  
	    List<Integer>adjList[];
	    int time = 0;
	    static final int NIL = -1;
	    
	    Graph(int v){
	    	this.V = v;
	    	adjList = new ArrayList[v];
	    	for(int i = 0; i < v; i++){
	    		adjList[i] = new ArrayList<Integer>();
	    	}
	    }
	    
	    void addEdge(int u, int v){
	    	adjList[u].add(v);
	    	adjList[v].add(u);
	    }
	 // A recursive function that finds and prints bridges 
	    // using DFS traversal 
	    // u --> The vertex to be visited next 
	    // visited[] --> keeps tract of visited vertices 
	    // disc[] --> Stores discovery times of visited vertices 
	    // parent[] --> Stores parent vertices in DFS tree 
	    void dfs(int u, boolean[] visited, int[] disc, int[] low, int[] parent){
	    	visited[u] = true;
	    	disc[u] = low[u] = ++time;
	    	
	    	for(Integer v : adjList[u]){
	    		if(!visited[v]){
	    			parent[v] = u;
	    			dfs(v, visited, disc, low, parent);
	    			// Check if the subtree rooted with v has a 
	                // connection to one of the ancestors of u 
	    			low[u] = Math.min(low[u], low[v]);
	    			 // If the lowest vertex reachable from subtree 
	                // under v is below u in DFS tree, then u-v is 
	                // a bridge 
	    			if(low[v] > disc[u]){
	    				System.out.println(u + " " + v);
	    			}
	    		}// Update low value of u for parent function calls.
	    		else if(v != parent[u]){
	    			low[u] = Math.min(low[u], disc[v]);
	    		}
	    	}
	    }
	    
	    void bridge(){
	    	boolean[] visited = new boolean[V];
	    	int[] low = new int[V];
	    	int[] parent = new int[V];
	    	int[] disc = new int[V];
	    	
	    	for(int i = 0; i < V; i++){
	    		parent[i] = -1;
	    		
	    	}
	    	
	    	for(int i = 0; i < V; i++){
	    		if(!visited[i]){
	    			dfs(i, visited, disc, low, parent);
	    		}
	    	}
	    }
	  
	}
}
