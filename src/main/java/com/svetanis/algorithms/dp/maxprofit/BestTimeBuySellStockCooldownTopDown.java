package com.svetanis.algorithms.dp.maxprofit;

// 309. Best Time to Buy and Sell Stock with Cooldown

// After you sell your stock, 
// you cannot buy stock on the next day 
// (i.e., cooldown one day).

public final class BestTimeBuySellStockCooldownTopDown {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int maxProfit(int[] prices) {
		Integer[][] dp = new Integer[prices.length][2];
		return dfs(prices, 0, 0, dp);
	}

	private static int dfs(int[] prices, int index, int holding, Integer[][] dp) {
		if (index >= prices.length) {
			return 0;
		}
		if (dp[index][holding] != null) {
			return dp[index][holding];
		}
		// cooldown (do nothing)
		int cooldown = dfs(prices, index + 1, holding, dp);
		if (holding == 1) { // holding, can sell
			int sell = prices[index] + dfs(prices, index + 2, 0, dp);
			dp[index][holding] = Math.max(cooldown, sell);
		} else { // not holding, can buy
			int buy = -prices[index] + dfs(prices, index + 1, 1, dp);
			dp[index][holding] = Math.max(cooldown, buy);
		}
		return dp[index][holding];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfit(a1)); // 3
		int[] a2 = { 1 };
		System.out.println(maxProfit(a2)); // 0
	}
}
