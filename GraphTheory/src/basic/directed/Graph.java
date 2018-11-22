package basic.directed;

public class Graph {
	private final int MAX_NODES = 20;
	private Vertex[] vertexList;
	private int[][] adjMatrix;
	private int nVertex;
	
	public int getnVertex() {
		return nVertex;
	}
	public Graph() {
		vertexList = new Vertex[MAX_NODES];
		adjMatrix = new int[MAX_NODES][MAX_NODES];
		nVertex = 0;
		for(int i = 0; i < MAX_NODES; i++){
			for(int j = 0; j < MAX_NODES; j++){
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addVertex(char label){
		vertexList[nVertex++] = new Vertex(label);
	}
	public int[][] getAdjMatrix() {
		return adjMatrix;
	}
	public void addEdge(int start, int end){
		adjMatrix[start - 1][end - 1] = 1;
		adjMatrix[end - 1][start -1] = 1;
	}
	public void displayVertex(int v){
		System.out.print(vertexList[v].getLabel());
	}
}
