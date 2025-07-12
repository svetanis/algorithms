package com.svetanis.algorithms.backtracking;

// 1219. Path with Maximum Gold

public final class PathWithMaxGold {
	// Time Complexity: O(4^(n*m))
	// Space Complexity: O(n * m)

	public int maxGold(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] != 0) {
					boolean[][] visited = new boolean[n][m];
					int gold = dfs(grid, visited, i, j, 0, 0);
					max = Math.max(max, gold);
				}
			}
		}
		return max;
	}

	private int dfs(int[][] grid, boolean[][] visited, int x, int y, int gold, int max) {
		gold += grid[x][y];
		visited[x][y] = true;
		max = Math.max(max, gold);
		// go down (x, y) --> (x + 1, y)
		if (valid(grid, visited, x + 1, y)) {
			max = dfs(grid, visited, x + 1, y, gold, max);
		}
		// go up (x, y) --> (x - 1, y)
		if (valid(grid, visited, x - 1, y)) {
			max = dfs(grid, visited, x - 1, y, gold, max);
		}
		// go right (x, y) --> (x, y + 1)
		if (valid(grid, visited, x, y + 1)) {
			max = dfs(grid, visited, x, y + 1, gold, max);
		}
		// go left (x, y) --> (x, y - 1)
		if (valid(grid, visited, x, y - 1)) {
			max = dfs(grid, visited, x, y - 1, gold, max);
		}
		visited[x][y] = false; // backtrack
		gold -= grid[x][y];
		return max;
	}

	private static boolean valid(int[][] grid, boolean[][] visited, int x, int y) {
		int n = grid.length;
		int m = grid[0].length;
		return x >= 0 && x < n && y >= 0 && y < m && grid[x][y] != 0 && !visited[x][y];
	}

	public static void main(String[] args) {
		PathWithMaxGold pmg = new PathWithMaxGold();
		int[][] g1 = { { 0, 6, 0 }, { 5, 8, 7 }, { 0, 9, 0 } }; // 24
		System.out.println(pmg.maxGold(g1));
		PathWithMaxGold pmg1 = new PathWithMaxGold();
		int[][] g2 = { { 1, 0, 7 }, { 2, 0, 6 }, { 3, 4, 5 }, { 0, 3, 0 }, { 9, 0, 20 } }; // 28
		System.out.println(pmg1.maxGold(g2));
	}
}
