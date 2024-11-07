package com.svetanis.algorithms.dp.mincost;

// 746. Min Cost Climbing Stairs

public final class MinCostClimbStairs {
	// Time Complexity: O(n)

	public static int minCost(int[] cost) {
		int prev = 0;
		int prevPrev = 0;
		for (int i = 2; i <= cost.length; i++) {
			// one step from previous
			int one = cost[i - 1] + prev;
			// two steps from step before the previous
			int two = cost[i - 2] + prevPrev;
			int curr = Math.min(one, two);
			prevPrev = prev;
			prev = curr;
		}
		return prev;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 15, 20 };
		int[] a2 = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
		System.out.println(minCost(a1)); // 15
		System.out.println(minCost(a2)); // 6
	}
}
