package basic.traverse;

public class DFSAPP {

	public static void main(String[] args) {
		GraphDFS graph = new GraphDFS();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('F');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('G');
		graph.addVertex('K');
		graph.addVertex('J');
		
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
		
		int adjM[][] = graph.getAdjMatrix();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(adjM[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("Visits: ");
		graph.dfs();
		System.out.println();
		
	}

}
class Stack{
	private final int SIZE = 20;
	private int[] stack;
	private int top;
	
	public Stack() {
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

class VertexDFS
{
	private char label;
	private boolean wasVisited;
	
	public VertexDFS(char label) {
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

class GraphDFS
{
	private final int VERTICES = 20;
	private VertexDFS[] vertexList;
	private int[][] adjMatrix;
	private int nVertex;
	private Stack stack;
	public GraphDFS() {
		vertexList = new VertexDFS[VERTICES];
		adjMatrix = new int[VERTICES][VERTICES];
		nVertex = 0;
		stack = new Stack();
		for(int i = 0; i < VERTICES; i++){
			for(int j = 0; j < VERTICES; j++){
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addVertex(char label){
		vertexList[nVertex++] = new VertexDFS(label);
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


	public void dfs(){
		vertexList[8].setWasVisited(true);
		displayVertex(8);
		stack.push(8);
		
		while(!stack.isEmpty()){
			int v = getAdjUnvisitedVertex(stack.peek());
			if(v == -1)
				stack.pop();
			else{
				vertexList[v].setWasVisited(true);
				//System.out.print(v + " ");
				displayVertex(v);
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