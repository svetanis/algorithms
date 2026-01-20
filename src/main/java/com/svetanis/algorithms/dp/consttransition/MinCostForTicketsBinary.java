package com.svetanis.algorithms.dp.consttransition;

import java.util.Arrays;

// 983. Minimum Cost For Tickets

public final class MinCostForTicketsBinary {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	private int[] days;
	private int[] costs;
	private int[] passes;
	private int totalDays;

	public int minCost(int[] days, int[] costs) {
		this.days = days;
		this.costs = costs;
		this.totalDays = days.length;
		this.passes = new int[] { 1, 7, 30 };
		Integer[] dp = new Integer[totalDays];
		return dfs(0, dp);
	}

	private int dfs(int index, Integer[] dp) {
		if (index >= totalDays) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int min = Integer.MAX_VALUE;
		for (int pass = 0; pass < passes.length; pass++) {
			int target = days[index] + passes[pass];
			// int next = lowerBound(days, target);
			int next = Arrays.binarySearch(days, target);
			next = next < 0 ? -next - 1 : next;
			int cost = costs[pass] + dfs(next, dp);
			min = Math.min(min, cost);
		}
		return dp[index] = min;
	}

	private int lowerBound(int[] days, int target) {
		int left = 0;
		int right = totalDays;
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
		MinCostForTicketsBinary mct = new MinCostForTicketsBinary();
		int[] days1 = { 1, 4, 6, 7, 8, 20 };
		int[] costs1 = { 2, 7, 15 };
		System.out.println(mct.minCost(days1, costs1)); // 11

		int[] days2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 };
		int[] costs2 = { 2, 7, 15 };
		System.out.println(mct.minCost(days2, costs2)); // 17
	}
}
