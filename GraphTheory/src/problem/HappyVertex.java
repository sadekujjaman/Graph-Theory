package problem;
import java.util.*;

public class HappyVertex {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		HappyVertexGraph graph = new HappyVertexGraph(n);
		for(int i = 0; i < m; i++){
		    int u = sc.nextInt();
		    int v = sc.nextInt();
		    graph.addEdge(u,v);
		}
		graph.search();
        graph.count();
		sc.close();
	}

}

class HappyVertexGraph
{
	private int vertices;
	private LinkedList<Integer> adjList[];
	boolean visited[];
	int parent[] = new int[10000003];
	public HappyVertexGraph(int v) {
		this.vertices = v;
		adjList = new LinkedList[vertices];
		visited = new boolean[vertices];
		Arrays.fill(visited, false);
		Arrays.fill(parent, -1);
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
	
  
    public void dfs(int start){
    	visited[start] = true;
    	Iterator<Integer> it = adjList[start].listIterator();
    	while(it.hasNext()){
    		int v = it.next();
    		if(!visited[v]){
    			parent[v] = start;
    			dfs(v);
    		}
    	}
    }
	
    public void search(){
    	for(int i = 0; i < vertices; i++){
    		if(!visited[i]){
    			parent[i] = - 1;
    			dfs(i);
    		}
    	}
    }
    
    public void count(){
    	int count = 0;
    	for(int i = 0; i < vertices; i++){
    		if(parent[i] != -1){
    			if(parent[parent[i]] == -1){
    				if(adjList[i].size() - 1 > adjList[parent[i]].size()){
    					count++;
    				}
    			}
    			else{
    				if(adjList[i].size() > adjList[parent[i]].size()){
    					count++;
    				}
    			}
    		}
    	}
    	System.out.println(count);
    }
	
	
}
