package com.svetanis.algorithms.dp.countways;

// 1269. Number of Ways to Stay in the Same Place After Some Steps

public final class CountWaysToRemainInPlaceBottomUp {
	// Time Complexity: O(steps * len)
	// Space Complexity: O(steps * len)

	private static final int MOD = (int) 1e9 + 7;

	public int countWays(int steps, int len) {
		len = Math.min(steps, len);
		int[][] dp = new int[len + 1][steps + 1];
		dp[0][0] = 1;

		for (int step = 1; step <= steps; step++) {
			for (int i = len - 1; i >= 0; i--) {
				int count = dp[i][step - 1];
				if (i > 0) {
					count = (count + dp[i - 1][step - 1]) % MOD;
				}
				if (i < len - 1) {
					count = (count + dp[i + 1][step - 1]) % MOD;
				}
				dp[i][step] = count;
			}
		}
		return dp[0][steps];
	}

	public static void main(String[] args) {
		CountWaysToRemainInPlaceBottomUp cwt = new CountWaysToRemainInPlaceBottomUp();
		System.out.println(cwt.countWays(3, 2)); // 4
		System.out.println(cwt.countWays(2, 4)); // 2
		System.out.println(cwt.countWays(4, 2)); // 8
	}
}
