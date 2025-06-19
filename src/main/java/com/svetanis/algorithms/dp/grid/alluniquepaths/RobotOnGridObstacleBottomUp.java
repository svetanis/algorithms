package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 63. Unique Paths II

public final class RobotOnGridObstacleBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int countUniquePaths(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		for (int r = 0; r < n && grid[r][0] == 0; r++) {
			dp[r][0] = 1;
		}
		for (int c = 0; c < m && grid[0][c] == 0; c++) {
			dp[0][c] = 1;
		}
		for (int r = 1; r < n; r++) {
			for (int c = 1; c < m; c++) {
				if (grid[r][c] == 0) {
					dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	// more compact
	public static int countUniquePaths2(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		dp[0][0] = grid[0][0] == 1 ? 0 : 1;
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < m; ++c) {
				if (grid[r][c] == 0) {
					int right = r < 1 ? 0 : dp[r - 1][c];
					int down = c < 1 ? 0 : dp[r][c - 1];
					dp[r][c] += (right + down);
				}
			}
		}
		return dp[n - 1][m - 1];
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

		System.out.println(countUniquePaths2(g1)); // 2
		System.out.println(countUniquePaths2(g2)); // 1
		System.out.println(countUniquePaths2(g3)); // 0
		System.out.println(countUniquePaths2(g4)); // 0
	}
}
