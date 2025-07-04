package com.svetanis.algorithms.dp.math.catalan;

// 1259. Handshakes That Don't Cross

public final class HandshakesBottomUp {
	// Time Complexity: O(n^2)

	private static final int MOD = (int) 1e9 + 7;

	public static int countWays(int numPeople) {
		int n = numPeople / 2;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] = (dp[i] + (dp[j] * dp[i - 1 - j]) % MOD) % MOD;
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(countWays(4)); // 2
	}
}
