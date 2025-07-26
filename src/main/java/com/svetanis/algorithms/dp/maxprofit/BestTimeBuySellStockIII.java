package com.svetanis.algorithms.dp.maxprofit;

// 123. Best Time to Buy and Sell Stock III

// You may complete at most two transactions.

public final class BestTimeBuySellStockIII {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int sell1 = 0;
		int sell2 = 0;
		int buy1 = -prices[0];
		int buy2 = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			int price = prices[i];
			buy1 = Math.max(buy1, -price);
			sell1 = Math.max(sell1, buy1 + price);
			buy2 = Math.max(buy2, sell1 - price);
			sell2 = Math.max(sell2, buy2 + price);
		}
		return sell2;
	}

	public static int maxProfit2(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int sell1 = 0;
		int sell2 = 0;
		int buy1 = Integer.MIN_VALUE;
		int buy2 = Integer.MIN_VALUE;
		for (int price : prices) {
			sell2 = Math.max(sell2, buy2 + price);
			buy2 = Math.max(buy2, sell1 - price);
			sell1 = Math.max(sell1, buy1 + price);
			buy1 = Math.max(buy1, -price);
		}
		return sell2;
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
