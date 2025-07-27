package com.svetanis.algorithms.dp.maxprofit;

// 2291. Maximum Profit From Trading Stocks

public final class MaxProfitTrading {
	// Time Complexity: O(n * budget)
	// Space Complexity: O(budget)

	public static int maxProfit(int[] present, int[] future, int budget) {
		int[] dp = new int[budget + 1];
		for (int i = 0; i < present.length; i++) {
			int profit = future[i] - present[i];
			for (int j = budget; j >= present[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - present[i]] + profit);
			}
		}
		return dp[budget];
	}

	public static void main(String[] args) {
		int[] present = { 1, 2, 3 };
		int[] future = { 2, 3, 5 };
		System.out.println(maxProfit(present, future, 5)); // 3
	}
}
