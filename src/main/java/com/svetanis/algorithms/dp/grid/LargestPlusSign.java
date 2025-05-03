package com.svetanis.algorithms.dp.grid;

import java.util.Arrays;

// 764. Largest Plus Sign

public final class LargestPlusSign {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	private int[][] dp;

	public int lps(int n, int[][] mines) {
		init(n, mines);
		for (int i = 0; i < n; i++) {
			int left = 0, right = 0, up = 0, down = 0;
			for (int j = 0, k = n - 1; j < n; j++, k--) {
				left = dp[i][j] > 0 ? left + 1 : 0;
				right = dp[i][k] > 0 ? right + 1 : 0;
				up = dp[j][i] > 0 ? up + 1 : 0;
				down = dp[k][i] > 0 ? down + 1 : 0;
				dp[i][j] = Math.min(dp[i][j], left);
				dp[i][k] = Math.min(dp[i][k], right);
				dp[j][i] = Math.min(dp[j][i], up);
				dp[k][i] = Math.min(dp[k][i], down);
			}
		}
		return Arrays.stream(dp).flatMapToInt(Arrays::stream).max().getAsInt();
	}

	private void init(int n, int[][] mines) {
		this.dp = new int[n][n];
		for (int[] row : dp) {
			Arrays.fill(row, n);
		}
		for (int[] minus : mines) {
			int row = minus[0];
			int col = minus[1];
			dp[row][col] = 0;
		}
	}

	public static void main(String[] args) {
		LargestPlusSign lps = new LargestPlusSign();
		int[][] mines1 = { { 4, 2 } };
		System.out.println(lps.lps(5, mines1)); // 2
		int[][] mines2 = { { 0, 0 } };
		System.out.println(lps.lps(1, mines2)); // 0
	}
}
