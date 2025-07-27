package com.svetanis.algorithms.dp.interval;

// 312. Burst Balloons

public final class BurstBalloonsBottomUp {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n^2)

	public static int burstBalloons(int[] a) {
		int[] ext = extended(a);
		int n = ext.length;
		int[][] dp = new int[n][n];
		for (int len = 2; len < n; len++) {
			for (int left = 0; left + len < n; left++) {
				int right = left + len;
				for (int k = left + 1; k < right; k++) {
					int coins = ext[left] * ext[k] * ext[right];
					int sum = dp[left][k] + coins + dp[k][right];
					dp[left][right] = Math.max(dp[left][right], sum);
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
		System.out.println(burstBalloons(a1)); // 167
		int[] a2 = { 1, 5 };
		System.out.println(burstBalloons(a2)); // 10
	}
}
