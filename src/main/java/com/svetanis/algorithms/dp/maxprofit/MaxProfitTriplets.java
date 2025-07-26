package com.svetanis.algorithms.dp.maxprofit;

// 2907. Maximum Profitable Triplets With Increasing Prices I

public final class MaxProfitTriplets {
	// Time Complexity: O(n^2)
	// Space Complexity: O(1)

	public static int maxProfit(int[] prices, int[] profits) {
		int max = -1;
		int n = profits.length;
		for (int j = 0; j < n; j++) {
			int left = 0;
			int right = 0;
			for (int i = 0; i < j; i++) {
				if (prices[i] < prices[j] && left < profits[i]) {
					left = profits[i];
				}
			}

			for (int k = j + 1; k < n; k++) {
				if (prices[j] < prices[k] && right < profits[k]) {
					right = profits[k];
				}
			}
			if (left > 0 && right > 0) {
				int profit = left + profits[j] + right;
				max = Math.max(max, profit);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] prices = { 3, 1, 4, 2 };
		int[] profits = { 4, 1, 5, 3 };
		System.out.println(maxProfit(prices, profits));
	}
}
