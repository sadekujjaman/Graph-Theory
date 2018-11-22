package tutorial;

public class ArticulationBEMain {

	public static void main(String[] args) {
		int v = 3;
		ArticulationBE graph = new ArticulationBE(v);
//		graph.addEdge(0, 5);
//		graph.addEdge(0, 1);
//		graph.addEdge(1, 2);
//		graph.addEdge(1, 3);
//		graph.addEdge(2, 3);
//		graph.addEdge(2, 4);
//		graph.addEdge(3, 4);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		
//		graph.addEdge(0, 1);
//		graph.addEdge(0, 2);
//		graph.addEdge(1, 2);
//		graph.addEdge(1, 3);
//		graph.addEdge(3, 4);
//		graph.addEdge(4, 5);
		
//		graph.DFSAPBE(0, 0);
//		graph.countAp();
//		
//		graph.initialize();
		graph.DFSBRBE(0, 0);
		graph.bridgePrint();
	}

}
class ArticulationBE
{
	private int vertices;
	private boolean adjMat[][];
	private boolean[] visited;
	private int low[];
	private int disc[];
	private int parent[];
	private boolean ap[];
	private int bridge[][];
	public ArticulationBE(int vert) {
		vertices = vert;
		adjMat = new boolean[vertices][vertices];
		visited = new boolean[vertices];
		low = new int[vertices];
		disc = new int[vertices];
		parent = new int[vertices];
		ap = new boolean[vertices];
		bridge = new int[vertices][vertices];
		
		for(int i = 0; i < vertices; i++){
			visited[i] = false;
			low[i] = 999999999;
			disc[i] = 0;
			parent[i] = -1; // nil
			ap[i] = false;
		}
		
	}
	public void initialize(){
		for(int i = 0; i < vertices; i++){
			visited[i] = false;
			low[i] = 999999999;
			disc[i] = 0;
			parent[i] = -1; // nil
			ap[i] = false;
		}
	}
	public void addEdge(int start, int end){
			adjMat[start][end] = true;
			adjMat[end][start] = true;
	}
	// dfs for articulation point using back edge
	// Complexity --> O(V+E)
	private int pointCount = 0;
	public void DFSAPBE(int root,int time){
		visited[root] = true;
		disc[root] = low[root] = time + 1;
		int child = 0;
		for(int i = 0; i < vertices; i++){
			if(adjMat[root][i] == true){
				if(visited[i] == false){
					child++;
					parent[i] = root;
					DFSAPBE(i, time + 1);
					low[root] = Math.min(low[root], low[i]);
					if(parent[root]== - 1 && child > 1){
						ap[root] = true;
						pointCount++;
					}
					if(parent[root] != - 1 && low[i] >= disc[root]){
						ap[root] = true;
						pointCount++;
					}
				}
				else if(parent[root] != i){
					low[root] = Math.min(low[root], disc[i]);
				}
			}
		}
	}
	
	public void countAp(){
		System.out.println(pointCount);
		for(int i = 0; i < vertices; i++){
			if(ap[i] == true){
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
	private int brigeCount = 0;
	
	public void DFSBRBE(int root,int time){
		visited[root] = true;
		disc[root] = low[root] = time + 1;
		int child = 0;
		for(int i = 0; i < vertices; i++){
			if(adjMat[root][i] == true){
				if(visited[i] == false){
					child++;
					parent[i] = root;
					DFSAPBE(i, time + 1);
					low[root] = Math.min(low[root], low[i]);
					
					if(low[i] > disc[root]){
						brigeCount++;
						//System.out.println(root + " " + i);
						bridge[root][i] = 5;// bridge
					}
				}
				else if(parent[root] != i){
					low[root] = Math.min(low[root], disc[i]);
				}
			}
		}
	}
	public void bridgePrint(){
		System.out.println(brigeCount);
		for(int i = 0; i < vertices; i++){
			for(int j = 0; j <vertices; j++){
				if(bridge[i][j] == 5){
					System.out.println(i + " " + j);
				}
			}
		}
	}
}