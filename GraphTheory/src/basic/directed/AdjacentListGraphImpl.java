package basic.directed;

import java.util.LinkedList;
import java.util.Scanner;

public class AdjacentListGraphImpl {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("How Many Vertices: ");
		int vertices = sc.nextInt();
		System.out.print("How Many Edges: ");
		int edges = sc.nextInt();
		System.out.println("Edges(start end): ");
		LinkedList<Integer>[] adjList = new LinkedList[vertices + 1];
		for(int i = 1; i <= vertices; i++){
			adjList[i] = new LinkedList<>();
		}
		
		while(edges > 0){
			int start = sc.nextInt();
			int end = sc.nextInt();
			adjList[start].add(end);
			//adjList[end].add(start);
			edges--;
		}
		
		for(int i = 1; i <= vertices; i++){
			LinkedList<Integer> list = adjList[i];
			System.out.print("adjList[" + i + "]-> ");
			for(int val: list){
				System.out.print(val + " ");
			}
			System.out.println();
		}
		sc.close();
	}

}


