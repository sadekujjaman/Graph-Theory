package maxflow;

import java.util.*;

public class Dinics {

  static class Edge {
    int t, rev, cap, f;

    public Edge(int t, int rev, int cap) {
      this.t = t;
      this.rev = rev;
      this.cap = cap;
    }
  }

  public static List<Edge>[] createGraph(int nodes) {
    List<Edge>[] adjList = new List[nodes];
    for (int i = 0; i < nodes; i++)
    	adjList[i] = new ArrayList<>();
    return adjList;
  }

  public static void addEdge(List<Edge>[] adjList, int s, int t, int cap) {
	  adjList[s].add(new Edge(t, adjList[t].size(), cap));
	  adjList[t].add(new Edge(s, adjList[s].size() - 1, 0));
  }

  static boolean dinicBfs(List<Edge>[] adjList, int src, int dest, int[] dist) {
    Arrays.fill(dist, -1);
    dist[src] = 0;
    int[] Q = new int[adjList.length];
    int sizeQ = 0;
    Q[sizeQ++] = src;
    for (int i = 0; i < sizeQ; i++) {
      int u = Q[i];
      for (Edge e : adjList[u]) {
        if (dist[e.t] < 0 && e.f < e.cap) {
          dist[e.t] = dist[u] + 1;
          Q[sizeQ++] = e.t;
        }
      }
    }
    return dist[dest] >= 0;
  }

  static int dinicDfs(List<Edge>[] adjList, int[] ptr, int[] dist, int dest, int u, int f) {
    if (u == dest)
      return f;
    for (; ptr[u] < adjList[u].size(); ++ptr[u]) {
      Edge e = adjList[u].get(ptr[u]);
      if (dist[e.t] == dist[u] + 1 && e.f < e.cap) {
        int df = dinicDfs(adjList, ptr, dist, dest, e.t, Math.min(f, e.cap - e.f));
        if (df > 0) {
          e.f += df;
          adjList[e.t].get(e.rev).f -= df;
          return df;
        }
      }
    }
    return 0;
  }

  public static int maxFlow(List<Edge>[] adjList, int src, int dest) {
    int flow = 0;
    int[] dist = new int[adjList.length];
    while (dinicBfs(adjList, src, dest, dist)) {
      int[] ptr = new int[adjList.length];
      while (true) {
        int df = dinicDfs(adjList, ptr, dist, dest, src, Integer.MAX_VALUE);
        if (df == 0)
          break;
        flow += df;
      }
    }
    return flow;
  }

  // Usage example
  public static void main(String[] args) {
//    List<Edge>[] adjList = createGraph(3);
//    addEdge(adjList, 0, 1, 3);
//    addEdge(adjList, 0, 2, 2);
//    addEdge(adjList, 1, 2, 2);
//    System.out.println(maxFlow(adjList, 0, 2));
	  
	  List<Edge>[] adjList = createGraph(4);
	    addEdge(adjList, 0, 1, 20);
	    addEdge(adjList, 0, 2, 10);
	    addEdge(adjList, 1, 2, 5);
	    addEdge(adjList, 1, 3, 10);
	    addEdge(adjList, 2, 3, 20);
	    
	    System.out.println(maxFlow(adjList, 0, 3));
	  
  }
}