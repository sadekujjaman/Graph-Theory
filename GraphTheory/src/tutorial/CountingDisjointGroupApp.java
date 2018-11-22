package tutorial;

import java.util.*;
public class CountingDisjointGroupApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // number of nodes
		int q = sc.nextInt(); // number of query
		DisjointGraph dug = new DisjointGraph(n);
		for(int i = 0; i < q; i++){
			int t = sc.nextInt(); // type of operation
			                      // 0 -> union
			                      // 1 -> find
			int x = sc.nextInt(); // two sets
			int y = sc.nextInt();
			switch(t){
			case 0:{
				dug.union(x, y);
			}
			break;
			case 1:{
				if(dug.find(x) == dug.find(y)){
					System.out.println("Yes");
				}
				else{
					System.out.println("No");
				}
			}
			break;
			}
		}
		sc.close();
//		DisjointGraph dg = new DisjointGraph(5);
//		dg.union(1, 2);
//		dg.union(5, 4);
//		dg.union(5, 1);
//		
//	     if(dg.find(1) == dg.find(4)){
//	    	 System.out.println("Friends");
//	     }
//	     else{
//	    	 System.out.println("Not Friends");
//	     }
//	     if(dg.find(1) == dg.find(3)){
//	    	 System.out.println("Friends");
//	     }
//	     else{
//	    	 System.out.println("Not Friends");
//	     }
	}

}

class DisjointGraph{
	int vertice;
	int parent[];
	int rank[];
	
	public DisjointGraph(int n) {
		this.vertice = n + 1;
		parent = new int[this.vertice];
		rank = new int[this.vertice];
		
		for(int i = 1; i < vertice; i++){
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int find(int x){
		if(parent[x] != x){
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	public void union(int x, int y){
		int xRoot = find(x);
		int yRoot = find(y);
		if(xRoot == yRoot)
			return;
		int xRank = rank[xRoot];
		int yRank = rank[yRoot];
		
		if(xRank < yRank){
			parent[xRoot] = yRoot;
		}
		else{
			parent[yRoot] = xRoot;
		}
		if(xRank == yRank)
			rank[xRoot] = rank[xRoot] + 1;
	}
	
	
}

