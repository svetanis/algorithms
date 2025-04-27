package com.svetanis.algorithms.dp.interval;

// 486. Predict the Winner

public final class PredictTheWinner {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static boolean game(int[] scores) {
		int n = scores.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = scores[i];
		}
		for (int start = n - 2; start >= 0; start--) {
			for (int end = start + 1; end < n; end++) {
				int first = scores[start] - dp[start + 1][end];
				int second = scores[end] - dp[start][end - 1];
				dp[start][end] = Math.max(first, second);
			}
		}
		return dp[0][n - 1] >= 0;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 5, 2 };
		System.out.println(game(a1)); // false
		int[] a2 = { 1, 5, 233, 7 };
		System.out.println(game(a2)); // true
	}
}
