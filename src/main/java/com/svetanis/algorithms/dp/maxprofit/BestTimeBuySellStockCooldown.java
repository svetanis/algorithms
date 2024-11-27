package com.svetanis.algorithms.dp.maxprofit;

// 309. Best Time to Buy and Sell Stock with Cooldown

// After you sell your stock, 
// you cannot buy stock on the next day 
// (i.e., cooldown one day).

public final class BestTimeBuySellStockCooldown {
	// Time Complexity: O(n)

	public static int maxProfit(int[] prices) {
		int prevNoStock = 0;
		int prevWithStock = -prices[0];
		int temp = prevNoStock;
		for (int day = 1; day < prices.length; day++) {
			int price = prices[day];
			// keep no stock or sell the stock
			int currNoStock = Math.max(prevNoStock, prevWithStock + price);
			// keep the stock or buy new stock
			prevWithStock = Math.max(prevWithStock, temp - price);
			temp = prevNoStock;
			prevNoStock = currNoStock;
		}
		return prevNoStock;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfit(a1)); // 3
		int[] a2 = { 1 };
		System.out.println(maxProfit(a2)); // 0
	}
}
