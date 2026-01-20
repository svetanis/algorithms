package com.svetanis.algorithms.dp.consttransition;

// 983. Minimum Cost For Tickets

public final class MinCostForTicketsTopDown {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private int totalDays;
	private int[] days;
	private int[] costs;

	public int minCost(int[] days, int[] costs) {
		this.days = days;
		this.costs = costs;
		this.totalDays = days.length;
		Integer[] dp = new Integer[totalDays];
		return dfs(0, 0, dp);
	}

	private int dfs(int index, int allowedDays, Integer[] dp) {
		if (index >= totalDays) {
			return 0;
		}
		if (days[index] <= allowedDays) {
			return dfs(index + 1, allowedDays, dp);
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int cost1 = costs[0] + dfs(index + 1, days[index], dp);
		int cost7 = costs[1] + dfs(index + 1, days[index] + 6, dp);
		int cost30 = costs[2] + dfs(index + 1, days[index] + 29, dp);
		return dp[index] = Math.min(cost1, Math.min(cost7, cost30));
	}

	public static void main(String[] args) {
		MinCostForTicketsTopDown mct = new MinCostForTicketsTopDown();
		int[] days1 = { 1, 4, 6, 7, 8, 20 };
		int[] costs1 = { 2, 7, 15 };
		System.out.println(mct.minCost(days1, costs1)); // 11

		int[] days2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 };
		int[] costs2 = { 2, 7, 15 };
		System.out.println(mct.minCost(days2, costs2)); // 17
	}
}
