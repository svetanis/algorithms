package com.svetanis.algorithms.dp.maxprofit;

// 188. Best Time to Buy and Sell Stock IV

// You may complete 
// at most k transactions, i.e.
// you may buy at most k times 
// and sell at most k times

public final class BestTimeBuySellStockIV {
	// Time Complexity: O(n * k)
	// Space Complexity: O(k)

	public static int maxProfit(int[] prices, int k) {
		int[][] dp = init(prices, k);
		for (int day = 1; day < prices.length; day++) {
			int price = prices[day];
			for (int count = k; count > 0; count--) {
				// not holding a stock
				int sell = dp[count][1] + price;
				dp[count][0] = Math.max(dp[count][0], sell);
				// holding a stock
				int buy = dp[count - 1][0] - price;
				dp[count][1] = Math.max(dp[count][1], buy);
			}
		}
		return dp[k][0];
	}

	private static int[][] init(int[] prices, int k) {
		int[][] dp = new int[k + 1][2];
		for (int count = 1; count <= k; count++) {
			dp[count][1] = -prices[0];
		}
		return dp;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 4, 1 };
		System.out.println(maxProfit(a1, 2)); // 2
		int[] a2 = { 3, 2, 6, 5, 0, 3 };
		System.out.println(maxProfit(a2, 2)); // 7
	}
}
