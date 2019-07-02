package maxflow;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Sadekujjaman Saju
 */
public class MaxFlowClass {

    public static void main(String[] args) {

        int n = 6;
        int[][] graph = new int[n][n];

        int s = 5;
        int t = 4;
        graph[s][0] = 10;
        graph[0][2] = 25;
        graph[2][t] = 10;
        graph[s][1] = 10;
        graph[1][3] = 15;
        graph[3][t] = 10;
        graph[3][0] = 6;

        int ans = calculate(graph, s, t);

        System.out.println("MaxFlow: " + ans);
    }

    private static int calculate(int[][] graph, int source, int dest) {

        int vertices = graph.length;
        int residualGraph[][] = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            System.arraycopy(graph[i], 0, residualGraph[i], 0, vertices);
        }
        int[] parent = new int[vertices];

        int maxFlow = 0;

        while (hasPath(residualGraph, source, dest, parent)) {

            int minFlow = Integer.MAX_VALUE;
            int u = 0;
            for (int v = dest; v != source; v = parent[v]) {
                u = parent[v];
                minFlow = Math.min(minFlow, graph[u][v]);
                System.out.print(v + " " + u + " ");
            }
            System.out.println();

            for (int v = dest; v != source; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= minFlow;
                residualGraph[v][u] += minFlow;

            }

            maxFlow += minFlow;

        }

        return maxFlow;
    }

    private static boolean hasPath(int[][] graph, int source, int dest, int[] parent) {
        int vertices = graph.length;
//        System.out.println(vertices);

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];
        
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] > 0 && !visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                    parent[v] = u;
                }
            }
        }
        return visited[dest];
    }
}
