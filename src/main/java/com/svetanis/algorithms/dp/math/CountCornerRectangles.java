package com.svetanis.algorithms.dp.math;

// 750. Number Of Corner Rectangles

public final class CountCornerRectangles {
	// Time Complexity: O(m * n^2)

	public static int countSimple(int[][] grid) {
		int count = 0;
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			int[] dp = new int[m];
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					continue;
				}
				for (int k = i - 1; k >= 0; k--) {
					if (grid[k][j] == 0) {
						continue;
					}
					count += dp[k];
					dp[k] += 1;
				}
			}
		}
		return count;
	}

	public static int count(int[][] grid) {
		int count = 0;
		int n = grid.length;
		int m = grid[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int pairs = 0;
				for (int k = 0; k < m; k++) {
					if (grid[i][k] == 1 && grid[j][k] == 1) {
						pairs++;
					}
				}
				if (pairs > 1) {
					count += pairs * (pairs - 1) / 2;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 0, 0, 1, 0 }, { 0, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0 }, { 1, 0, 1, 0, 1 } };
		System.out.println(count(g1)); // 1

		int[][] g2 = { { 1, 0, 1 }, { 1, 1, 1 }, { 1, 0, 0 }, { 1, 0, 1 } };
		System.out.println(count(g2)); // 3
	}
}
