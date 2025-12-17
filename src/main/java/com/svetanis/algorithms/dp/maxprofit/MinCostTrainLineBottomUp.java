package com.svetanis.algorithms.dp.maxprofit;

import com.svetanis.java.base.utils.Print;

// 2361. Minimum Costs Using the Train Line

public final class MinCostTrainLineBottomUp {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public long[] minCosts(int[] regular, int[] express, int ecost) {
		int n = regular.length;
		long[] cost = new long[n];
		// 1 - regular lane, 0 - express lane
		long[][] dp = new long[n + 1][2];
		dp[0][1] = 0;
		dp[0][0] = ecost;
		for (int i = 1; i < n + 1; i++) {
			dp[i][1] = regular[i - 1] + Math.min(dp[i - 1][0], dp[i - 1][1]);
			dp[i][0] = express[i - 1] + Math.min(dp[i - 1][0], dp[i - 1][1] + ecost);
			cost[i - 1] = Math.min(dp[i][0], dp[i][1]);
		}
		return cost;
	}

	public static void main(String[] args) {
		MinCostTrainLineBottomUp mct = new MinCostTrainLineBottomUp();
		int[] regular = { 1, 3, 2 };
		int[] express = { 4, 1, 2 };
		Print.print(mct.minCosts(regular, express, 2));
	}
}
