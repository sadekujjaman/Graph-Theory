package mst.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestPrim {

	public static void main(String[] args) {
		int v = 9;
		TestPrim prim = new TestPrim();
		Graph graph = prim.createGraph(v);
		
		prim.addEdge(graph, 0, 1, 4);
        prim.addEdge(graph, 0, 7, 8);
        prim.addEdge(graph, 1, 2, 8);
        prim.addEdge(graph, 1, 7, 11);
        prim.addEdge(graph, 2, 3, 7);
        prim.addEdge(graph, 2, 8, 2);
        prim.addEdge(graph, 2, 5, 4);
        prim.addEdge(graph, 3, 4, 9);
        prim.addEdge(graph, 3, 5, 14);
        prim.addEdge(graph, 4, 5, 10);
        prim.addEdge(graph, 5, 6, 2);
        prim.addEdge(graph, 6, 7, 1);
        prim.addEdge(graph, 6, 8, 6);
        prim.addEdge(graph, 7, 8, 7);
        
		prim.printGraph(graph);
		System.out.println();
		prim.getPrimMST(graph);
	}

	private class Node implements Comparable<Node> {
		int vertice;
		int key; // weight;

		public Node(int vertice, int key) {
			this.vertice = vertice;
			this.key = key;
		}

		@Override
		public int compareTo(Node o) {
			return this.key - o.key;
		}

	}

	private class AdjList {
		ArrayList<Node> nodes;
	}

	private class Graph {
		int V;
		AdjList[] adjLists;
	}

	private Graph createGraph(int v) {
		Graph graph = new Graph();
		graph.V = v;
		graph.adjLists = new AdjList[v];
		for (int i = 0; i < v; i++) {
			AdjList adj = new AdjList();
			adj.nodes = new ArrayList<>();
			graph.adjLists[i] = adj;
		}
		return graph;
	}

	private void addEdge(Graph graph,int src, int dest, int key) {// key->weight
		Node srcNode = new Node(src, key);
		Node destNode = new Node(dest, key);
		graph.adjLists[src].nodes.add(destNode);
		graph.adjLists[dest].nodes.add(srcNode);
		
	}
	
	private void printGraph(Graph graph){
		for(int i = 0; i < graph.V; i++){
			System.out.print(i + " -> ");
			for(Node node: graph.adjLists[i].nodes){
				System.out.print(node.vertice + " ");
			}
			System.out.println();
		}
	}
	
	private void getPrimMST(Graph graph){
		Node[] keys = new Node[graph.V];
		int[] parent = new int[graph.V];
		boolean[] addedMst = new boolean[graph.V];
		for(int i = 0; i < graph.V; i++){
			keys[i] = new Node(i, Integer.MAX_VALUE);
			parent[i] = -1;
			addedMst[i] = false;
		}
		
		keys[0].key = 0;
		Queue<Node> pQueue = new PriorityQueue<>();
		pQueue.addAll(Arrays.asList(keys));
		while(pQueue.size() > 1){
			Node u = pQueue.remove();
			addedMst[u.vertice] = true;
			
			for(Node node : graph.adjLists[u.vertice].nodes){
				if(!addedMst[node.vertice] && node.key < keys[node.vertice].key){
					pQueue.remove(keys[node.vertice]); // remove that node from queue
					
					keys[node.vertice].key = node.key;
					parent[node.vertice] = u.vertice;
					
					pQueue.add(keys[node.vertice]);  // add back
					 //remove add can me made single function by using a visited flag 
                    //remove_add() in O(lg(n))
				}
			}
		}
		
		printMST(keys, parent, graph);
	}

	private void printMST(Node[] keys, int[] parent, Graph graph) {
		int sum = 0;
		for(int i = 1; i < graph.V; i++){
			sum += keys[i].key;
			System.out.println(parent[keys[i].vertice] + "---" + keys[i].vertice + "  " + keys[i].key);
		}
		System.out.println("------");
		System.out.println("      " + sum);
	}
}
