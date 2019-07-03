package basic.weighted.shortestPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class ShortestPathPriorityQ {

	
	public static void main(String[] args) {
	
		int vertices = 6;
		
		Graph graph = new Graph(vertices);
//		graph.addEdge(0, 1, 2);
//		graph.addEdge(0, 2, 3);
//		graph.addEdge(1, 2, 1);
//		graph.addEdge(1, 3, 2);
//		graph.addEdge(2, 3, 4);
//		graph.addEdge(2, 4, 3);
//		graph.addEdge(3, 4, 2);
//		graph.addEdge(3, 5, 1);
//		graph.addEdge(4, 5, 6);
		
		graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
		
		graph.getShortestPath(0);
	
	}

	
	
	static class Graph{
		int vertices;
		List<Edge> adjList[];
		
		Graph(int vertices){
			this.vertices = vertices;
			adjList = new ArrayList[this.vertices];
			for(int i = 0; i < this.vertices; i++){
				adjList[i] = new ArrayList<>();
			}
		}
		
	 void addEdge(int source, int destination, int weight){
			Edge e = new Edge(source, destination, weight);
			
			adjList[source].add(e);
			
			e = new Edge(destination, source, weight);
			adjList[destination].add(e);
		}
	 
	 void getShortestPath(int sourceVertex){
	
		 boolean SPT[] = new boolean[vertices];
		 
		 int distance[] = new int[vertices];
		 
		 PriorityQueue<Pair> pq = new PriorityQueue<>(vertices, new Comparator<Pair>() {

				@Override
				public int compare(Pair p1, Pair p2) {
					
					return p1.distance - p2.distance;
				}
			});
		 
		 for(int i = 0; i < vertices; i++){
			 distance[i] = Integer.MAX_VALUE;
		 }
		 distance[sourceVertex] = 0;
		 
		 Pair p0 = new Pair(distance[sourceVertex], sourceVertex); 
		 pq.offer(p0);
		 
		 while(!pq.isEmpty()){
			 Pair extractedPair = pq.poll();
			 int extractedVertex = extractedPair.vertex;
			 if(SPT[extractedVertex] == false){
				 SPT[extractedVertex] = true;
				 for(Edge edge : adjList[extractedVertex]){
					 int destination = edge.destination;
					 
					 if(SPT[destination] == false){
						 int newDistance = distance[extractedVertex] + edge.weight;
						 
						 if(distance[destination] > newDistance){
							 Pair newPair = new Pair(destination, newDistance);
							 pq.offer(newPair);
							 distance[destination] = newDistance;
						 }
					 }
				 }
			 }
		 }
		 
		 printShortestPath(sourceVertex, distance);
		 
	 }

	private void printShortestPath(int sourceVertex, int[] distance) {
		
		for(int i = 0; i < vertices; i++){
			System.out.println("Source Vertex " + sourceVertex + " to vertex " + i + " distance = " + distance[i]);
		}
	}
	 
		
	}
	
	static class Edge{
		int source;
		int destination;
		int weight;
		
		Edge(int source, int destination, int weight){
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
		
	}
	
	static class Pair{
		int vertex;
		int distance;
		Pair(int vertex, int distance){
			this.vertex = vertex;
			this.distance = distance;
		}
	}
}