package com.svetanis.algorithms.dp.countways.stairs;

// 746. Min Cost Climbing Stairs

public final class MinCostClimbStairsRecursive {
	// Time Complexity: O(2^n)

	public static int minCost(int[] cost) {
		int n = cost.length;
		return Math.min(dfs(cost, n - 1), dfs(cost, n - 2));
	}

	private static int dfs(int[] cost, int index) {
		if (index < 0) {
			return 0;
		}
		if (index == 0 || index == 1) {
			return cost[index];
		}
		return cost[index] + Math.min(dfs(cost, index - 1), dfs(cost, index - 2));
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 15, 20 };
		int[] a2 = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
		System.out.println(minCost(a1)); // 15
		System.out.println(minCost(a2)); // 6
	}
}
