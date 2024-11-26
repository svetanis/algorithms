package com.svetanis.algorithms.dp.maxprofit;

// 122. Best Time to Buy and Sell Stock II

// You can only hold at most one share of the stock at any time.
// You can buy and sell the stock on the same day.

public final class BestTimeBuySellStockII {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxProfit(int[] prices) {
		int profit = 0; // max profit
		for (int i = 1; i < prices.length; i++) {
			int daily = Math.max(0, prices[i] - prices[i - 1]);
			profit += daily;
		}
		return profit;
	}

	public static void main(String[] args) {
		int[] a1 = { 7, 1, 5, 3, 6, 4 };
		System.out.println(maxProfit(a1)); // 7
		int[] a2 = { 1, 2, 3, 4, 5 };
		System.out.println(maxProfit(a2)); // 4
		int[] a3 = { 7, 6, 4, 3, 1 };
		System.out.println(maxProfit(a3)); // 0
	}
}
