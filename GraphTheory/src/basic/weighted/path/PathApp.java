package basic.weighted.path;

public class PathApp {

	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A'); // 0 (start)
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addEdge(0, 1, 50); // AB 50
		theGraph.addEdge(0, 3, 80); // AD 80
		theGraph.addEdge(1, 2, 60); // BC 60
		theGraph.addEdge(1, 3, 90); // BD 90
		theGraph.addEdge(2, 4, 40); // CE 40
		theGraph.addEdge(3, 2, 20); // DC 20
		theGraph.addEdge(3, 4, 70); // DE 70
		theGraph.addEdge(4, 1, 50); // EB 50
		System.out.println("Shortest paths");
		theGraph.path(); // shortest paths
		System.out.println();
	}

}

class DistPar{
	private int distance;
	private int parentVertex;
	
	public DistPar(int parentVertex, int distance) {
		this.parentVertex = parentVertex;
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getParentVertex() {
		return parentVertex;
	}

	public void setParentVertex(int parentVertex) {
		this.parentVertex = parentVertex;
	}
	
}

class Vertex{
	private char label;
	private boolean isInTree;
	
	public Vertex(char label) {
		this.label = label;
	}

	public char getLabel() {
		return label;
	}

	public void setLabel(char label) {
		this.label = label;
	}

	public boolean isInTree() {
		return isInTree;
	}

	public void setInTree(boolean isInTree) {
		this.isInTree = isInTree;
	}
	
	
}

class Graph{
	
	private final int MAX_SIZE = 20;
	private final int INFINITY = 999999999;
	private Vertex vertexList[];
	private int adjMat[][];
	private int nVertex;
	private int nTree;
	private DistPar sPath[];
	private int currentVertex;
	private int startToCurrent;
	
	public Graph() {
		
		vertexList = new Vertex[MAX_SIZE];
		adjMat = new int[MAX_SIZE][MAX_SIZE];
		nVertex = 0;
		nTree = 0;
		
		for(int j = 0; j < MAX_SIZE; j++){
			for(int i = 0; i < MAX_SIZE; i++){
				adjMat[j][i] = INFINITY;
			}
		}
		
		sPath = new DistPar[MAX_SIZE];
	}
	
	public void addVertex(char label){
		vertexList[nVertex++] = new Vertex(label);
	}
	
	public void addEdge(int start, int end, int weight){
		adjMat[start][end] = weight;
	}
	
	public void path(){
		int startTree = 0;
		vertexList[startTree].setInTree(true);
		nTree = 1;
		
		for(int i = 0; i < nVertex; i++){
			int tempDist = adjMat[startTree][i];
			sPath[i] = new DistPar(startTree, tempDist);
		}
		
		while(nTree < nVertex){
			int indexMin = getMin();
			int minDist = sPath[indexMin].getDistance();
			
			if(minDist == INFINITY){
				System.out.println("There are unreachable vertices");
				break;
			}
			else{
				currentVertex = indexMin;
				startToCurrent = sPath[indexMin].getDistance();
			}
			
			vertexList[currentVertex].setInTree(true);
			nTree++;
			adjust_sPath();
		}
		
		displayPaths();
		
		nTree = 0;
		for(int j = 0; j < nVertex; j++){
			vertexList[j].setInTree(false);
		}
	}
	
	public int getMin(){
		int minDist = INFINITY;
		int indexMin = 0;
		for(int i = 1; i < nVertex; i++){
			if(!vertexList[i].isInTree() && sPath[i].getDistance() < minDist){
				minDist = sPath[i].getDistance();
				indexMin = i;
			}
		}
		return indexMin;
	}
	
	public void adjust_sPath(){
		
		int column = 1;
		while(column < nVertex){
			
			if(vertexList[column].isInTree()){
				column++;
				continue;
			}
			
			int currenToFring = adjMat[currentVertex][column];
			
			int startToFringe = startToCurrent + currenToFring;
			int sPathDist = sPath[column].getDistance();
			
			if(startToFringe < sPathDist){
				sPath[column].setParentVertex(currentVertex);
				sPath[column].setDistance(startToFringe);
			}
			column++;
		}
	}
	
	public void displayPaths(){
		for(int j = 0; j < nVertex; j++){
			System.out.print(vertexList[j].getLabel() + "=");
			if(sPath[j].getDistance() == INFINITY){
				System.out.print("INF");
			}
			else{
				System.out.println(sPath[j].getDistance());
			}
			char parent = vertexList[sPath[j].getParentVertex()].getLabel();
			System.out.println("(" + parent + ")");
		}
		System.out.println("");
	}
}
