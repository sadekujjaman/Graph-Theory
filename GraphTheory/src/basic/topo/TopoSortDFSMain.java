package basic.topo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class TopoSortDFSMain {

	public static void main(String[] args) {
		int vert = 7;
		TopoSortDFSGraph graph = new TopoSortDFSGraph(vert);
		graph.addEdge(0, 2);
		graph.addEdge(4, 2);
		graph.addEdge(6, 5);
		graph.addEdge(1, 5);
		graph.addEdge(1, 3);
		graph.addEdge(6, 0);
		graph.addEdge(3, 2);
		
		graph.topologicalSort();
	}

}

class TopoSortDFSGraph{
	private List<Integer> adjList[];
	private Stack<Integer> stack;
	private boolean visited[];
	private int vertices;
	public TopoSortDFSGraph(int vert) {
		this.vertices = vert;
		adjList = new ArrayList[vertices];
		stack = new Stack<>();
		visited = new boolean[vertices];
		for(int i = 0; i < vertices; i++){
			adjList[i] = new ArrayList<>();
			visited[i] = false;
		}
	}
	
	public void addEdge(int start, int end){
		adjList[start].add(end);
	}
	
	public void topologicalSort(){
		for(int i = vertices - 1; i >= 0 ; i--){
			if(!visited[i]){
				dfs(i);
			}
		}
		System.out.println("Topological Sort: ");
		for(int i = 0;  i < vertices; i++){
			System.out.print(stack.pop() + " ");
		}
	}
	
	public void dfs(int start){
		visited[start] = true;
		Integer[] arr = adjList[start].toArray(new Integer[adjList[start].size()]);
		Arrays.sort(arr);
//		Iterator<Integer> it = adjList[start].listIterator();
//		while(it.hasNext()){
//			int u = it.next();
//			if(!visited[u]){
//				dfs(u);
//			}
//		}
		// for lexicographically smaller sorted order
		for(int i = arr.length - 1; i >= 0; i--){
			int u = arr[i];
			if(!visited[u])
				dfs(u);
		}
		stack.push(start);
	}
}
