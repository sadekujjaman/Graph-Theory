package tutorial;

import java.util.LinkedList;

public class GFG {
   public static void main(String[] args){
	   Graph graph = new Graph(5);
	   
	   addEdge(graph, 0, 1);
	   addEdge(graph, 0, 2);
	   addEdge(graph, 0, 4);
	   addEdge(graph, 1, 2);
	   addEdge(graph, 1, 3);
	   addEdge(graph, 2, 3);
	   addEdge(graph, 2, 4);
	   addEdge(graph, 3, 4);
	   
	   printGraph(graph);
   }
   
   static void addEdge(Graph graph, int src, int des){
	  // LinkedList<Integer> list[] = graph.getAdjList();
	  // list[src].addFirst(des);
	   graph.getAdjList()[src].addFirst(des);
	   graph.getAdjList()[des].addFirst(src);
   }
   
   static void printGraph(Graph graph){
//	 LinkedList<Integer> lists[] = graph.getAdjList();
//	 int v = 0;
//	 for(LinkedList<Integer> list : lists){
//		System.out.print(v + "-->");
//		 for(int i : list){
//			 System.out.print(i + " ");
//		 }
//		 System.out.println();
//		 v++;
//	 }
	   for(int i = 0; i < graph.getVertex(); i++){
		   System.out.println("Adjacency List of vertex " + i);
		   System.out.print("head");
		   for(Integer in : graph.getAdjList()[i]){
			   System.out.print("->" + in);
		   }
		   System.out.println();
	   }
   }
}
class Graph{
	private int vertex;
	private LinkedList<Integer> adjList[];
	
	public Graph(int vertices) {
		this.vertex = vertices;
		adjList = new LinkedList[vertices];
		
		for(int i = 0; i < vertices; i++){
			adjList[i] = new LinkedList<>();
		}
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public LinkedList<Integer>[] getAdjList() {
		return adjList;
	}

	public void setAdjList(LinkedList<Integer>[] adjList) {
		this.adjList = adjList;
	}
	
	
}