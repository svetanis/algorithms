package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

// 1035. Uncrossed Lines

public final class UncrossedLinesBottomUp {
	// Time Complexity: O(n*m)
	// Space Complexity: (O(n*m)

	public static int maxUncrossedLines(int[] a1, int[] a2) {
		int n = a1.length;
		int m = a2.length;
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (a1[i - 1] == a2[j - 1]) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[n][m];
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