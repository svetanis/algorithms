package com.svetanis.algorithms.dp.maxprofit;

import com.svetanis.java.base.utils.Print;

// 2361. Minimum Costs Using the Train Line

public final class MinCostTrainLineTopDown {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private int ecost;
	private int[] regular;
	private int[] express;

	public long[] minCosts(int[] regular, int[] express, int ecost) {
		int n = regular.length;
		this.ecost = ecost;
		this.regular = regular;
		this.express = express;
		Long[][] dp = new Long[n][2];
		// 1 - regular lane, 0 - express lane
		dfs(n - 1, 1, dp);
		long[] cost = new long[n];
		for (int i = 0; i < n; i++) {
			cost[i] = dp[i][1];
		}
		return cost;
	}

	private long dfs(int index, int lane, Long[][] dp) {
		if (index < 0) {
			return 0;
		}
		if (dp[index][lane] != null) {
			return dp[index][lane];
		}

		long reg = regular[index] + dfs(index - 1, 1, dp);
		int fee = lane == 1 ? ecost : 0;
		long exp = express[index] + fee + dfs(index - 1, 0, dp);
		return dp[index][lane] = Math.min(reg, exp);
	}

	public static void main(String[] args) {
		MinCostTrainLineTopDown mct = new MinCostTrainLineTopDown();
		int[] regular = { 1, 3, 2 };
		int[] express = { 4, 1, 2 };
		Print.print(mct.minCosts(regular, express, 2));
	}
}
