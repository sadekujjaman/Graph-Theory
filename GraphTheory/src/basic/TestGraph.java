package basic;
/* ===== ===== =====

Theory of Programming

Adjacency List using Collections API
http://theoryofprogramming.com/2014/12/24/graph-theory-basics/
GitHub - https://github.com/VamsiSangam/theoryofprogramming
Code Contributor - Vamsi Sangam

===== ===== ===== */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


class AdjacencyList {
    //private final LinkedList< Map.Entry<Integer, Integer> >[] adjacencyList;
    private final LinkedList< Map<Integer, Integer> >[] adjacencyList;

    // Constructor
    public AdjacencyList(int vertices) {
        adjacencyList = (LinkedList< Map<Integer, Integer> >[]) new LinkedList[vertices];
        for (int i = 0; i < adjacencyList.length; ++i) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Appends a new Edge to the linked list
    public void addEdge(int startVertex, int endVertex, int weight) {
        //adjacencyList[startVertex].add((Map<Integer, Integer>) new HashMap<>().put(endVertex, weight));
		Map<Integer, Integer> map = new HashMap<>();
		map.put(endVertex, weight);
    	adjacencyList[startVertex].add(map);
    }

    // Returns number of vertices
    // Does not change for an object
    public int getNumberOfVertices() {
        return adjacencyList.length;
    }

    // Returns number of outward edges from a vertex
    public int getNumberOfEdgesFromVertex(int startVertex) {
        return adjacencyList[startVertex].size();
    }

    // Returns a copy of the Linked List of outward edges from a vertex
    public LinkedList< Map<Integer, Integer> > getEdgesFromVertex(int startVertex) {
        LinkedList< Map<Integer, Integer> > edgeList
                = (LinkedList< Map<Integer, Integer> >) new LinkedList(adjacencyList[startVertex]);

        return edgeList;
    }

    // Prints the Adjaency List
    public void printAdjacencyList() {
        int i = 0;

        for (LinkedList< Map<Integer, Integer> > list : adjacencyList) {
            System.out.print("adjacencyList[" + (i) + "] -> ");

            for (Map<Integer, Integer> edge : list) {
                Set set = edge.entrySet();
                Iterator it = set.iterator();
                while(it.hasNext()){
                	Map.Entry<Integer, Integer> me = (Entry<Integer, Integer>) it.next();
                	System.out.println(me.getKey() + "(" + me.getValue() + ")");
                }
            }

            ++i;
            System.out.println();
        }
    }

    // Removes an edge and returns true if there
    // was any change in the collection, else false
    public boolean removeEdge(int startVertex, Map<Integer, Integer> edge) {
        return adjacencyList[startVertex - 1].remove(edge);
    }

    public boolean hasEdge(int startVertex, int endVertex, int weight) {
        return adjacencyList[startVertex].contains(new HashMap<>(endVertex, weight));
    }
}

public class TestGraph
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int vertices = s.nextInt();
        int edges = s.nextInt();
        int u, v, weight;

        AdjacencyList adjacencyList = new AdjacencyList(vertices);

        int i = 0;

        while (i < edges) {
            u = s.nextInt();
            v = s.nextInt();
            weight = s.nextInt();

            adjacencyList.addEdge(u, v, weight);
            ++i;
        }

        System.out.println("The Adjacency List -");
        adjacencyList.printAdjacencyList();

        System.out.println("Remove - " + adjacencyList.removeEdge(2, new HashMap<>(3, 2)));

        System.out.println("The Adjacency List -");
        adjacencyList.printAdjacencyList();

        System.out.println(adjacencyList.hasEdge(2, 3, 2));
    }
}