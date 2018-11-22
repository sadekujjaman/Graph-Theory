package tutorial;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class ArticulationBFMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = 6;
		ArticulationBF graph = new ArticulationBF(v);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		
//		graph.addEdge(0, 5);
//		graph.addEdge(0, 1);
//		graph.addEdge(1, 3);
//		graph.addEdge(1, 2);
//		graph.addEdge(2, 3);
//		graph.addEdge(2, 4);
//		graph.addEdge(3, 4);
		
		graph.articulationPoint();
		sc.close();
	}

}
class ArticulationBF
{
	private int vertices;
	private int adjMat[][];
	boolean visited[];
	public ArticulationBF(int v) {
		this.vertices = v;
		adjMat = new int[vertices][vertices];
		visited = new boolean[vertices];
		Arrays.fill(visited, false);
		
	}

	public int getVertices() {
		return vertices;
	}

	public void setVertices(int vertices) {
		this.vertices = vertices;
	}

	
	public void addEdge(int start, int end){
		adjMat[start][end] = 1;// directed
		adjMat[end][start] = 1;
	}
	

	public void dfs(int start){
		visited[start] = true;
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		while(!stack.isEmpty()){
			int v = getAdjUnvisitedVertex(stack.peek());
			if(v == -1)
				stack.pop();
			else{
				visited[v] = true;
				stack.push(v);
			}
		}
	}
	public int getAdjUnvisitedVertex(int v){
		for(int i = 0; i < vertices; i++){
			if(adjMat[v][i] == 1 && !visited[i])
				return i;
		}
		return - 1;
	}
	
	// complexity --> O(V*(V+E))
	public void articulationPoint(){
		int init = 0;
		for(int i = 0; i < vertices; i++){
			visited[i] = false;
		}
		
		for(int i = 0; i < vertices; i++){
			if(!visited[i])
			{
				dfs(i);
				init++;
			}
		}
		System.out.println(init);
		int pointCount = 0;
		for(int i = 0; i < vertices; i++){
			int[] copy = new int[vertices];
			for(int j = 0; j < vertices; j++){
				visited[j] = false;
				copy[j] = adjMat[i][j];
				adjMat[i][j] = adjMat[j][i] = 0;
			}
			int nVal = 0;
			for(int j = 0; j < vertices; j++){
				if(!visited[j] && j != i){
					dfs(j);
					nVal++;
				}
			}
			if(nVal > init){
				pointCount++;
			}
			for(int j = 0; j < vertices; j++){
				adjMat[i][j] = adjMat[j][i] =  copy[j];
			}
		}
		System.out.println(pointCount);
//		int count = 0;
//		for(int i = 0; i < vertices; i++){
//			if(!visited[i]){
//				dfs(i);
//				count++;
//			}
//		}
//		System.out.println(count);
		
	}
}
