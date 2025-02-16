package com.svetanis.algorithms.dp.interval;

import java.util.Arrays;

// 664. Strange Printer

public final class StrangePrinter {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n^2)

	private static final int INFINITY = Integer.MAX_VALUE / 2;

	public static int strangePrinter(String s) {
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int[] row : dp) {
			Arrays.fill(row, INFINITY);
		}
		for (int i = n - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i][j - 1];
				} else {
					for (int k = i; k < j; k++) {
						int sum = dp[i][k] + dp[k + 1][j];
						dp[i][j] = Math.min(dp[i][j], sum);
					}
				}

			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(strangePrinter("aaabbb")); // 2
		System.out.println(strangePrinter("aba")); // 2
	}
}
