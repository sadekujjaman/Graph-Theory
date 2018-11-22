package basic.traverse;

public class MSTAPP {

	public static void main(String[] args) {
		GraphMST graph = new GraphMST();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		//graph.addVertex('F');
		graph.addVertex('D');
		graph.addVertex('E');
		//graph.addVertex('G');
		//graph.addVertex('K');
		//graph.addVertex('J');
		
	    graph.addEdges(0, 1); // AB
		graph.addEdges(0, 2); // AC
		graph.addEdges(0, 3); // AD
		graph.addEdges(0, 4); // AE
		graph.addEdges(1, 2); // BC
		graph.addEdges(1, 3); // BD
		graph.addEdges(1, 4); // BE
		graph.addEdges(2, 3); // CD
		graph.addEdges(2, 4); // CE
		graph.addEdges(3, 4); // DE
		/*
		graph.addEdges(0, 1);
		graph.addEdges(0, 3);
		graph.addEdges(0, 2);
		graph.addEdges(1, 2);
		graph.addEdges(1, 6);
		graph.addEdges(2, 3);
		graph.addEdges(3, 4);
		graph.addEdges(4, 2);
		graph.addEdges(5, 2);
		graph.addEdges(5, 4);
		graph.addEdges(5, 8);
		graph.addEdges(6, 2);
		graph.addEdges(6, 5);
		graph.addEdges(7, 6);
		graph.addEdges(7, 5);
		graph.addEdges(8, 4);
		graph.addEdges(8, 7);
		*/
		int adjM[][] = graph.getAdjMatrix();
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				System.out.print(adjM[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("Visits: ");
		graph.mst();
		System.out.println();
		
	}

}
class StackMST{
	private final int SIZE = 20;
	private int[] stack;
	private int top;
	
	public StackMST() {
		stack = new int[SIZE];
		top = -1;
	}
	public void push(int item){
		stack[++top] = item;
	}
	public int pop(){
		return stack[top--];
	}
	public int peek(){
		return stack[top];
	}
	
	public boolean isEmpty(){
		return top == -1;
	}
	public int size(){
		return top;
	}
}

class VertexMST
{
	private char label;
	private boolean wasVisited;
	
	public VertexMST(char label) {
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

class GraphMST
{
	private final int VERTICES = 20;
	private VertexMST[] vertexList;
	private int[][] adjMatrix;
	private int nVertex;
	private StackMST stack;
	public GraphMST() {
		vertexList = new VertexMST[VERTICES];
		adjMatrix = new int[VERTICES][VERTICES];
		nVertex = 0;
		stack = new StackMST();
		for(int i = 0; i < VERTICES; i++){
			for(int j = 0; j < VERTICES; j++){
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addVertex(char label){
		vertexList[nVertex++] = new VertexMST(label);
	}
	
	public void addEdges(int start, int end){
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
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


	public void mst(){
		vertexList[0].setWasVisited(true);
		//displayVertex(0);
		stack.push(0);
		
		while(!stack.isEmpty()){
			int currentVertex = stack.peek();
			int v = getAdjUnvisitedVertex(currentVertex);
			if(v == -1)
				stack.pop();
			else{
				vertexList[v].setWasVisited(true);
				displayVertex(currentVertex);
				displayVertex(v);
				System.out.print("---");
				stack.push(v);
			}
		}
		for(int i = 0; i < nVertex ; i++){
			vertexList[i].setWasVisited(false);
		}
	}
	
	public int getAdjUnvisitedVertex(int v){
		for(int i = 0; i < nVertex; i++){
			if(adjMatrix[v][i] == 1 && !vertexList[i].isWasVisited())
				return i;
		}
		return - 1;
	}
}