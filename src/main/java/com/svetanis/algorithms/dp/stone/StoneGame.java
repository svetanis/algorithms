package com.svetanis.algorithms.dp.stone;

// 877. Stone Game

public final class StoneGame {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static boolean stoneGame(int[] piles) {
		int n = piles.length;
		int[][] dp = init(piles);
		for (int start = n - 2; start >= 0; start--) {
			for (int end = start + 1; end < n; end++) {
				int left = piles[start] - dp[start + 1][end];
				int right = piles[end] - dp[start][end - 1];
				dp[start][end] = Math.max(left, right);
			}
		}
		return dp[0][n - 1] > 0 ? true : false;
	}

	private static int[][] init(int[] piles) {
		int n = piles.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = piles[i];
		}
		return dp;
	}

	public static void main(String[] args) {
		int[] a = { 5, 3, 4, 5 };
		System.out.println(stoneGame(a)); // true

		int[] a2 = { 3, 7, 2, 3 };
		System.out.println(stoneGame(a2)); // true
	}
}
