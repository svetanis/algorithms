package com.svetanis.algorithms.dp.maxprofit;

import com.svetanis.java.base.utils.Print;

// 2361. Minimum Costs Using the Train Line

public final class MinCostTrainLineSpaceOptimized {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public long[] minCosts(int[] regular, int[] express, int ecost) {
		int n = regular.length;
		long prevReg = 0;
		long prevExp = ecost;
		long[] cost = new long[n];
		// 1 - regular lane, 0 - express lane
		for (int i = 1; i < n + 1; i++) {
			long reg = regular[i - 1] + Math.min(prevExp, prevReg);
			long exp = express[i - 1] + Math.min(prevExp, prevReg + ecost);
			cost[i - 1] = Math.min(reg, exp);
			prevReg = reg;
			prevExp = exp;
		}
		return cost;
	}

	public static void main(String[] args) {
		MinCostTrainLineSpaceOptimized mct = new MinCostTrainLineSpaceOptimized();
		int[] regular = { 1, 3, 2 };
		int[] express = { 4, 1, 2 };
		Print.print(mct.minCosts(regular, express, 2));
	}
}
