package com.svetanis.algorithms.dp.countways.stairs;

// 70. Climbing Stairs

public final class ClimbingStairs70BottomUp {
	// Time complexity: O(n)
	// Space complexity: O(n)

	public static int count(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 0 || n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(count(2)); // 2
		System.out.println(count(3)); // 3
	}
}
