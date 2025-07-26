package com.svetanis.algorithms.dp.maxprofit;

// 123. Best Time to Buy and Sell Stock III

// You may complete at most two transactions.

public final class BestTimeBuySellStockIIIBottomUp {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int n = prices.length;
		int[][] dp = new int[n][5];
		dp[0][0] = 0;
		dp[0][1] = -prices[0]; // buy first stock
		dp[0][2] = 0; // after first sell
		dp[0][3] = -prices[0]; // buy second stock
		dp[0][4] = 0; // after second sell
		for (int i = 1; i < prices.length; i++) {
			int price = prices[i];
			dp[i][0] = dp[i - 1][0];
			dp[i][1] = Math.max(dp[i - 1][1], -price);
			dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + price);
			dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - price);
			dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + price);
		}
		return dp[n - 1][4];
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 3, 5, 0, 0, 3, 1, 4 };
		System.out.println(maxProfit(a1)); // 6
		int[] a2 = { 1, 2, 3, 4, 5 };
		System.out.println(maxProfit(a2)); // 4
		int[] a3 = { 7, 6, 4, 3, 1 };
		System.out.println(maxProfit(a3)); // 0
	}
}
