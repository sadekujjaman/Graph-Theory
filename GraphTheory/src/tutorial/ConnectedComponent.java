package tutorial;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class ConnectedComponent {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int v = 6;
		ConnectedComponentGr graph = new ConnectedComponentGr(v);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.connectedComponent();
		sc.close();
	}

}

class ConnectedComponentGr
{
	private int vertices;
	private LinkedList<Integer> adjList[];
	boolean visited[];
	public ConnectedComponentGr(int v) {
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
	

	public void dfs(int start){
		visited[start] = true;
		Iterator<Integer> it = adjList[start].listIterator();
		while(it.hasNext()){
			int v = it.next();
			if(!visited[v]){
				dfs(v);
			}
		}
	}
	public void connectedComponent(){
		int count = 0;
		for(int i = 0; i < vertices; i++){
			if(!visited[i]){
				dfs(i);
				count++;
			}
		}
		System.out.println(count);
	}
}
