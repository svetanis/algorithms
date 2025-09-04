package com.svetanis.algorithms.dp.rodcut;

// 2312. Selling Pieces of Wood

public final class SellingWoodBottomUp {

	public long sellingWood(int m, int n, int[][] prices) {
		long[][] profit = new long[m + 1][n + 1];
		for (int[] price : prices) {
			profit[price[0]][price[1]] = price[2];
		}
		long[][] dp = new long[m + 1][n + 1];
		for (int height = 1; height <= m; height++) {
			for (int width = 1; width <= n; width++) {
				long max = profit[height][width];
				for (int cut = 1; cut <= height / 2; cut++) {
					max = Math.max(max, dp[cut][width] + dp[height - cut][width]);
				}
				for (int cut = 1; cut <= width / 2; cut++) {
					max = Math.max(max, dp[height][cut] + dp[height][width - cut]);
				}
				dp[height][width] = max;
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		SellingWoodBottomUp sw = new SellingWoodBottomUp();
		int[][] prices1 = { { 1, 4, 2 }, { 2, 2, 7 }, { 2, 1, 3 } };
		System.out.println(sw.sellingWood(3, 5, prices1)); // 19

		int[][] prices2 = { { 3, 2, 10 }, { 1, 4, 2 }, { 4, 1, 3 } };
		System.out.println(sw.sellingWood(4, 6, prices2)); // 32
	}
}
