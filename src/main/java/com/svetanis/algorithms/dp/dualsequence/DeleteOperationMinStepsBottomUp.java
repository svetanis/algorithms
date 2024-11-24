package com.svetanis.algorithms.dp.dualsequence;

import static java.lang.Math.min;

// 583. Delete Operation for Two Strings 

public final class DeleteOperationMinStepsBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int delete(String x, String y) {
		int n = x.length();
		int m = y.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i;
		}
		for (int j = 1; j <= y.length(); j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int left = dp[i - 1][j];
					int top = dp[i][j - 1];
					dp[i][j] = 1 + min(left, top);
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(delete("sea", "eat")); // 2
		System.out.println(delete("leetcode", "etco")); // 4
	}
}
