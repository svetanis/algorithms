package com.svetanis.algorithms.dp.countways.stairs;

// 746. Min Cost Climbing Stairs

public final class MinCostClimbStairsBottomUp {
	// Time Complexity: O(n)

	public static int minCost(int[] cost) {
		int n = cost.length;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 0;
		for (int i = 2; i <= cost.length; i++) {
			// one step from previous
			int one = cost[i - 1] + dp[i - 1];
			// two steps from step before the previous
			int two = cost[i - 2] + dp[i - 2];
			dp[i] = Math.min(one, two);
		}
		return dp[n];
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 15, 20 };
		int[] a2 = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
		System.out.println(minCost(a1)); // 15
		System.out.println(minCost(a2)); // 6
	}
}
