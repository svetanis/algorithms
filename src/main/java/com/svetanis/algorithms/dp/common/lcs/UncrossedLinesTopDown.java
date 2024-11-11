package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

// 1035. Uncrossed Lines

public final class UncrossedLinesTopDown {

	public static int maxUncrossedLines(int[] a1, int[] a2) {
		int n = a1.length;
		int m = a2.length;
		int[][] dp = new int[n][m];
		return dfs(a1, a2, dp, 0, 0);
	}

	private static int dfs(int[] a1, int[] a2, int[][] dp, int i, int j) {
		int n = a1.length;
		int m = a2.length;

		if (i == n || j == m) {
			return 0;
		}

		if (dp[i][j] != 0) {
			return dp[i][j];
		}

		if (a1[i] == a2[j]) {
			dp[i][j] = 1 + dfs(a1, a2, dp, i + 1, j + 1);
		} else {
			int top = dfs(a1, a2, dp, i + 1, j);
			int left = dfs(a1, a2, dp, i, j + 1);
			dp[i][j] = max(top, left);
		}
		return dp[i][j];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 2 };
		int[] a2 = { 1, 2, 4 };
		System.out.println(maxUncrossedLines(a1, a2)); // 2

		int[] a3 = { 2, 5, 1, 2, 5 };
		int[] a4 = { 10, 5, 2, 1, 5, 2 };
		System.out.println(maxUncrossedLines(a3, a4)); // 3

		int[] a5 = { 1, 3, 7, 1, 7, 5 };
		int[] a6 = { 1, 9, 2, 5, 1 };
		System.out.println(maxUncrossedLines(a5, a6)); // 2
	}
}
