package tutorial;

import java.util.Vector;

public class GraphSTLSet1 {

	public static void main(String[] args) {
		int v = 5;
		Vector<Integer> vector[] = new Vector[v];
		
		for(int i = 0; i < v; i++){
			vector[i] = new Vector<>();
		}
//		   addEdge(vector, 0, 1);
//		   addEdge(vector, 0, 2);
//		   addEdge(vector, 0, 4);
//		   addEdge(vector, 1, 2);
//		   addEdge(vector, 1, 3);
//		   addEdge(vector, 2, 3);
//		   addEdge(vector, 2, 4);
//		   addEdge(vector, 3, 4);
		addEdge(vector, 0, 1);
	    addEdge(vector, 0, 4);
	    addEdge(vector, 1, 2);
	    addEdge(vector, 1, 3);
	    addEdge(vector, 1, 4);
	    addEdge(vector, 2, 3);
	    addEdge(vector, 3, 4);
		   print(vector);
		   DFS(vector, v);
	}
	static void DFS(Vector<Integer> adj[], int V)
	{
	    //vector<bool> visited(V, false);
		Vector<Boolean> visited = new Vector<>(V + 2);
		
		for(int u = 0; u < V; u++){
			visited.addElement(false);
		}
	    for (int u=0; u<V; u++)
	        if (visited.get(u) == false)
	            DFSUtil(u, adj, visited);
	}
	private static void DFSUtil(int u, Vector<Integer>[] adj, Vector<Boolean> visited) {
		visited.set(u, true); 
	    System.out.print(u + " ");
	    		
	    for (int i=0; i<adj[u].size(); i++)
	        if (visited.get(adj[u].get(i)) == false)
	            DFSUtil(adj[u].get(i), adj, visited);
	}
	static void addEdge(Vector<Integer> vector[],int src, int des){
		vector[src].addElement(des);
		vector[des].addElement(src);
	}
	static void print(Vector<Integer> vector[]){
		for(int i = 0; i < vector.length; i++){
			System.out.print(i + "-->");
			for(int v : vector[i]){
				System.out.print(v + " ");
			}
			System.out.println();
		}
	}

}
