package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 63. Unique Paths II

public final class RobotOnGridObstacleBottomUpSpaceOptimized {
	// Time Complexity: O(n * m)
	// Space Complexity: O(m)

	public static int countUniquePaths(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[] dp = new int[m];
		dp[0] = grid[0][0] == 1 ? 0 : 1;
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < m; ++c) {
				if (grid[r][c] == 1) {
					dp[c] = 0;
				} else if (c > 0) {
					dp[c] += dp[c - 1];
				}
			}
		}
		return dp[m - 1];
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
