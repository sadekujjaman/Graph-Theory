package maxflow;

/**
 * Sadekujjaman Saju
 * 
 */


import java.util.ArrayList;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
// Maxflow by Ford-Fulkerson
public class MaxFlow {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int nodes = sc.nextInt();
		
		int source = sc.nextInt() - 1;
		int sink = sc.nextInt() - 1;
		int edges = sc.nextInt();
		
		List<Integer>[] adjList = new ArrayList[nodes];
		int[][] capacity = new int[nodes][nodes];
		int[][] flow = new int[nodes][nodes];
		
		
		for(int i = 0; i < nodes; i++){
			adjList[i] = new ArrayList<>();
		}
		
		
		for(int i = 0; i < edges; i++){
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			int cap = sc.nextInt();
			
			adjList[u].add(v);
			adjList[v].add(u);
			
//			int temp = capacity[u][v];
//			int temp1 = capacity[v][u];
//			
//			capacity[u][v] = cap;
//			capacity[u][v] += temp1;
//			
//			capacity[v][u] = cap;
//			capacity[v][u] += temp;
//			
			capacity[u][v] += cap;
			capacity[v][u] += cap;
			
//			capacity[u][v] = cap;
//			capacity[v][u] = cap;
			
			//capacity[v][u] = cap;
			flow[u][v] = 0;
			flow[v][u] = 0;
		}
		
		
//		int result = getAugmentedPath(source, sink, adjList, capacity, flow, parent);
//		System.out.println(result);
//		
//		int start = sink;
//		while(start != -1){
//			System.out.print(start + " ");
//			start = parent[start];
//		}
//		System.out.println();
		
		int result = fordFulkerson(adjList, capacity, flow, source, sink);
		System.out.println(result);
		
		sc.close();
	}

	private static int fordFulkerson(List<Integer>[] adjList, int[][] capacity, int[][] flow, int source, int sink) {
		
		int[] parent = new int[capacity.length];
		int minFlow = getAugmentedPath(source, sink, adjList, capacity, flow, parent);
		
		int maxFlow = 0;
		
		while(minFlow != -1){
			
			
			
			int start = sink;
			int f = Integer.MAX_VALUE;
//			while(parent[start] != -1){
//				int p = parent[start];
//				f = Math.min(f, capacity[p][start] - flow[p][start]);
//				start = parent[start];
//			}
			f = minFlow;
			maxFlow += f;
			start = sink;
			while(parent[start] != - 1){
				//System.out.print(start + " ");
				int p = parent[start];
				flow[p][start] += f;
				start = parent[start];
			}
			//System.out.print(start + "-> " + f );
			//System.out.println();
			
			
			parent = new int[capacity.length];
			minFlow = getAugmentedPath(source, sink, adjList, capacity, flow, parent);
		}
		
		return maxFlow;
	}

	private static int getAugmentedPath(int source, int sink, List<Integer>[] adjList, int[][] capacity, int[][] flow, int[] parent) {
		
		boolean[] visited = new boolean[parent.length];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] = true;
		int minFlow = 99999999;
		parent[source] = -1;
		
		while(!queue.isEmpty()){
			int v = queue.remove();
			
			Iterator<Integer> it = adjList[v].listIterator();
			while(it.hasNext()){
				int adj = it.next();
				if(!visited[adj]){
					int f = capacity[v][adj] - flow[v][adj];
					if(f > 0){
						if(minFlow > f)
							minFlow = f;
						parent[adj] = v;
						queue.add(adj);
						visited[adj] = true;
					}
				}
			}
		}
		if(!visited[sink])
			return -1;
		return minFlow;
	}

}
