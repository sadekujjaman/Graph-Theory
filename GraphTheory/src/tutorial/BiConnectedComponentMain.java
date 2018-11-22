package tutorial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BiConnectedComponentMain {

	public static void main(String[] args) {
		int vert = 4;
		BiConnectedComponent graph = new BiConnectedComponent(vert);
		
//		graph.addEdge(0, 3);
//		graph.addEdge(3, 2);
//		graph.addEdge(2, 5);
//		graph.addEdge(2, 4);
//		graph.addEdge(5, 4);
//		graph.addEdge(1, 4);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
//		graph.addEdge(3, 4);
//		graph.addEdge(4, 5);
//		graph.addEdge(5, 1);
		
		graph.biConnected();
		
		graph.print();
	}

}
class Edge{
	int start;
	int end;
	public Edge(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

class BiConnectedComponent
{
	private int vertices;
	private boolean adjMat[][];
	private boolean[] visited;
	private int low[];
	private int disc[];
	private int parent[];
	private boolean ap[];
	private int bridge[][];
	Stack<Edge> stack = new Stack<>();
	List<Edge> edges = new ArrayList<>();
	public BiConnectedComponent(int vert) {
		vertices = vert;
		adjMat = new boolean[vertices][vertices];
		visited = new boolean[vertices];
		low = new int[vertices];
		disc = new int[vertices];
		parent = new int[vertices];
		ap = new boolean[vertices];
		bridge = new int[vertices][vertices];
		
		for(int i = 0; i < vertices; i++){
			visited[i] = false;
			low[i] = 999999999;
			disc[i] = 0;
			parent[i] = -1; // nil
			ap[i] = false;
		}
		
	}
	public void initialize(){
		for(int i = 0; i < vertices; i++){
			visited[i] = false;
			low[i] = 999999999;
			disc[i] = 0;
			parent[i] = -1; // nil
			ap[i] = false;
		}
	}
	public void addEdge(int start, int end){
			adjMat[start][end] = true;
			adjMat[end][start] = true;
			Edge e = new Edge(start, end);
			edges.add(e);
	}
	// dfs for articulation point using back edge
	// Complexity --> O(V+E)
	private int odd = 0; // counter for component of odd vertices 
	private int even = 0; // counter for component of even vertices 
	public void DFSBIBE(int root,int time){
		visited[root] = true;
		disc[root] = low[root] = time + 1;
		int child = 0;
		for(int i = 0; i < vertices; i++){
			if(adjMat[root][i] == true){
				if(visited[i] == false){
					child++;
					parent[i] = root;
					Edge e = new Edge(root, i);
					stack.push(e);
					DFSBIBE(i, time + 1);
					low[root] = Math.min(low[root], low[i]);
					if(parent[root]== - 1 && child > 1){
						Set<Integer> set = new HashSet<>();
						System.out.println("____");
						while((stack.peek().start != root && stack.peek().end != i))
						{
							Edge ee = stack.pop();
							System.out.println(ee.start + " " + ee.end);
							set.add(ee.start);
							set.add(ee.end);
						}
						Edge ee = stack.pop();
						System.out.println(ee.start + " " + ee.end);
						set.add(ee.start);
						set.add(ee.end);
						if(set.size() % 2 == 0)
							even++;
						else
							odd++;
						System.out.println("______");
					}
					if(parent[root] != - 1 && low[i] >= disc[root]){
						System.out.println("____");
						Set<Integer> set = new HashSet<>();
						while((stack.peek().start != root && stack.peek().end != i))
						{
							Edge ee = stack.pop();
							System.out.println(ee.start + " " + ee.end);
							set.add(ee.start);
							set.add(ee.end);
						}
						Edge ee = stack.pop();
						System.out.println(ee.start + " " + ee.end);
						set.add(ee.start);
						set.add(ee.end);
						if(set.size() % 2 == 0)
							even++;
						else
							odd++;
						System.out.println("______");
					}
				}
				else if(parent[root] != i && disc[i] < low[root]){
					low[root] =  disc[i];
					Edge e = new Edge(root, i);
					stack.push(e);
				}
			}
		}
	}
	
	public void biConnected(){
		for(int i = 0; i < vertices; i++){
			if(!visited[i]){
				DFSBIBE(i, 0);
				Set<Integer> set = new HashSet<>();
				while(!stack.isEmpty()){
					Edge e = stack.peek();
					System.out.println(e.start + " " + e.end);
					set.add(e.start);
					set.add(e.end);
					stack.pop();
				}
				if(set.size() % 2 == 0)
					even++;
				else
					odd++;
			}
		}
	}
	
	public void print(){
		System.out.println(">>>");
		
		System.out.println(odd + " " + even);
	}
	
}
