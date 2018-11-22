package disjointset;

public class DisjointSet1App {

	public static void main(String[] args) {
		int v = 3;
		int e = 3;
		DisjointSet1Graph graph = new DisjointSet1Graph(v, e);
		// add edge 0---1
		//graph.edges[0].src = 0;
		graph.edges[0].dest = 1;
		// add edge 1---2
		//graph.edges[1].src = 1;
		//graph.edges[1].dest = 2;
		// add edge 0---2
		graph.edges[2].src = 0;
		//graph.edges[2].dest = 2;
		
		if(graph.isCycle(graph)){
			System.out.println("Has Cycle");
		}
		else{
			System.out.println("Has Not Cycle");
		}
	}

}

class DisjointSet1Graph{
	private int vertice;
	private int edge;
	Edge edges[];
	
	class Edge{
		int src;
		int dest;
	};
	
	public DisjointSet1Graph(int v, int e) {
		vertice = v;
		edge = e;
		edges = new Edge[e];
		
		for(int i = 0; i < e; i++){
			edges[i] = new Edge();
		}
		
	}
	
	public int find(int parent[], int i){
		if(parent[i] == -1)
			return i;
		return find(parent, parent[i]);
	}
	
	public int[] union(int parent[], int x, int y){
		int xSet = find(parent, x);
		int ySet = find(parent, y);
		parent[xSet] = ySet;
		return parent;
	}
	public boolean isCycle(DisjointSet1Graph graph){
		// allocate memory for creating v subsets
		int parent[] = new int[graph.vertice];
		// initialize all subsets as single element sets
		for(int i = 0; i < parent.length; i++){
			parent[i] = -1;
		}
		// Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
		for(int i = 0; i < graph.edge; i++){
			int x = graph.find(parent, graph.edges[i].src);
			int y = graph.find(parent, graph.edges[i].dest);
			if(x == y)
				return true;// has cycle
			parent = graph.union(parent, x, y);
		}
		return false;
	}
}
