package basic.topo;

public class TopoApp {

	public static void main(String[] args) {
		GraphTopo graph = new GraphTopo();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('F');
		graph.addVertex('G');
		
		graph.addEdges(0, 2);
		graph.addEdges(1, 5);
		graph.addEdges(1, 3);
		graph.addEdges(3, 2);
		graph.addEdges(4, 2);
		graph.addEdges(6, 0);
		graph.addEdges(6, 5);
		
		graph.topo();
		
		
	}

}

class VertexTopo
{
	private char label;
	private boolean wasVisited;
	
	public VertexTopo(char label) {
		this.label = label;
		wasVisited = false;
	}
	
	public char getLabel(){
		return this.label;
	}

	public boolean isWasVisited() {
		return wasVisited;
	}

	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}
	
}
class GraphTopo
{
	private final int VERTICES = 20;
	private VertexTopo[] vertexList;
	private int[][] adjMatrix;
	private int nVertex;
	private char sortedArray[];
	public GraphTopo() {
		vertexList = new VertexTopo[VERTICES];
		adjMatrix = new int[VERTICES][VERTICES];
		nVertex = 0;
		sortedArray = new char[VERTICES];
		for(int i = 0; i < VERTICES; i++){
			for(int j = 0; j < VERTICES; j++){
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addVertex(char label){
		vertexList[nVertex++] = new VertexTopo(label);
	}
	
	public void addEdges(int start, int end){
		adjMatrix[start][end] = 1;
		//adjMatrix[end][start] = 1;
	}
	
	public void displayVertex(int v){
		System.out.print(vertexList[v].getLabel() + " ");
	}
	
	public int getNVertex(){
		return nVertex;
	}
	
	public int[][] getAdjMatrix() {
		return adjMatrix;
	}
    public void topo(){
    	int verTex = nVertex;
    	while(nVertex>0){
    		int currentVertex = noSuccessor();
    		if(currentVertex == -1){
    			System.out.println("ERROR! Having A Cycle");
    			break;
    		}
    		else{
    			sortedArray[nVertex - 1] = vertexList[currentVertex].getLabel();
    			deleteVertex(currentVertex);
    		}
    	}
    	
    	System.out.println("Topological Sorted Order: ");
    	for(int i = 0; i < verTex; i++){
    		System.out.print(sortedArray[i] + " ");
    	}
    	System.out.println();
    }
	private void deleteVertex(int currentVertex) {
		// not last vertex
		if(currentVertex != nVertex - 1){
			// delete from vertexList
			for(int j = currentVertex; j < nVertex - 1; j++){
				vertexList[j] = vertexList[j + 1];
			}
			
			// delete row from adjMat
			for(int j = currentVertex; j < nVertex - 1; j++){
				moveRowUp(j, nVertex);
			}
			
			// delete column from adjMat
			for(int j = currentVertex; j < nVertex - 1; j++){
				moveColLeft(j, nVertex);
			}
		}
		nVertex--;
	}

	private void moveColLeft(int j, int nVertex2) {
		for(int row = 0; row < nVertex2; row++){
			adjMatrix[row][j] = adjMatrix[row][j + 1];
		}
	}

	private void moveRowUp(int j, int nVertex2) {
		for(int col = 0; col < nVertex2; col++){
			adjMatrix[j][col] = adjMatrix[j + 1][col];
		}
	}

	public int noSuccessor(){
		boolean isEdge;
		
		for(int i = 0; i < nVertex; i++){
			isEdge = false;
			for(int j = 0; j < nVertex; j++){
				if(adjMatrix[i][j] > 0)
				{
					isEdge = true;
					break;
				}
			}
			if(!isEdge)
				return i;
		}
		return - 1;
	}
	
	public int getAdjUnvisitedVertex(int v){
		for(int i = 0; i < nVertex; i++){
			if(adjMatrix[v][i] == 1 && !vertexList[i].isWasVisited())
				return i;
		}
		return - 1;
	}
}
