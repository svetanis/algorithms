package com.svetanis.algorithms.dp.countways.stairs;

// 746. Min Cost Climbing Stairs

public final class MinCostClimbStairsTopDown {
	// Time Complexity: O(n)

	public static int minCost(int[] cost) {
		int n = cost.length;
		Integer[] dp = new Integer[n + 1];
		return Math.min(dfs(cost, n - 1, dp), dfs(cost, n - 2, dp));
	}

	private static int dfs(int[] cost, int index, Integer[] dp) {
		if (index < 0) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		if (index == 0 || index == 1) {
			return dp[index] = cost[index];
		}
		return dp[index] = cost[index] + Math.min(dfs(cost, index - 1, dp), dfs(cost, index - 2, dp));
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 15, 20 };
		int[] a2 = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
		System.out.println(minCost(a1)); // 15
		System.out.println(minCost(a2)); // 6
	}
}
