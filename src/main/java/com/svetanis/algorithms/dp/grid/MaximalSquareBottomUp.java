package com.svetanis.algorithms.dp.grid;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

// 221. Maximal Square

public final class MaximalSquareBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int mss(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int max = MIN_VALUE;
		int[][] dp = new int[m][n];
		for (int r = 0; r < m; r++) {
			dp[r][0] = grid[r][0] - '0';
			max = max(max, dp[r][0]);
		}
		for (int c = 0; c < n; c++) {
			dp[0][c] = grid[0][c] - '0';
			max = max(max, dp[0][c]);
		}
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < n; c++) {
				if (grid[r][c] == '1') {
					int top = dp[r - 1][c];
					int left = dp[r][c - 1];
					int diag = dp[r - 1][c - 1];
					dp[r][c] = 1 + min(min(top, left), diag);
					max = max(max, dp[r][c]);
				} else {
					dp[r][c] = 0;
				}
			}
		}
		return max * max;
	}

	public static void main(String[] args) {
		char[][] g1 = { { '1', '0', '1', '0', '0' }, //
				{ '1', '0', '1', '1', '1' }, //
				{ '1', '1', '1', '1', '1' }, //
				{ '1', '0', '0', '1', '0' } };
		System.out.println(mss(g1)); // 4
		char[][] g2 = { { '0', '1' }, { '1', '0' } };
		System.out.println(mss(g2)); // 1
		char[][] g3 = { { '0' } };
		System.out.println(mss(g3)); // 0
	}
}
