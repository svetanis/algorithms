package com.svetanis.algorithms.dp.countways;

// 1269. Number of Ways to Stay in the Same Place After Some Steps

public final class CountWaysToRemainInPlaceSpaceOptimized {
	// Time Complexity: O(steps * min(steps, len))
	// Space Complexity: O(min(steps, len))

	private static final int MOD = (int) 1e9 + 7;

	public int countWays(int steps, int len) {
		len = Math.min(steps, len);
		int[] dp = new int[len];
		int[] prev = new int[len];
		prev[0] = 1;
		for (int step = 1; step <= steps; step++) {
			dp = new int[len];
			for (int i = len - 1; i >= 0; i--) {
				int count = prev[i];
				if (i > 0) {
					count = (count + prev[i - 1]) % MOD;
				}
				if (i < len - 1) {
					count = (count + prev[i + 1]) % MOD;
				}
				dp[i] = count;
			}
			prev = dp;
		}
		return dp[0];
	}

	public static void main(String[] args) {
		CountWaysToRemainInPlaceSpaceOptimized cwt = new CountWaysToRemainInPlaceSpaceOptimized();
		System.out.println(cwt.countWays(3, 2)); // 4
		System.out.println(cwt.countWays(2, 4)); // 2
		System.out.println(cwt.countWays(4, 2)); // 8
	}
}
