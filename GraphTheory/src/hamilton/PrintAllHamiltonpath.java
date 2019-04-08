package hamilton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintAllHamiltonpath {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int nodes = sc.nextInt();
		int edges = sc.nextInt();
		
		Graph g = new Graph(nodes);
		
		for(int i = 0; i < edges; i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			g.addEdge(u, v);
		}
		
		int start = 1;
		List<Integer>path = new ArrayList<>();
		boolean[] visited = new boolean[nodes + 1];
		path.add(start);
		visited[start] = true;
		printAllPath(g, start, visited, path);
				
		sc.close();
	}

	
	private static void printAllPath( Graph g, int start, boolean[] visited, List<Integer> path) {
		if(path.size() == g.nodes){
			for(Integer e : path){
				System.out.print(e + " ");
			}
			System.out.println();
			return;
		}
		
		for(int v : g.adjList[start]){
			if(!visited[v]){
				visited[v] = true;
				path.add(v);
				
				printAllPath(g, start, visited, path);
				
				visited[v] = false;
				path.remove(path.size() - 1);
			}
		}
		
	}


	private static class Graph{
		List<Integer>adjList[];
		int nodes;
		
		Graph(int nodes){
			this.nodes = nodes;
			adjList = new ArrayList[nodes + 1];
			for(int i = 0; i <= nodes; i++){
				adjList[i] = new ArrayList<>();
			}
		}
		
		public void addEdge(int u, int v){
			adjList[u].add(v);
			adjList[v].add(u);
		}
	}
}
