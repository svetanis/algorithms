package com.svetanis.algorithms.dp.maxprofit;

// 122. Best Time to Buy and Sell Stock II

// You can only hold at most one share of the stock at any time.
// You can buy and sell the stock on the same day.

public final class BestTimeBuySellStockIIPeakValley {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxProfit(int[] prices) {
		int n = prices.length;
		int profit = 0; // max profit
		int valley = prices[0];
		int peak = prices[0];
		int i = 0;
		while(i < n - 1) {
			while(i < n - 1 && prices[i] >= prices[i + 1]) {
				i++;
			}
			valley = prices[i];
			while(i < n - 1 && prices[i] <= prices[i + 1]) {
				i++;
			}
			peak = prices[i];
			profit += peak - valley;
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
