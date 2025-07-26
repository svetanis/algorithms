package com.svetanis.algorithms.dp.maxprofit;

// 309. Best Time to Buy and Sell Stock with Cooldown

// After you sell your stock, 
// you cannot buy stock on the next day 
// (i.e., cooldown one day).

public final class BestTimeBuySellStockCooldownBottomUp {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int n = prices.length;
		int[] sell = new int[n];
		sell[0] = 0;
		int[] buy = new int[n];
		buy[0] = -prices[0];
		for (int i = 1; i < n; i++) {
			sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
			int temp = i >= 2 ? sell[i - 2] - prices[i] : -prices[i];
			buy[i] = Math.max(buy[i - 1], temp);
		}
		return sell[n - 1];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfit(a1)); // 3
		int[] a2 = { 1 };
		System.out.println(maxProfit(a2)); // 0
	}
}
