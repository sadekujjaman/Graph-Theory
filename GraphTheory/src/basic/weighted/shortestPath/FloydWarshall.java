
package basic.weighted.shortestPath;

import java.util.Arrays;
import java.util.Scanner;

public class FloydWarshall {

	static final int INF = 99999;
	
	static int vertices;
	static int edges;
	static int W[][];
	static int Q[][];
	
	
/*
	 
4 7
1 1 7
1 2 5
2 1 7
2 4 2
3 2 3
4 1 4
4 3 1


	 
*/
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		vertices = sc.nextInt();
		edges = sc.nextInt();
		
		W = new int[vertices + 1][vertices + 1]; // 1 based graph
		Q = new int[vertices + 1][vertices + 1];
		
		for(int i = 0; i <= vertices; i++){
			Arrays.fill(W[i], 0);
			Arrays.fill(Q[i], INF);
		}
		for(int i = 0; i < edges; i++){
		     int u = sc.nextInt();
		     int v = sc.nextInt();
		     int w = sc.nextInt();
		     
		     W[u][v] = w;
		     Q[u][v] = w;
		}
		
		
	    // calculate floyd
		printPathMatrix();
		calculateFloydWarshall();
		System.out.println();
		printPathMatrix();
		
		sc.close();
	
	}
	
	private static void printPathMatrix(){
		for(int i = 1; i <= vertices; i++){
			for(int j = 1; j <= vertices; j++){
				System.out.print(Q[i][j] + "\t\t");
			}
			System.out.println();
		}
	}
	

	private static void calculateFloydWarshall(){
		
		System.out.println();
		for(int k = 1; k <= vertices; k++){
			for(int i = 1; i <= vertices; i++){
				for(int j = 1; j <= vertices; j++){
//					Q[i][j] = Math.min(Q[i][j], Q[i][k] + Q[k][j]);
					Q[i][j] = Math.min(Q[i][j], Math.max(Q[i][k], Q[k][j]));
				}
			}
//			System.out.println("Q " + k);
//			printPathMatrix();
			
		}
		
	}
}
