package tutorial;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphBFS {

	public static void main(String[] args) {
		GraphDirectdBFS g = new GraphDirectdBFS(11);
		 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        g.addEdge(3, 7);
        g.addEdge(3, 8);
        g.addEdge(4, 9);
        g.addEdge(4, 10);
        System.out.println("Following is Beadth First Traversal "+
                           "(starting from vertex 2)");
 
        g.bfs(0);
	}

}

class GraphDirectdBFS
{
	private int vertices;
	private LinkedList<Integer> adjList[];
	
	public GraphDirectdBFS(int v) {
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
	
	public void bfs(int startIndex){
		boolean visited[] = new boolean[vertices];
		int level[] = new int[vertices];
		
		visited[startIndex] = true;
		level[startIndex] = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(startIndex);
		while(!queue.isEmpty()){
			int v = queue.poll();
			System.out.print(v + " ");
			
			Iterator<Integer> it = adjList[v].listIterator();
			while(it.hasNext()){
				int n = it.next();
				if(!visited[n]){
					visited[n] = true;
					queue.add(n);
					level[n] = level[v] + 1;
				}
			}
		}
		System.out.println();
		for(int i = 0; i < vertices; i++){
			System.out.print(level[i] + " ");
		}
		System.out.println();
		// modified version
//		for(int i = 0; i < vertices; i++){
//			if(!visited[i])
//				dfsUtil(i, visited);
//		}
	}
 
}
