package com.svetanis.algorithms.dp.maxprofit;

// 123. Best Time to Buy and Sell Stock III

// You may complete at most two transactions.

public final class BestTimeBuySellStockIII {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxProfit(int[] prices) {
		int s1 = 0;
		int s2 = 0;
		int b1 = -prices[0];
		int b2 = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			int price = prices[i];
			b1 = Math.max(b1, -price);
			s1 = Math.max(s1, b1 + price);
			b2 = Math.max(b2, s1 - price);
			s2 = Math.max(s2, b2 + price);
		}
		return s2;
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
