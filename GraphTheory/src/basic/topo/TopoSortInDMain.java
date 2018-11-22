package basic.topo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TopoSortInDMain {

	public static void main(String[] args) {
		int vert = 5;
		TopoSortInDGraph graph = new TopoSortInDGraph(vert);
//		graph.addEdge(0, 2);
//		graph.addEdge(4, 2);
//		graph.addEdge(6, 5);
//		graph.addEdge(1, 5);
//		graph.addEdge(1, 3);
//		graph.addEdge(6, 0);
//		graph.addEdge(3, 2);
		
//		graph.addEdge(0, 1);
//		graph.addEdge(0, 2);
//		graph.addEdge(1, 2);
//		graph.addEdge(1, 3);
//		graph.addEdge(2, 3);
//		graph.addEdge(2, 4);
		
/*		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(2, 4);
		graph.addEdge(3, 1);
		graph.addEdge(4, 1);*/
		
		Scanner sc = new Scanner(System.in);
		
		
		graph.topologicalSort();
	}

}

class TopoSortInDGraph{
	private List<Integer> adjList[];
	private List<Integer> topoSort;
	private boolean visited[];
	private int inDegree[];
	private int vertices;
	public TopoSortInDGraph(int vert) {
		this.vertices = vert;
		adjList = new ArrayList[vertices];
		topoSort = new ArrayList<>();
		visited = new boolean[vertices];
		inDegree = new int[vertices];
		for(int i = 0; i < vertices; i++){
			adjList[i] = new ArrayList<>();
			visited[i] = false;
		}
	}
	
	public void addEdge(int start, int end){
		adjList[start].add(end);
		inDegree[end]++;
	}
	
	public void topologicalSort(){
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < vertices; i++){
			System.out.println("Indegee of " + i + " is " + inDegree[i]);
			if(inDegree[i] == 0){
				queue.add(i);
				visited[i] = true;
			}
		}
		if(queue.isEmpty()){
			System.out.println("Graph has Cycle");
			return;
		}
		
		while(!queue.isEmpty()){
			int v = queue.remove();
			//System.out.println(v);
			topoSort.add(v);
			Iterator<Integer> it = adjList[v].listIterator();
			while(it.hasNext())
			{
				int u = it.next();
				if(!visited[u]){
					inDegree[u]--;
					if(inDegree[u] == 0)
					{
						visited[u] = true;
						queue.add(u);
					}
				}
			}
		}
		
		// print topological sorted order
		System.out.print("Topological Sort: ");
		for(int i = 0; i < vertices; i++){
			System.out.print(topoSort.get(i) + " ");
		}
	}
}
