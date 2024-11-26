package com.svetanis.algorithms.dp.consttransition;

// 983. Minimum Cost For Tickets

public final class MinCostForTickets {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int minCost(int[] days, int[] costs) {
		int[] passes = { 1, 7, 30 };
		Integer[] dp = new Integer[366];
		return dfs(days, costs, passes, 0, dp);
	}

	private static int dfs(int[] days, int[] costs, int[] passes, int index, Integer[] dp) {
		if (index >= days.length) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int min = Integer.MAX_VALUE;
		for (int k = 0; k < passes.length; k++) {
			int target = days[index] + passes[k];
			int next = lowerBound(days, target);
			int cost = costs[k] + dfs(days, costs, passes, next, dp);
			min = Math.min(min, cost);
		}
		return dp[index] = min;
	}

	private static int lowerBound(int[] days, int target) {
		int left = 0;
		int right = days.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (days[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] days1 = { 1, 4, 6, 7, 8, 20 };
		int[] costs1 = { 2, 7, 15 };
		System.out.println(minCost(days1, costs1)); // 11

		int[] days2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 };
		int[] costs2 = { 2, 7, 15 };
		System.out.println(minCost(days2, costs2)); // 17
	}
}
