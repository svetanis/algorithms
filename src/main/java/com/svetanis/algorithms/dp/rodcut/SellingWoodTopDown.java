package com.svetanis.algorithms.dp.rodcut;

// 2312. Selling Pieces of Wood

public final class SellingWoodTopDown {

	private int[][] profit;
	private Long[][] dp;

	public long sellingWood(int m, int n, int[][] prices) {
		this.profit = new int[m + 1][n + 1];
		for (int[] price : prices) {
			this.profit[price[0]][price[1]] = price[2];
		}
		this.dp = new Long[m + 1][n + 1];
		dfs(m, n);
		return dp[m][n];
	}

	private long dfs(int height, int width) {
		if (dp[height][width] != null) {
			return dp[height][width];
		}
		long max = profit[height][width];
		for (int r = 1; r <= height / 2; r++) {
			max = Math.max(max, dfs(r, width) + dfs(height - r, width));
		}
		for (int c = 1; c <= width / 2; c++) {
			max = Math.max(max, dfs(height, c) + dfs(height, width - c));
		}
		return dp[height][width] = max;
	}

	public static void main(String[] args) {
		SellingWoodTopDown sw = new SellingWoodTopDown();
		int[][] prices1 = { { 1, 4, 2 }, { 2, 2, 7 }, { 2, 1, 3 } };
		System.out.println(sw.sellingWood(3, 5, prices1)); // 19

		int[][] prices2 = { { 3, 2, 10 }, { 1, 4, 2 }, { 4, 1, 3 } };
		System.out.println(sw.sellingWood(4, 6, prices2)); // 32
	}
}
