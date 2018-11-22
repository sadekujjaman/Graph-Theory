package basic.weighted.path;

import java.util.*;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		AdjacencyDijkstrGraphT graph = new AdjacencyDijkstrGraphT(n);

		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			graph.addEdge(u - 1, v - 1, w);
		}
		int arr[] = graph.shortPath(0, n - 1);

		for (int i = 1; i < n; i++) {
			System.out.print(arr[i] + " ");
		}

	}

}

class AdjacencyDijkstrGraphT {
	private int vertices;
	private LinkedList<Integer> adjList[];
	private int weightAll[][];
	private boolean visited[];

	public AdjacencyDijkstrGraphT(int v) {
		this.vertices = v;
		adjList = new LinkedList[vertices];
		visited = new boolean[vertices];
		weightAll = new int[vertices][vertices];
		for (int i = 0; i < v; i++) {
			adjList[i] = new LinkedList<>();
			visited[i] = false;
		}
	}

	public void addEdge(int start, int end, int weight) {
		Map<Integer, Integer> map = new HashMap<>();
		adjList[start].add(end);
        weightAll[start][end] = weight;
	}

	public int[] shortPath(int start, int end) {

		int weight[] = new int[vertices];
		for (int i = 0; i < vertices; i++) {
			weight[i] = Integer.MAX_VALUE;
		}

		weight[start] = 0;
		;
		Queue<CM> pQueue = new PriorityQueue<>();
		CM c = new CM(0,start);
		pQueue.add(c);
		while (!pQueue.isEmpty()) {
			CM cc = pQueue.remove();
			int key = cc.weight;
			int x = cc.v;
				if (visited[x])
					continue;
				visited[x] = true;
				LinkedList<Integer> adj = adjList[x];
				Iterator<Integer> it = adj.listIterator();
				while (it.hasNext()) {
					int v = it.next();
					int weig = weightAll[x][v];
					// System.out.print(v + " ");
					if (weight[v] > weight[x] + weig) {
						weight[v] = weight[x] + weig;
						
						pQueue.add(new CM(weight[v],v));
					}
				
				}
		}
		return weight;
	}
    class CM implements Comparable<CM>{
        int v;
        int weight;
		public CM(int i, int start) {
			this.v = start;
			this.weight = i;
		}
		@Override
		public int compareTo(CM arg0) {
			if(this.weight > arg0.weight)
				return 1;
			else if(this.weight < arg0.weight)
				return -1;
			else 
				return 0;
		}
    	
    }
    
}
