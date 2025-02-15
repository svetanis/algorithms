package com.svetanis.algorithms.dp.interval;

// 1130. Minimum Cost Tree From Leaf Values

public final class MinCostTreeFromLeafValues {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n^2)

	public static int mct(int[] a) {
		int n = a.length;
		int[][] dp = new int[n][n];
		int[][] ml = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			ml[i][i] = a[i];
			for (int j = i + 1; j < n; j++) {
				ml[i][j] = Math.max(ml[i][j - 1], a[j]);
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int product = ml[i][k] * ml[k + 1][j];
					int cost = dp[i][k] + dp[k + 1][j] + product;
					dp[i][j] = Math.min(dp[i][j], cost);
				}
			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		int[] a1 = { 6, 2, 4 };
		System.out.println(mct(a1)); // 32

		int[] a2 = { 4, 11 };
		System.out.println(mct(a2)); // 44
	}
}
