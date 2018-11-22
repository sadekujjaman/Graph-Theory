package basic.weighted.path;
import java.util.*;

public class PathMatrix {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	   
	    PathGraph graph = new PathGraph(5);
	    // v = 4
//	    graph.addEdge(1, 2, 1);
//	    graph.addEdge(2, 3, 1);
//	    graph.addEdge(2, 4, 1);
//	    graph.addEdge(3, 4, 1);
 		//int ans = graph.path(1, 4);
		//System.out.println(ans);
 		
// 		graph.addEdge(1, 2, 4);
//	    graph.addEdge(1, 4, 1);
//	    graph.addEdge(2, 3, 3);
//	    graph.addEdge(2, 5, 2);
//	    graph.path(1, 5);
	    
	    graph.addEdge(1, 2, 5);
	    graph.addEdge(1, 3, 2);
	    graph.addEdge(3, 4, 1);
	    graph.addEdge(1, 4, 6);
	    graph.addEdge(3, 5, 5);
	    
	    graph.path(1, 5);
	    
		sc.close();
	}

}

class PathGraph
{
	private int vertices;
	private int adjMat[][];
	private int weights[][];
	int totalWeight = 0;
	// 5 --> 0 1 2 3 4
	// 6 --> 1 2 3 4 5
	public PathGraph(int vertices) {
		this.vertices = vertices + 1;
		adjMat = new int[this.vertices][this.vertices];
		weights = new int[this.vertices][this.vertices];
	}
	
	public void addEdge(int start, int end, int weight){
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
		weights[start][end] = weight;
		weights[end][start] = weight;
		totalWeight += weight;
	}
	
	public int path(int s, int e){
		int infinity = 9999999;
		int q[][] = new int[vertices][vertices];
 		for(int i = 1; i < vertices; i++){
			for(int j = 1; j < vertices; j++){
				if(weights[i][j] == 0)
					q[i][j] = infinity;
				else
					q[i][j] = weights[i][j];
			}
		}
 		
 		for(int k = 1; k < vertices; k++){
 			for(int i = 1; i < vertices; i++){
 				for(int j = 1; j < vertices; j++){
 					q[i][j] = Math.min(q[i][j], (q[i][k] + q[k][j]));
 				}
 			}
 		}
 		int max = -1;
  		for(int i = 1; i <vertices; i++){
  			for(int j = 1; j < vertices; j++){
  				System.out.print(q[i][j] + "\t");
  				if(max < q[i][j])
  					max = q[i][j];
  			}
  			System.out.println();
  		}
// 		int cost = 0;
// 		if(totalWeight<100)
// 			cost = 0;
// 		else if(totalWeight > 10000)
// 			cost = 10000;
// 		else if(totalWeight > 1000)
// 			cost = 1000;
// 		else if(totalWeight > 100)
// 			cost = 100;
// 		System.out.print(cost + " ");
//        System.out.println(max);
        return q[s][e];
 		
	}
}
