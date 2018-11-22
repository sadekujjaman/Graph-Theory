package mst.basic;

import java.util.Arrays;
import java.util.Scanner;

public class KruskalMST {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = 4;
		int m = 5;
		KruskalGraph graph = new KruskalGraph(n, m);
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 2, 6);
		graph.addEdge(0, 3, 5);
		graph.addEdge(1, 3, 15);
		graph.addEdge(2, 3, 4);
		
		graph.kruskalMST();
		sc.close();
	}

}

class KruskalGraph{
	
	class Edge implements Comparable<Edge>{
        int src, dest, weight;
        public Edge(){
        	
        }
		public Edge(int src2, int dest2, int weight2) {
			this.src = src2;
			this.dest = dest2;
			this.weight = weight2;
		}
		@Override
		public int compareTo(Edge o) {
		    return	this.weight - o.weight; 
		}
		
	}
	
	class Subset{
		int parent, rank;
	}
	
	int vertices;
	int edges;
	Edge[] edgeArray;
	int edgeCount = 0;
	public KruskalGraph(int n, int m) {
		this.vertices = n;
		this.edges = m;
		edgeArray = new Edge[m];
		for(int i = 0; i < m; i++){
			edgeArray[i] = new Edge();
		}
	}
	
	public void addEdge(int src, int dest, int weight){
		edgeArray[edgeCount++] = new Edge(src, dest, weight);
	}
	
	public int find(Subset[] subsets, int i){
		if(subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);
		return subsets[i].parent;
	}
	
	public void union(Subset[] subsets, int x, int y){
	    int xRoot = find(subsets, x);
	    int yRoot = find(subsets, y);
	    
	    int xRank = subsets[xRoot].rank;
	    int yRank = subsets[yRoot].rank;
	    
	    if(xRank < yRank){
	    	subsets[xRoot].parent = yRoot;
	    }
	    else if(yRank < xRank){
	    	subsets[yRoot].parent = xRoot;
	    }
	    else{
	    	subsets[yRoot].parent = xRoot;
	    	subsets[xRoot].rank++;
	    }
	    
	}
	
	public void kruskalMST(){
		Edge[] result = new Edge[vertices];
		Subset[] subsets = new Subset[vertices];
		int e = 0;
		for(int i = 0; i < vertices; i++){
			result[i] = new Edge();
			subsets[i] = new Subset();
			subsets[i].parent = i;
			subsets[i].rank = 0;
		}
		Arrays.sort(edgeArray);
		
		int i = 0;
		while(e < vertices - 1){
			Edge nextEdge = new Edge();
			nextEdge = edgeArray[i++];
			
			int x = find(subsets, nextEdge.src);
			int y = find(subsets, nextEdge.dest);
			
			if(x != y){
				result[e++] = nextEdge;
				union(subsets, x, y);
			}
		}
		
		int sum = 0;
		for(int j = 0; j < e; j++){
			sum+=result[j].weight;
			System.out.println(result[j].src + "--" + result[j].dest + " -- " + result[j].weight);
		}
		System.out.println(sum);
	}
}
