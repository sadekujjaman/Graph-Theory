package tutorial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MaximumFlowFFKMain {

	public static void main(String[] args) {
		MaximumFlowFFK ffk = new MaximumFlowFFK();
		int[][] capacity = {
				{0, 3, 0, 3, 0, 0, 0},
                {0, 0, 4, 0, 0, 0, 0},
                {3, 0, 0, 1, 2, 0, 0},
                {0, 0, 0, 0, 2, 6, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 0, 0, 0, 0}};
       System.out.println("Maximum flow is: " + ffk.maxFlow(capacity, 0, 6));
	}
	public int maximumFlowFFK(int[][] capacity, int source, int sink){
		int[][] residualCapacity = new int[capacity.length][capacity[0].length];
		Map<Integer, Integer> parent = new HashMap<>();
		List<List<Integer>> augmentPaths = new ArrayList<>();
		
		int maximumFlow = 0;
		
		while(BFSFFK(residualCapacity, parent, source, sink)){
			List<Integer> augmentPath = new ArrayList<>();
		    int flow = Integer.MAX_VALUE;
		    int v = sink;
		    while(v != source){
		    	int u = parent.get(v);
		    	augmentPath.add(v);
		    	if(flow > residualCapacity[u][v]){
		    		flow = residualCapacity[u][v];
		    	}
		    	v = u;
		    }
		    augmentPath.add(source);
		    Collections.reverse(augmentPath);
		    augmentPaths.add(augmentPath);
		    
		    maximumFlow += flow;
		    
		    v = sink;
		    while(v != source){
		    	int u = parent.get(v);
		    	residualCapacity[u][v] -= flow;
		    	residualCapacity[v][u] += flow;
		    	v = u;
		    }
		}
		printAugmentedPaths(augmentPaths);
		return maximumFlow;
	}
	
	private void printAugmentedPaths(List<List<Integer>> augmentPaths) {
		System.out.println("Augmented paths: ");
		augmentPaths.forEach(path->{
			path.forEach(u ->{
				System.out.print(u + " ");
			});
			System.out.println();
		});
		
		}

	public boolean BFSFFK(int[][] residualCapacity, Map<Integer, Integer> parent,
			int source, int sink){
		boolean foundAugmentPath = false;
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		queue.add(source);
		visited.add(source);
		while(!queue.isEmpty()){
			int u = queue.remove();
			for(int v = 0; v < residualCapacity.length; v++){
				if(!visited.contains(v) && residualCapacity[u][v] > 0){
					visited.add(v);
					parent.put(v, u);
					queue.add(v);
					if(v == sink){
						foundAugmentPath = true;
						break;
					}
				}
			}
		}
		return foundAugmentPath;
	}

}
