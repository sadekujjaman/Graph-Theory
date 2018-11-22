package basic.floodFill;

public class FloodFill {

	public static void main(String[] args) {
		
		// for graph traverse
		int n = 4;
		int m = 4;
		boolean visited[][] = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}

		dfs(0, 0, visited, n, m);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		
		
		// for maze solving
		n = 5;
		m = 5;
		int mat[][] = new int[n][m];
		boolean visitedmaze[][] = new boolean[n][m];
		mat[0][4] = 5; // 5 means obstacle(X)
		mat[1][3] = 5;
		mat[2][1] = 5;
		mat[3][0] = 5;
		
		mat[4][1] = 5;
		mat[4][3] = 5;
		
		mat[1][2] = 5;
		boolean ans  = dfs(0, 0, visitedmaze, mat, n, m, 3, 4);
		System.out.println(ans);
	}

	// solving maze(Given a matrix, a source cell, a destination cell,
	// some cells which cannot be visited,
	// and some valid moves, check if the destination cell
	// can be reached from the source cell)

	private static boolean dfs(int x, int y, boolean[][] visited, int mat[][], int n, int m, int dest_x, int dest_y) {
		if (x == dest_x && y == dest_y)
			return true;
		if (x >= n || y >= m)
			return false;
		if (x < 0 || y < 0)
			return false;
		if (mat[x][y] == 5)
			return false;
		if (visited[x][y] == true)
			return false;

		visited[x][y] = true;
		
		if (dfs(x + 1, y, visited, mat, n, m, dest_x, dest_y) == true)
			return true;
		if(dfs(x - 1, y, visited, mat, n, m, dest_x, dest_y) == true)
			return true;
		if (dfs(x, y + 1, visited, mat, n, m, dest_x, dest_y) == true)
			return true;
		if(dfs(x, y - 1, visited, mat, n, m, dest_x, dest_y) == true)
			return true;
		return false;
	}

	// graph traverse
	// complexity ---> O(N * M)
	private static void dfs(int x, int y, boolean[][] visited, int n, int m) {
		if (x >= n || y >= m)
			return;
		if (x < 0 || y < 0)
			return;

		if (visited[x][y] == true)
			return;

		visited[x][y] = true;
		// upper row
		dfs(x - 1, y - 1, visited, n, m);
		dfs(x, y - 1, visited, n, m);
		dfs(x + 1, y - 1, visited, n, m);
		// middle row
		dfs(x - 1, y, visited, n, m);
		dfs(x + 1, y, visited, n, m);
		// down row
		dfs(x - 1, y + 1, visited, n, m);
		dfs(x, y + 1, visited, n, m);
		dfs(x + 1, y + 1, visited, n, m);
	}

}
