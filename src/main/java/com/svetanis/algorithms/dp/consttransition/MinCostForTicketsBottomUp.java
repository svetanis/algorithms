package com.svetanis.algorithms.dp.consttransition;

import java.util.HashSet;
import java.util.Set;

// 983. Minimum Cost For Tickets

public final class MinCostForTicketsBottomUp {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public int minCost(int[] days, int[] costs) {
		Set<Integer> set = new HashSet<>();
		for (int day : days) {
			set.add(day);
		}
		int totalDays = days.length;
		int lastDay = days[totalDays - 1];
		int[] dp = new int[lastDay + 1];
		for (int day = 1; day <= lastDay; day++) {
			if (!set.contains(day)) {
				dp[day] = dp[day - 1];
			} else {
				int cost1 = costs[0] + dp[day - 1];
				int cost7 = costs[1] + dp[Math.max(0, day - 7)];
				int cost30 = costs[2] + dp[Math.max(0, day - 30)];
				dp[day] = Math.min(cost1, Math.min(cost7, cost30));
			}
		}
		return dp[lastDay];
	}

	public int minCost2(int[] days, int[] costs) {
		int totalDays = days.length;
		int lastDay = days[totalDays - 1];
		int[] dp = new int[lastDay + 1];
		int index = 0;
		for (int day = 1; day <= lastDay; day++) {
			if (day < days[index]) {
				dp[day] = dp[day - 1];
			} else {
				index += 1;
				int cost1 = costs[0] + dp[day - 1];
				int cost7 = costs[1] + dp[Math.max(0, day - 7)];
				int cost30 = costs[2] + dp[Math.max(0, day - 30)];
				dp[day] = Math.min(cost1, Math.min(cost7, cost30));
			}
		}
		return dp[lastDay];
	}

	public static void main(String[] args) {
		MinCostForTicketsBottomUp mct = new MinCostForTicketsBottomUp();
		int[] days1 = { 1, 4, 6, 7, 8, 20 };
		int[] costs1 = { 2, 7, 15 };
		System.out.println(mct.minCost(days1, costs1)); // 11

		int[] days2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 };
		int[] costs2 = { 2, 7, 15 };
		System.out.println(mct.minCost(days2, costs2)); // 17
	}
}
