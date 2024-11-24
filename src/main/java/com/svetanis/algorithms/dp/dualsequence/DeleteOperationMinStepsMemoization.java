package com.svetanis.algorithms.dp.dualsequence;

import static java.lang.Math.min;

// 583. Delete Operation for Two Strings 

public final class DeleteOperationMinStepsMemoization {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int delete(String x, String y) {
		int n = x.length();
		int m = y.length();
		Integer[][] dp = new Integer[n + 1][m + 1];
		return dfs(x, y, n, m, dp);
	}

	private static int dfs(String x, String y, int n, int m, Integer[][] dp) {
		if (n == 0 && m == 0) {
			return 0;
		}
		if (dp[n][m] != null) {
			return dp[n][m];
		}
		if (n == 0) {
			return dp[0][m] = 1 + dfs(x, y, n, m - 1, dp);
		}
		if (m == 0) {
			return dp[n][0] = 1 + dfs(x, y, n - 1, m, dp);
		}
		if (x.charAt(n - 1) == y.charAt(m - 1)) {
			dp[n][m] = dfs(x, y, n - 1, m - 1, dp);
		} else {
			int d1 = dfs(x, y, n - 1, m, dp);
			int d2 = dfs(x, y, n, m - 1, dp);
			dp[n][m] = 1 + min(d1, d2);
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(delete("sea", "eat")); // 2
		System.out.println(delete("leetcode", "etco")); // 4
	}
}
