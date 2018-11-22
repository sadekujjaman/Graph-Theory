package basic.weighted.path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShrtBFS1APP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = 3;
		
		GraphBFS graph = new GraphBFS(v);
		graph.addEdge(1, 2);
		
		int start = 1;
		int[] distance = graph.sortestDistances(start);
		
		for(int i = 0; i < v; i++){
			if(i == start)
				continue;
			System.out.print(distance[i] + " ");
		}
		
		sc.close();
	}

}

class GraphBFS {
	private int vertices;
	private LinkedList<Integer> adjList[];
	boolean visited[];
	public GraphBFS(int v) {
		this.vertices = v;
		adjList = new LinkedList[vertices];
		visited = new boolean[vertices];
		
		Arrays.fill(visited, false);
		
		for(int i = 0; i < adjList.length; i++){
			adjList[i] = new LinkedList<>();
		}
	}

	public int getVertices() {
		return vertices;
	}

	public void setVertices(int vertices) {
		this.vertices = vertices;
	}

	public LinkedList<Integer>[] getAdjList() {
		return adjList;
	}

	public void setAdjList(LinkedList<Integer>[] adjList) {
		this.adjList = adjList;
	}
	
	public void addEdge(int start, int end){
		adjList[start].add(end);// directed
		adjList[end].add(start);
	}
	private final int EDGE_LENGTH = 6;
	public int[] sortestDistances(int start){
		int[] distances = new int[vertices];
		Arrays.fill(distances, -1);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		distances[start] = 0;
		while(!queue.isEmpty()){
			int u = queue.poll();
			Iterator<Integer> it = adjList[u].listIterator();
			while(it.hasNext()){
				int v = it.next();
				if(distances[v] == - 1){
				distances[v] = distances[u] + EDGE_LENGTH;
				queue.add(v);
				}
			}
		}
		return distances;
		
	}
}