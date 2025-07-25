package com.svetanis.algorithms.dp.maxprofit;

// 309. Best Time to Buy and Sell Stock with Cooldown

// After you sell your stock, 
// you cannot buy stock on the next day 
// (i.e., cooldown one day).

public final class BestTimeBuySellStockCooldown {
	// Time Complexity: O(n)

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int sellPrev = 0;
		int buyPrev = -prices[0];
		int sellPrevPrev = 0;
		for (int day = 1; day < prices.length; day++) {
			int price = prices[day];
			int sellCurr = Math.max(sellPrev, buyPrev + price);
			int buyCurr = Math.max(buyPrev, sellPrevPrev - price);
			sellPrevPrev = sellPrev;
			sellPrev = sellCurr;
			buyPrev = buyCurr;
		}
		return sellPrev;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfit(a1)); // 3
		int[] a2 = { 1 };
		System.out.println(maxProfit(a2)); // 0
	}
}
