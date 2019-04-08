package hamilton;

import java.util.Scanner;


/*
 *
 * @author Sadekujjaman Saju
 * 
 */
public class HamiltonCycle {
    
    static boolean visited[]; // = new boolean[1000];
    static int node = 4;
    static int matrix[][];// = new int[node + 2][node + 2];
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
//        System.out.println("Number of Node: ");
        node = sc.nextInt();
        visited = new boolean[node + 5];
        matrix = new int[node + 5][node + 5];
//        System.out.println("Number of Edge: ");
        int edge = sc.nextInt();
        
        for(int i = 0; i < edge; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }
//        matrix[1][3] = 1;
//        matrix[3][1] = 1;
//        matrix[1][2] = 1;
//        matrix[2][1] = 1;
//        matrix[3][2] = 1;
//        matrix[2][3] = 1;
//        matrix[2][4] = 1;
//        matrix[4][2] = 1;
//        matrix[1][4] = 1;
//        matrix[4][1] = 1;
        
        
        int ans = checkHamiltonCycle(1, 0);
        System.out.println("Ans: "  + ans);
    }

    private static int checkHamiltonCycle(int i, int depth) {
      
        if(i == 1){
            if(depth == node){
//                System.out.println("1");
                 return 1;
            }
          
        }
         
//        System.out.println(i + " " + depth);
        int ans = 0;
       for(int j = 1; j <= node; j++){
           if(matrix[i][j] == 1){
               if(!visited[j]){
                 visited[j] = true;
                 ans = checkHamiltonCycle(j, depth+1);
                
                 if(ans == 1)
                     return 1;
                 visited[j] = false;
                 
               }
           }
       }
        
        return ans;
    }
    
    
}
