package com.svetanis.algorithms.dp.grid;

// 63. Unique Paths II

public final class RobotOnGridObstacleBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int countUniquePaths(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		for (int r = 0; r < m && grid[r][0] == 0; r++) {
			dp[r][0] = 1;
		}
		for (int c = 0; c < n && grid[0][c] == 0; c++) {
			dp[0][c] = 1;
		}
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < n; c++) {
				if (grid[r][c] == 0) {
					dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
				}
			}
		}
		return dp[m - 1][n - 1];
	}

	public static void main(String[] args) {
		// m rows and n columns
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
