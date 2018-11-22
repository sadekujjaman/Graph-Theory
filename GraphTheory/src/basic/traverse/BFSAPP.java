package basic.traverse;

public class BFSAPP {

	public static void main(String[] args) {
		GraphBFS graph = new GraphBFS();
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
		graph.bfs();
		System.out.println();
	}

}

class Queue{
	private final int SIZE = 20;
	private int[] queue;
	private int front;
	private int rear;
	
	public Queue() {
		queue = new int[SIZE];
		front = 0;
		rear = - 1;
	}
	public void enqueue(int item){
		if(rear == SIZE - 1)
			rear = - 1;
		queue[++rear] = item;
	}
	
	public int dequeue(){
		int temp =  queue[front++];
		if(front == SIZE)
			front = 0;
		return temp;
	}
	
	public boolean isEmpty(){
		return(rear + 1 == front || front + SIZE - 1 == rear);
	}
}

class VertexBFS
{
	private char label;
	private boolean wasVisited;
	
	public VertexBFS(char label) {
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

class GraphBFS
{
	private final int VERTICES = 20;
	private VertexBFS[] vertexList;
	private int[][] adjMatrix;
	private int nVertex;
	private  Queue queue;
	public GraphBFS() {
		vertexList = new VertexBFS[VERTICES];
		adjMatrix = new int[VERTICES][VERTICES];
		nVertex = 0;
		queue = new Queue();
		for(int i = 0; i < VERTICES; i++){
			for(int j = 0; j < VERTICES; j++){
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addVertex(char label){
		vertexList[nVertex++] = new VertexBFS(label);
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


	public void bfs(){
		vertexList[0].setWasVisited(true);
		displayVertex(0);
		queue.enqueue(0);
		int v2;
		while(!queue.isEmpty()){
			int v1 = queue.dequeue();
			while((v2 = getAdjUnvisitedVertex(v1)) != - 1){
				vertexList[v2].setWasVisited(true);
				//System.out.print(v + " ");
				displayVertex(v2);
				queue.enqueue(v2);
			}
		}
		
		for(int i = 0; i < nVertex; i++){
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
