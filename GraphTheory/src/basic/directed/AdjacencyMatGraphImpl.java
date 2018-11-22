package basic.directed;

import java.util.Scanner;

public class AdjacencyMatGraphImpl {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("How Many Vertices: ");
		int vertices = sc.nextInt();
		System.out.print("How Many Edges: ");
		int edges = sc.nextInt();
		
		int adjacency[][] = new int[vertices + 1][vertices + 1];
		System.out.println("Edges(start end): ");
		while(edges > 0){
			int start = sc.nextInt();
			int end = sc.nextInt();
			adjacency[start][end] = 1;
			//adjacency[end][start] = 1;
			edges--;
		}
		System.out.println("Adjacency Matrix: ");
		for(int i = 1; i <= vertices; i++){
			for(int j = 1; j <= vertices; j++){
				System.out.print(adjacency[i][j] + " ");
			}
			System.out.println();
		}
		sc.close();
	}

}
