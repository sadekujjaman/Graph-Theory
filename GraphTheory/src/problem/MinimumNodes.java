package problem;
import java.util.*;

public class MinimumNodes {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		GraphDirectdBFS graph = new GraphDirectdBFS(n);
		long arr[] = new long[n];
		for(int i = 0; i < n; i++){
			arr[i] = sc.nextLong();
		}
		graph.setWeight(arr);
		for(int i = 0; i < n - 1; i++){
		    int u = sc.nextInt();
		    int v = sc.nextInt();
		    graph.addEdge(u - 1,v - 1);
		}
		for(int i = 0; i < q; i++){
			graph.setVisited(arr);
			int x = sc.nextInt();
			long k = sc.nextLong();
			graph.setK(k);
		graph.levelCount(x - 1);
		graph.dfs(x - 1);
		graph.search();
		}
		
//		GraphDirectdBFS graph = new GraphDirectdBFS(4);
//		int arr[] = {2,3,4,5};
//		graph.setWeight(arr);
//		graph.addEdge(0, 1);
//		graph.addEdge(0, 3);
//		graph.addEdge(1, 2);
//		graph.levelCount(1);
//		graph.dfs(1);
//		graph.search();
		sc.close();
	}

}

class GraphDirectdBFS
{
	private int vertices;
	private LinkedList<Integer> adjList[];
	boolean visited[];
	long weight[];
	int level[];
	int parent[] = new int[10000003];
	public GraphDirectdBFS(int v) {
		this.vertices = v;
		adjList = new LinkedList[vertices];
		visited = new boolean[vertices];
		weight = new long[vertices];
		level = new int[vertices];
		Arrays.fill(visited, false);
		Arrays.fill(level, 0);
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
	public void setWeight(long[] arr){
		for(int i = 0; i < arr.length; i++){
			weight[i] = arr[i];
		}
		
	}
    Stack<Integer> stack = new Stack<>();
    long nodeCount = -1;
    long min = Long.MAX_VALUE;
    long k = 0;
    public void setK(long k){
    	this.k = k;
    }
    public void dfs(int start){
    	
    	visited[start] = true;
    	
    	stack.push(start);
    	while(!stack.isEmpty()){
    		int v = stack.pop();
    		//System.out.print(v + " ");
    		Iterator<Integer> it = adjList[v].listIterator();
    		while(it.hasNext()){
    			int adj = it.next();
    			if(!visited[adj]){
    				visited[adj] = true;
    				weight[adj] = weight[adj] + weight[v];
    				if(weight[adj] >= k && weight[v] < k){
    					nodeCount = level[adj] + 1;
    					if(min > nodeCount)
    						min = nodeCount;
    					break;
    				}
    				else if(weight[v] >= k){
    					nodeCount = level[v] + 1;
    					if(min > nodeCount)
    					  min = nodeCount;
    					break;
    				}
    				
    				dfs(adj);
    			}
    		}
    	}
    	
    }
	public void setVisited(long arr[] ){
		Arrays.fill(visited, false);
		nodeCount = - 1;
		min = Long.MAX_VALUE;
		k = 0;
		Arrays.fill(level, 0);
		for(int i = 0; i < arr.length; i++){
			weight[i] = arr[i];
		}
		//System.out.println(weight[3]);
		//Arrays.fill(a, val);
	}
    public void search(){
//    	for(int i = 0; i < vertices; i++){
//    		if(!visited[i]){
//    			parent[i] = - 1;
//    			dfs(i);
//    		}
//    	}
    	//System.out.println();
    	if(min == Long.MAX_VALUE)
    		min = -1;
    	System.out.println(min);
    }
    
	public void levelCount(int start){
		//System.out.println(level.length);
		boolean vis[] = new boolean[vertices];
		Arrays.fill(vis, false);
		level[start] = 0;
		//System.out.println(level[0]);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		vis[start] = true;
		while(!queue.isEmpty()){
			int v = queue.poll();
			Iterator<Integer> it = adjList[v].listIterator();
			while(it.hasNext()){
				int adj = it.next();
				if(!vis[adj]){
					queue.add(adj);
					level[adj] = level[v] + 1;
					vis[adj] = true;
				}
			}
		}
		
// 		for(int i = 0; i < vertices; i++){
// 			System.out.print(level[i] + " ");
// 		}
	}
    
}
