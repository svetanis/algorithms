package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 63. Unique Paths II

public final class RobotOnGridObstacleTopDown {
	// Time Complexity: O(2^(n + m))
	// Space Complexity: O(n + m)

	public static int countUniquePaths(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		Integer[][] dp = new Integer[n][m];
		return dfs(grid, 0, 0, dp);
	}

	private static int dfs(int[][] grid, int row, int col, Integer[][] dp) {
		int n = grid.length;
		int m = grid[0].length;
		if (row >= n || col >= m || grid[row][col] == 1) {
			return 0;
		}
		if (row == n - 1 && col == m - 1) {
			return 1;
		}
		if (dp[row][col] != null) {
			return dp[row][col];
		}
		int right = dfs(grid, row, col + 1, dp);
		int down = dfs(grid, row + 1, col, dp);
		return dp[row][col] = right + down;
	}

	public static void main(String[] args) {
		// n rows and m columns
		int[][] g1 = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		int[][] g2 = { { 0, 1 }, { 0, 0 } };
		int[][] g3 = { { 1 } };
		int[][] g4 = { { 1, 0 } };
		System.out.println(countUniquePaths(g1)); // 2
		System.out.println(countUniquePaths(g2)); // 1
		System.out.println(countUniquePaths(g3)); // 0
		System.out.println(countUniquePaths(g4)); // 0
	}
}
