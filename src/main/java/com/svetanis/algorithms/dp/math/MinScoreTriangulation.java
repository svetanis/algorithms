package com.svetanis.algorithms.dp.math;

// 1039. Minimum Score Triangulation of Polygon

public final class MinScoreTriangulation {
	// Time Complexity: O(n)
	// Space Complexity: O(n^2)

	public static int minScore(int[] a) {
		int n = a.length;
		Integer[][] dp = new Integer[n][n];
		return dfs(a, 0, a.length - 1, dp);
	}

	private static int dfs(int[] a, int i, int j, Integer[][] dp) {
		if (i + 1 == j) {
			return 0;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}
		int min = Integer.MAX_VALUE;
		for (int k = i + 1; k < j; k++) {
			int score = a[i] * a[k] * a[j];
			score += dfs(a, i, k, dp) + dfs(a, k, j, dp);
			min = Math.min(min, score);
		}
		return dp[i][j] = min;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		System.out.println(minScore(a1)); // 6

		int[] a2 = { 3, 7, 4, 5 };
		System.out.println(minScore(a2)); // 144

		int[] a3 = { 1, 3, 1, 4, 1, 5 };
		System.out.println(minScore(a3)); // 13
	}
}
