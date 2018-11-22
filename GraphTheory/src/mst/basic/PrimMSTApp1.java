package mst.basic;

import java.util.Arrays;

public class PrimMSTApp1 {

	public static void main(String[] args) {
		int n = 9;
		PrimMST1 mst = new PrimMST1(n);
		mst.addEdge(0, 1, 4);
		mst.addEdge(0, 7, 8);
		mst.addEdge(1, 2, 8);
		mst.addEdge(1, 7, 11);
		mst.addEdge(2, 3, 7);
		mst.addEdge(2, 5, 4);
		mst.addEdge(2, 8, 2);
		mst.addEdge(3, 4, 9);
		mst.addEdge(3, 5, 14);
		mst.addEdge(4, 5, 10);
		mst.addEdge(5,6,2);
		mst.addEdge(6, 8, 6);
		mst.addEdge(6, 7, 1);
		mst.addEdge(7, 8, 7);
		mst.mst();
		
		
	}

}

class PrimMST1{
	private int vertices;
	private int adjMat[][];
	private boolean addedMst[];
	private int key[];
	int parent[];
	public PrimMST1(int n) {
		this.vertices = n;
		adjMat = new int[n][n];
		addedMst = new boolean[n];
		key = new int[vertices];
		parent = new int[vertices];
		
		for(int i = 0; i < vertices; i++){
			key[i] = Integer.MAX_VALUE;
			addedMst[i] = false;
		}
	}
	
	 void addEdge(int u, int v, int w){
		adjMat[u][v] = w;
		adjMat[v][u] = w;
	}
	
	private int minKey(){
		int min = Integer.MAX_VALUE;
		int min_Index = -1;
		for(int i = 0; i < vertices; i++){
			if(!addedMst[i] && min > key[i]){
				min = key[i];
				min_Index = i;
			}
		}
		return min_Index;
	}
	
	 void mst(){
		key[0] = 0;
		parent[0] = -1;
		int sum = 0;
		for(int count = 0; count < vertices - 1; count++){
			int u = minKey();
			
			addedMst[u] = true;
			
			for(int v = 0; v < vertices; v++){
				if(!addedMst[v] && adjMat[u][v] != 0 && adjMat[u][v] <= key[v]){
					parent[v] = u;
					key[v] = adjMat[u][v];
					
				}
			}
		}
		System.out.println(":::MST:::");
		for(int i = 1; i < vertices; i++){
			System.out.println(parent[i] + " " + i + " " + adjMat[i][parent[i]]);
			sum+= adjMat[i][parent[i]];
		}
		System.out.println(sum);
	}
}
