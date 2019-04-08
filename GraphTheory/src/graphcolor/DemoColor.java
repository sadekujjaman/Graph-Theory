package graphcolor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// data structure to store graph edges
class Edge
{
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

// class to represent a graph object
class Graph
{
	// An array of Lists to represent adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N)
	{
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (int i = 0; i < edges.size(); i++)
		{
			int src = edges.get(i).source;
			int dest = edges.get(i).dest;

			adjList.get(src).add(dest);
			adjList.get(dest).add(src);
		}
	}
}

public class DemoColor
{
	// string array to store colors (10-colorable graph)
	private static String COLORS[] = {"", "BLUE", "GREEN", "RED"};
//			, "YELLOW",
//					"ORANGE", "PINK", "BLACK", "BROWN", "WHITE", "PURPLE"};

	// Function to check if it is safe to assign color c to vertex v
	private static boolean isSafe(Graph graph, int[] color, int v, int c)
	{
		// check color of every adjacent vertex of v
		for (int u : graph.adjList.get(v))
			if (color[u] == c)
				return false;

		return true;
	}

	public static void kColorable(Graph g, int[] color, int k, int v, int N)
	{
		// if all colors are assigned, print the solution
		if (v == N)
		{
			for (v = 0; v < N; v++)
				System.out.printf("%-8s" , color[v]); 

			System.out.println();

			return;
		}

		// try all possible combinations of available colors
		for (int c = 1; c <= k; c++)
		{
			// if it is safe to assign color c to vertex v
			if (isSafe(g, color, v, c))
			{
				// assign color c to vertex v
				color[v] = c;

				// recurse for next vertex
				kColorable(g, color, k, v + 1, N);

				// backtrack
				color[v] = 0;
			}
		}
	}
	public static void main(String[] args)
	{
		// vector of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(
				new Edge(0, 1), new Edge(1, 2), new Edge(2, 0)
//				new Edge(0, 1), new Edge(0, 4),
//				new Edge(0, 5), new Edge(4, 5),
//				new Edge(1, 4), new Edge(1, 3),
//				new Edge(2, 3), new Edge(2, 4)
		);

		// Set number of vertices in the graph
		final int N = 3;

		// create a graph from edges
		Graph g = new Graph(edges, N);

		int k = 2;

		int[] color = new int[N];

		// print all k-colorable configurations of the graph
		kColorable(g, color, k, 0, N);
	}
}