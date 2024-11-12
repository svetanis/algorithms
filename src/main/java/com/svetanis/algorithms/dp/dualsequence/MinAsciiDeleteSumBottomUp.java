package com.svetanis.algorithms.dp.dualsequence;

import static java.lang.Math.min;

// 712. Minimum ASCII Delete Sum for Two Strings

public final class MinAsciiDeleteSumBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int delete(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[n + 1][m + 1];

		// first column
		for (int i = 1; i <= n; i++) {
			dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
		}
		// first row
		for (int j = 1; j <= m; j++) {
			dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int d1 = s1.charAt(i - 1) + dp[i - 1][j];
					int d2 = s2.charAt(j - 1) + dp[i][j - 1];
					dp[i][j] = min(d1, d2);
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(delete("sea", "eat")); // 231
		System.out.println(delete("delete", "leet")); // 403
	}
}
