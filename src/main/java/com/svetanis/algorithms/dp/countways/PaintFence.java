package com.svetanis.algorithms.dp.countways;

// 276. Paint Fence

public final class PaintFence {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int countWays(int n, int k) {
		int[][] dp = new int[n][2];
		dp[0][0] = k;
		for (int i = 1; i < n; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1);
			dp[i][1] = dp[i - 1][0];
		}
		return dp[n - 1][0] + dp[n - 1][1];
	}

	public static void main(String[] args) {
		System.out.println(countWays(3, 2)); // 6
	}
}
