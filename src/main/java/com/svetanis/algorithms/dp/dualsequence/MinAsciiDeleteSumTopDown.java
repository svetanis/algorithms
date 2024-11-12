package com.svetanis.algorithms.dp.dualsequence;

import static java.lang.Math.min;

// 712. Minimum ASCII Delete Sum for Two Strings

public final class MinAsciiDeleteSumTopDown {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int delete(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		Integer[][] dp = new Integer[n + 1][m + 1];
		return dfs(s1, s2, n, m, dp);
	}

	private static int dfs(String x, String y, int n, int m, Integer[][] dp) {
		if (n == 0 && m == 0) {
			return 0;
		}
		if (dp[n][m] != null) {
			return dp[n][m];
		}
		if (n == 0) {
			dp[0][m] = y.charAt(m - 1) + dfs(x, y, n, m - 1, dp);
			return dp[0][m];
		}
		if (m == 0) {
			dp[n][0] = x.charAt(n - 1) + dfs(x, y, n - 1, m, dp);
			return dp[n][0];
		}
		if (x.charAt(n - 1) == y.charAt(m - 1)) {
			dp[n][m] = dfs(x, y, n - 1, m - 1, dp);
		} else {
			int d1 = x.charAt(n - 1) + dfs(x, y, n - 1, m, dp);
			int d2 = y.charAt(m - 1) + dfs(x, y, n, m - 1, dp);
			dp[n][m] = min(d1, d2);
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(delete("sea", "eat")); // 231
		System.out.println(delete("delete", "leet")); // 403
	}
}
