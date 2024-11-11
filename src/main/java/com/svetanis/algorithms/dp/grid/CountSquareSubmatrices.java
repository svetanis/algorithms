package com.svetanis.algorithms.dp.grid;

import static java.lang.Math.min;

// 1277. Count Square Submatrices with All Ones

public final class CountSquareSubmatrices {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int count(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int total = 0;
		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = grid[i][j];
				} else if (grid[i][j] == 1) {
					int top = dp[i - 1][j];
					int left = dp[i][j - 1];
					int diag = dp[i - 1][j - 1];
					dp[i][j] = 1 + min(diag, min(top, left));
				}
				total += dp[i][j];
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 } };
		System.out.println(count(g1)); // 15

		int[][] g2 = { { 1, 0, 1 }, { 1, 1, 0 }, { 1, 1, 0 } };
		System.out.println(count(g2)); // 7
	}
}
