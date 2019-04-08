package basic.weighted.path;

import java.util.*;


public class DijkstraApp { 

	static long INF = Long.MAX_VALUE;
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		@SuppressWarnings("unchecked")
		ArrayList<Node> adjList[] = new ArrayList[n + 1];
		
//		int weight[][] = new int[n + 1][n + 1];
		
		
		

		for(int i = 0; i <= n; i++){
			adjList[i] = new ArrayList<>();
			
		}
		
		for(int i = 0; i < m; i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			
			adjList[u].add(new Node(v, w));
			adjList[v].add(new Node(u, w));

//			
//			weight[u].add(v, w);
//			weight[v].add(u, w);
//			
		}
		
		
		
		int start = 1; 
		int end = n;
		
		long dis[] = new long[n + 1];
		boolean visited[] = new boolean[n + 1];
		
		Arrays.fill(dis, INF);
		Arrays.fill(visited, false);
		
//		Queue<Integer> queue = new LinkedList<>();
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		
		queue.add(new Node(start, 0));
		visited[start] = true;
		dis[start] = 0;
		int prev[] = new int[n + 1];
		Arrays.fill(prev, 0);
//		prev[start] = -1;
		
		while(!queue.isEmpty()){
			Node top = queue.poll();
			int u = top.v;
			long wi = top.w;
			
			for(Node t : adjList[u]){
				
				if((dis[u] + t.w) < dis[t.v]){
					dis[t.v] = dis[u] + t.w;
				     visited[t.v] = true;
				    queue.add(new Node(t.v, t.w));
					prev[t.v] = u;
				}
			}
			
		}
		if(!visited[n])
			System.out.println("-1");
		else{
			printAns(prev, end);
//			System.out.print(1);
//			int u = n - 1;
//			ArrayList<Integer> list  = new ArrayList<>();
//			while(u > 0){
//				list.add(u);
//				u = prev[u];
//			}
//			
//			Collections.reverse(list);
//			
//			for(Integer a : list){
//				System.out.print(" " + (++a));
//			}
		}
		
//		if(dis[end] == INF){
//			System.out.println(-1);
//		}
//		else{
//			printAns(prev, end);
//		}
		System.out.println();
		sc.close();
		
	}


	private static void printAns(int[] prev, int end) {
		if(prev[end] != 0)
			printAns(prev, prev[end]);
		
		System.out.print(end + " ");
		
	}
	
	private static class Node implements Comparable<Node>{
		int v;
		long w;
		public Node(int v, long w){
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			
			return Long.compare(this.w, o.w);
		}
	}
	
} 
