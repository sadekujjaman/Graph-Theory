package basic.weighted.path;
import java.util.*;

public class ShrtDjBFS {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		//for(int i = 0; i < test; i++){
		    int n = sc.nextInt();
		    int m = sc.nextInt();
		    GraphDirectdBFS g = new GraphDirectdBFS(n);
		    for(int j = 0; j < m; j++){
		        int u = sc.nextInt();
		        int v = sc.nextInt();
		        g.addEdge(u - 1, v - 1);
		    }
		    int l = g.bfs(0, 1);
		    System.out.println(l);
		
		//}
    sc.close();
	}

}

class GraphDirectdBFS
{
	private int vertices;
	private LinkedList<Integer> adjList[];
	public GraphDirectdBFS(int v) {
		this.vertices = v;
		adjList = new LinkedList[vertices];
		
		for(int i = 0; i < adjList.length; i++){
			adjList[i] = new LinkedList<>();
		}
	}

	public int getVertices() {
		return vertices;
	}

	public void setVertices(int vertices) {
		this.vertices = vertices;
	}

	public LinkedList<Integer>[] getAdjList() {
		return adjList;
	}

	public void setAdjList(LinkedList<Integer>[] adjList) {
		this.adjList = adjList;
	}
	
	public void addEdge(int start, int end){
		adjList[start].add(end);// directed
		adjList[end].add(start);
	}
	
	
public int bfs(int startIndex, int end){
		boolean visited[] = new boolean[vertices];
		
		int weight[] = new int[vertices];
		
		for(int i = 0; i < vertices; i++){
			weight[i] = 99999999;
		}
		
		weight[startIndex] = 0;
		visited[startIndex] = true;
		
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(startIndex);
		while(!queue.isEmpty()){
			int v = queue.poll();
		
			int temp = v;
		
			Iterator<Integer> it = adjList[v].listIterator();
			while(it.hasNext()){
				int n = it.next();
				int adj = n;
				if(!visited[n]){
					visited[n] = true;
					queue.add(n);
					if(weight[adj] > weight[temp] + 3){
						weight[adj] = weight[temp] + 3;
					}
					
				}
			}
		}
		
		return weight[end];
	}
}
