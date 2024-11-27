package com.svetanis.algorithms.dp.maxprofit;

// 714. Best Time to Buy and Sell Stock with Transaction Fee

// no multiple transactions,  
// must sell before buy again 
// pay fee for each sell or buy transaction

public final class BestTimeBuySellStockTransactionFee {
	// Time Complexity: O(n)

	public static int maxProfit(int[] prices, int fee) {
		int profit = 0;
		int stock = -prices[0];
		for (int day = 1; day < prices.length; day++) {
			int price = prices[day];
			// sell price of stock minus fee
			profit = Math.max(profit, stock + price - fee);
			// price of buying stock
			stock = Math.max(stock, profit - price);
		}
		return profit;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 2, 8, 4, 9 };
		System.out.println(maxProfit(a1, 2)); // 8
		int[] a2 = { 1, 3, 7, 5, 10, 3 };
		System.out.println(maxProfit(a2, 3)); // 6
	}
}
