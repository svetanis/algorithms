package com.svetanis.algorithms.dp.grid.alluniquepaths;

import static java.lang.Integer.MAX_VALUE;
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

	// more compact: one base case, and the recurrence handles the edges.
	// A cell in row 0 has no cell above it and a cell in column 0 has none
	// to its left, so the recurrence has to name a value for the arrival that
	// does not exist. It is MAX_VALUE and not 0: with 0, min would take a free
	// arrival from outside the grid and every edge cell would be too cheap.
	// MAX_VALUE is never added to, because (0, 0) is the only cell with no real
	// neighbour on either side and it is written before the recurrence runs.
	public static int mps2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (r == 0 && c == 0) {
					dp[0][0] = grid[0][0];
					continue;
				}
				int top = r == 0 ? MAX_VALUE : dp[r - 1][c];
				int left = c == 0 ? MAX_VALUE : dp[r][c - 1];
				dp[r][c] = grid[r][c] + min(top, left);
			}
		}
		return dp[m - 1][n - 1];
	}

	public static void main(String[] args) {
		// m rows and n columns
		int[][] g1 = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int[][] g2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(mps(g1)); // 7: 1->3->1->1->1
		System.out.println(mps(g2)); // 12

		System.out.println(mps2(g1)); // 7: 1->3->1->1->1
		System.out.println(mps2(g2)); // 12
	}
}
