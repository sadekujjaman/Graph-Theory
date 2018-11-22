package tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SCCApp {

	public static void main(String[] args) {
		int vert = 15;
		SCCGraph graph = new SCCGraph(vert);
		
//		graph.addEdge(1, 0);
//		graph.addEdge(2, 1);
//		graph.addEdge(2, 3);
//		graph.addEdge(3, 4);
//		
//		graph.addEdge(0, 3);
//		graph.addEdge(0, 2);
//		graph.addEdge(1, 3);
//		graph.addEdge(2, 3);
//		graph.addEdge(3, 4);
//		graph.addEdge(4, 0);
		
		graph.addEdge(15, 14);
		graph.addEdge(13, 8);
		graph.addEdge(1, 10);
		graph.addEdge(6, 5);
		graph.addEdge(1, 7);
		graph.addEdge(8, 2);
		graph.addEdge(10, 11);
		graph.addEdge(7, 3);
		graph.addEdge(4, 12);
		graph.addEdge(4, 5);
		graph.addEdge(9, 6);
		graph.addEdge(6, 3);
		graph.addEdge(4, 13);
		graph.addEdge(6, 4);
		graph.addEdge(5, 8);
		
		graph.dfs(0);
		graph.scc();
		//graph.printStack();
	}

}

class SCCGraph{
	private List<Integer> adjList[];
	private boolean visited[];
	private Stack<Integer> stack;
	private List<Integer> revAdjList[];
	private int vertices;
	public SCCGraph(int vert) {
		this.vertices = vert;
		adjList = new ArrayList[vertices];
		visited = new boolean[vertices];
		revAdjList = new ArrayList[vertices];
		stack = new Stack<>();
		for(int i = 0; i < vertices; i++){
			adjList[i] = new ArrayList<>();
			revAdjList[i] = new ArrayList<>();
			visited[i] = false;
		}
	}
	
	public void addEdge(int start, int end){
		adjList[start - 1].add(end - 1);
		revAdjList[end - 1].add(start - 1);
	}
	
	private int odd = 0;
	private int even = 0;
	
	
	public void scc(){
		odd = 0;
		even = 0;
	    Stack<Integer> revStack = stack;
	    int sccCount = 0;
		System.out.println();
		Arrays.fill(visited, false);
		while(!revStack.isEmpty()){
			int top = stack.pop();
			if(!visited[top]){
				System.out.println();
				count = 0;
				sccCount++;
			    dfsUtil(top, revAdjList);
			    if(count % 2 == 0)
			    	even += count;
			    else
			    	odd += count;
			}
		}
		System.out.println();
		System.out.println("SSC: " + sccCount);
		print();
	}
	
   public void print(){
	   System.out.println((odd - even));
   }
   
   
	public void dfs(int start){
		for(int i = 0; i < vertices; i++){
			if(!visited[i])
				dfsUtil(i, adjList);
		}
	}
	private int count = 0;
	public void dfsUtil(int start, List<Integer>[] adjList2){
		visited[start] = true;
		count++;
		System.out.print(start + " ");
		Iterator<Integer> it = adjList2[start].listIterator();
		while(it.hasNext()){
			int v = it.next();
			if(!visited[v]){
				//visited[v] = true;
				dfsUtil(v, adjList2);
			}
		}
		stack.push(start);
	}

	public void printStack(){
		System.out.println();
		System.out.println(stack.size());
		while(!stack.isEmpty()){
			System.out.print(stack.pop() + 1 + " ");
		}
	}
	
}
