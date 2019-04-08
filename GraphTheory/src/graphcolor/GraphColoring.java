
package graphcolor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Saju
 */
public class GraphColoring {
    
    
    static int nodes;
    static int edges;
    static int colors;
    
    static int colorTrack[];
    
    static int adjMat[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        colors = sc.nextInt();
        
        nodes = sc.nextInt();
        edges = sc.nextInt();
        
        adjMat = new int[nodes + 1][nodes + 1];
        
        colorTrack = new int[nodes + 1];
        
        for(int i = 0; i < edges; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            adjMat[u][v] = 1;
            adjMat[v][u] = 1;
        }
        
//        colorTrack[1] = 1;
        boolean ans = calculate(1);
        System.out.println(ans);
        
        if(ans){
        	for(int i = 1; i <= nodes; i++){
        		System.out.println(i + " -> " + colorTrack[i]);
        	}
        }
        else{
        	System.out.println("-1");
        }
        sc.close();
    }

    private static boolean calculate(int u) {
    
        int j = 0;
        for(int i = 1; i <= colors; i++){
          
            for(j = 1; j <= nodes; j++){
                if(adjMat[u][j] == 1 && colorTrack[j] == i){
                    break;
                }
            }
            if(j > nodes){
                colorTrack[u] = i;
                
                for(j = 1; j <= nodes; j++){
                    if(adjMat[u][j] == 1 && colorTrack[j] == 0 && !calculate(j)){
                        break;
                    }
                    
                }
                
                if(j > nodes){
                	
                    return true;
                }
                 colorTrack[u] = 0;
            }
            
        }
        
        return false;
    
    }
}
