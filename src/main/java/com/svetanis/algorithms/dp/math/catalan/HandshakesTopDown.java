package com.svetanis.algorithms.dp.math.catalan;

// 1259. Handshakes That Don't Cross

public final class HandshakesTopDown {
	// Time Complexity: O(n^2)

	private static final int MOD = (int) 1e9 + 7;

	public static int countWays(int n) {
		int[] dp = new int[n + 1];
		return dfs(n, dp);
	}

	private static int dfs(int n, int[] dp) {
		if (n < 2) {
			return 1;
		}
		if (dp[n] != 0) {
			return dp[n];
		}
		for (int left = 0; left < n; left += 2) {
			int right = n - left - 2;
			dp[n] = (int) ((dp[n] + (long) dfs(left, dp) * dfs(right, dp) % MOD) % MOD);
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(countWays(4)); // 2
	}
}
