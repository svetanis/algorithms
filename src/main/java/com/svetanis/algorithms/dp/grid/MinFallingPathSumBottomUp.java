package com.svetanis.algorithms.dp.grid;

import java.util.Arrays;

// 931. Minimum Falling Path Sum

public final class MinFallingPathSumBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final int INF = Integer.MAX_VALUE;

	public static int mps(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		for (int c = 0; c < n; c++) {
			dp[0][c] = grid[0][c];
		}
		for (int r = 1; r < n; r++) {
			for (int c = 0; c < m; c++) {
				int val = grid[r][c];
				int top = dp[r - 1][c];
				int left = c == 0 ? INF : dp[r - 1][c - 1];
				int right = c == m - 1 ? INF : dp[r - 1][c + 1];
				dp[r][c] = val + Math.min(Math.min(right, left), top);
			}
		}
		return Arrays.stream(dp[n - 1]).min().getAsInt();
	}

	public static void main(String[] args) {
		int[][] g1 = { { 2, 1, 3 }, { 6, 5, 4 }, { 7, 8, 9 } };
		int[][] g2 = { { -19, 57 }, { -40, -5 } };
		System.out.println(mps(g1)); // 13
		System.out.println(mps(g2)); // -59
	}
}
