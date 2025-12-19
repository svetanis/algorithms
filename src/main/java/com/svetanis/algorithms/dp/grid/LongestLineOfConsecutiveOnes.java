package com.svetanis.algorithms.dp.grid;

// 562. Longest Line of Consecutive One in Matrix

public final class LongestLineOfConsecutiveOnes {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int longestLine(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int[][] h = new int[rows + 2][cols + 2];
		int[][] v = new int[rows + 2][cols + 2];
		int[][] d = new int[rows + 2][cols + 2];
		int[][] ad = new int[rows + 2][cols + 2];

		int max = 0;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				if (grid[i - 1][j - 1] == 1) {
					h[i][j] = h[i][j - 1] + 1;
					max = Math.max(max, h[i][j]);
					v[i][j] = v[i - 1][j] + 1;
					max = Math.max(max, v[i][j]);
					d[i][j] = d[i - 1][j - 1] + 1;
					max = Math.max(max, d[i][j]);
					ad[i][j] = ad[i - 1][j + 1] + 1;
					max = Math.max(max, ad[i][j]);
				}
			}
		}
		return max;
	}

	public static int longestLine2(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		if (rows == 0) {
			return 0;
		}
		int max = 0;
		int[][][] dp = new int[rows][cols][4];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 1) {
					// horizontal direction
					dp[i][j][0] = j == 0 ? 1 : dp[i][j - 1][0] + 1;
					max = Math.max(max, dp[i][j][0]);
					// vertical direction
					dp[i][j][1] = i == 0 ? 1 : dp[i - 1][j][1] + 1;
					max = Math.max(max, dp[i][j][1]);
					// diagonal direction
					dp[i][j][2] = (i == 0 || j == 0) ? 1 : dp[i - 1][j - 1][2] + 1;
					max = Math.max(max, dp[i][j][2]);
					// antidiagonal direction
					dp[i][j][3] = (i == 0 || j == cols - 1) ? 1 : dp[i - 1][j + 1][3] + 1;
					max = Math.max(max, dp[i][j][3]);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] grid = { { 0, 1, 1, 0 }, { 0, 1, 1, 1 }, { 1, 0, 0, 1 } };
		System.out.println(longestLine2(grid)); // 3
	}
}
