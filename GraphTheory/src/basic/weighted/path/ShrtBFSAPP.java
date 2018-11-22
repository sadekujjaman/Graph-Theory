package basic.weighted.path;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShrtBFSAPP {

	public static void main(String[] args) {
		int v = 10;
		ShrtBFSGraph graph = new ShrtBFSGraph(v);
		graph.addEdge(0, 8);
		graph.addEdge(8, 2);
		graph.addEdge(8, 9);
		graph.addEdge(2, 1);
		graph.addEdge(2, 4);
		graph.addEdge(4, 3);
		graph.addEdge(3, 1);
		graph.addEdge(1, 9);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		graph.addEdge(1, 7);
		graph.addEdge(3, 5);
		
		graph.path(2, 6);
	}

}

class ShrtBFSGraph{
	private int vertices;
	private LinkedList<Integer> adjList[];
	
	public ShrtBFSGraph(int v) {
		vertices = v;
		adjList = new LinkedList[vertices];
		
		for(int i = 0; i < vertices; i++){
			adjList[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int start, int end){
		adjList[start].add(end);
		adjList[end].add(start);
	}
	
	public void path(int start, int end){
		Queue<Integer> queue = new LinkedList<>();
		int[] prev = new int[vertices];
		boolean[] flag = new boolean[vertices];
		Arrays.fill(prev, -1);
		Arrays.fill(flag, false);
		flag[start] = true;
		queue.add(start);
		while(!queue.isEmpty()){
			int v = queue.poll();
			Iterator<Integer> it = adjList[v].listIterator();
			while(it.hasNext()){
				int adj = it.next();
				if(!flag[adj]){
					flag[adj] = true;
					prev[adj] = v;
					queue.add(adj);
				}
			}
		}
		Stack<Integer> stack = new Stack<>();
		int currentV = end;
		while(prev[currentV] != - 1){
			stack.add(currentV);
			currentV = prev[currentV];
		}
		stack.add(start);
	   System.out.println(stack.size());
	   while(!stack.isEmpty()){
		   System.out.print(stack.pop() + " ");
	   }
	}
}