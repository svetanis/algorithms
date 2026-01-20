package com.svetanis.algorithms.dp.consttransition;

import java.util.HashSet;
import java.util.Set;

// 983. Minimum Cost For Tickets

public final class MinCostForTicketsTopDown2 {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private int totalDays;
	private int[] days;
	private int[] costs;
	private Set<Integer> set;

	public int minCost(int[] days, int[] costs) {
		this.days = days;
		this.costs = costs;
		this.totalDays = days.length;
		int lastDay = days[totalDays - 1];
		this.set = new HashSet<>();
		for (int day : days) {
			set.add(day);
		}
		Integer[] dp = new Integer[lastDay + 1];
		return dfs(1, dp);
	}

	private int dfs(int currDay, Integer[] dp) {
		if (currDay > days[totalDays - 1]) {
			return 0;
		}
		// don't need to travel
		if (!set.contains(currDay)) {
			return dfs(currDay + 1, dp);
		}
		if (dp[currDay] != null) {
			return dp[currDay];
		}
		int cost1 = costs[0] + dfs(currDay + 1, dp);
		int cost7 = costs[1] + dfs(currDay + 7, dp);
		int cost30 = costs[2] + dfs(currDay + 30, dp);
		return dp[currDay] = Math.min(cost1, Math.min(cost7, cost30));
	}

	public static void main(String[] args) {
		MinCostForTicketsTopDown2 mct = new MinCostForTicketsTopDown2();
		int[] days1 = { 1, 4, 6, 7, 8, 20 };
		int[] costs1 = { 2, 7, 15 };
		System.out.println(mct.minCost(days1, costs1)); // 11

		int[] days2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 };
		int[] costs2 = { 2, 7, 15 };
		System.out.println(mct.minCost(days2, costs2)); // 17
	}
}
