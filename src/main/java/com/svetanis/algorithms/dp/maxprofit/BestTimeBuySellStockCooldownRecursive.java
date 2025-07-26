package com.svetanis.algorithms.dp.maxprofit;

// 309. Best Time to Buy and Sell Stock with Cooldown

// After you sell your stock, 
// you cannot buy stock on the next day 
// (i.e., cooldown one day).

public final class BestTimeBuySellStockCooldownRecursive {
	// Time Complexity: O(2^n)
	// Space Complexity: O(n)

	public static int maxProfit(int[] prices) {
		return dfs(prices, 0, false);
	}

	private static int dfs(int[] prices, int index, boolean holding) {
		if (index >= prices.length) {
			return 0;
		}
		// cooldown (do nothing)
		int cooldown = dfs(prices, index + 1, holding);
		if (holding) { // holding, can sell
			int sell = prices[index] + dfs(prices, index + 2, false);
			return Math.max(cooldown, sell);
		} else { // not holding, can buy
			int buy = -prices[index] + dfs(prices, index + 1, true);
			return Math.max(cooldown, buy);
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfit(a1)); // 3
		int[] a2 = { 1 };
		System.out.println(maxProfit(a2)); // 0
	}
}
