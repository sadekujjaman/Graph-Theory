package basic.directed;

import java.util.Scanner;

public class GraphTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Graph graph = new Graph();
		int vertices = 5;
		int ch = 65;
		for(int i = 0; i < vertices; i++){
			char label = (char) ch++;
			graph.addVertex(label);
		}
		int edges = sc.nextInt();
		while(edges > 0){
			int start = sc.nextInt();
			int end = sc.nextInt();
			graph.addEdge(start, end);
			edges--;
		}
		
		int[][] adjMatrix = graph.getAdjMatrix();
		System.out.print(" --->");
		for(int i = 0; i < vertices; i++){
			graph.displayVertex(i);
			System.out.print(" ");
		}
		System.out.println();
		for(int i = 0; i < vertices; i++){
			graph.displayVertex(i);
			System.out.print("--->");
			for(int j = 0; j < vertices; j++){
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}

}
