package com.svetanis.algorithms.dp.maxprofit;

import com.svetanis.java.base.utils.Print;

// 2361. Minimum Costs Using the Train Line

public final class MinCostTrainLine {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static long[] minCosts(int[] regular, int[] express, int ecost) {
		int n = regular.length;
		long mcr = 0;
		long mce = Long.MAX_VALUE / 2;
		long[] cost = new long[n];
		for (int day = 0; day < n; day++) {
			int rc = regular[day];
			int ec = express[day];
			long rcm = Math.min(mcr + rc, mce + rc);
			long ecm = Math.min(mce + ec, mcr + ec + ecost);
			mcr = rcm;
			mce = ecm;
			cost[day] = Math.min(mcr, mce);
		}
		return cost;
	}

	public static void main(String[] args) {
		int[] regular = { 1, 3, 2 };
		int[] express = { 4, 1, 2 };
		Print.print(minCosts(regular, express, 2));
	}
}
