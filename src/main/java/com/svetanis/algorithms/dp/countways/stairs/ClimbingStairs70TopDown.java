package com.svetanis.algorithms.dp.countways.stairs;

// 70. Climbing Stairs

public final class ClimbingStairs70TopDown {
	// Time complexity: O(n)
	// Space complexity: O(n)

	public static int count(int n) {
		Integer[] dp = new Integer[n + 1];
		return count(n, dp);
	}

	public static int count(int n, Integer[] dp) {
		if (n < 0) {
			return 0;
		}
		if (n == 0 || n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (dp[n] != null) {
			return dp[n];
		}
		dp[n] = count(n - 1, dp) + count(n - 2, dp);
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(count(2)); // 2
		System.out.println(count(3)); // 3
	}
}
