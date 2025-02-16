package com.svetanis.algorithms.dp.interval;

// 312. Burst Balloons

public final class BurstBalloons {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n^2)

	public static int maxCoins(int[] a) {
		int[] ext = extended(a);
		int n = ext.length;
		int[][] dp = new int[n][n];
		for (int len = 2; len < n; len++) {
			for (int i = 0; i + len < n; i++) {
				int j = i + len;
				for (int k = i + 1; k < j; k++) {
					int coins = ext[i] * ext[k] * ext[j];
					int sum = dp[i][k] + dp[k][j] + coins;
					dp[i][j] = Math.max(dp[i][j], sum);
				}
			}
		}
		return dp[0][n - 1];
	}

	private static int[] extended(int[] a) {
		int n = a.length;
		int[] extended = new int[n + 2];
		extended[0] = 1;
		extended[extended.length - 1] = 1;
		System.arraycopy(a, 0, extended, 1, n);
		return extended;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, 5, 8 };
		System.out.println(maxCoins(a1)); // 167
		int[] a2 = { 1, 5 };
		System.out.println(maxCoins(a2)); // 10
	}
}
