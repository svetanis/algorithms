package com.svetanis.algorithms.dp.grid.alluniquepaths;

import static java.lang.Math.min;

// 64. Minimum Path Sum

public final class MinPathSumBottomUpSubmit {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int mps(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = init(grid);
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < n; c++) {
				int left = dp[r][c - 1];
				int top = dp[r - 1][c];
				int val = grid[r][c];
				dp[r][c] = val + min(left, top);
			}
		}
		return dp[m - 1][n - 1];
	}

	private static int[][] init(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		// initialize dp grid and
		int[][] dp = new int[m][n];
		// base case at dp[0][0]
		dp[0][0] = grid[0][0];
		// min path sum for the first row
		for (int c = 1; c < n; c++) {
			dp[0][c] = dp[0][c - 1] + grid[0][c];
		}
		// min path sum for the first column
		for (int r = 1; r < m; r++) {
			dp[r][0] = dp[r - 1][0] + grid[r][0];
		}
		return dp;
	}

	public static void main(String[] args) {
		// m rows and n columns
		int[][] g1 = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int[][] g2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(mps(g1)); // 7: 1->3->1->1->1
		System.out.println(mps(g2)); // 12
	}
}
