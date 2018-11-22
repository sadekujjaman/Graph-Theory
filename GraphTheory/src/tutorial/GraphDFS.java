package tutorial;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphDFS {

	public static void main(String[] args) {
		GraphDirectdDFS g = new GraphDirectdDFS(4);
		 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Depth First Traversal "+
                           "(starting from vertex 2)");
 
        g.dfs(2);
	}

}

class GraphDirectdDFS
{
	private int vertices;
	private LinkedList<Integer> adjList[];
	
	public GraphDirectdDFS(int v) {
		this.vertices = v;
		adjList = new LinkedList[vertices];
		
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
	}
	public void dfs(int startIndex){
		boolean visited[] = new boolean[vertices];
		//dfsUtil(startIndex, visited);
		
		// modified version
		for(int i = 0; i < vertices; i++){
			if(!visited[i])
				dfsUtil(i, visited);
		}
	}

	private void dfsUtil(int startIndex, boolean[] visited) {
		visited[startIndex] = true;
		System.out.print(startIndex + " ");
		
		Iterator<Integer> it = adjList[startIndex].listIterator();
		while(it.hasNext()){
			int nextIndex = it.next();
			if(!visited[nextIndex])
				dfsUtil(nextIndex, visited);
				
		}
	}
}
