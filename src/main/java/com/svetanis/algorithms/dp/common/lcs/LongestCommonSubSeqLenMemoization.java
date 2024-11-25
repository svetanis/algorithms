package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

// 1143. Longest Common Subsequence

public final class LongestCommonSubSeqLenMemoization {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int lcs(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		Integer[][] dp = new Integer[n + 1][m + 1];
		return lcs(s1, s2, n, m, dp);
	}

	private static int lcs(String s1, String s2, int i, int j, Integer[][] dp) {
		if (i == 0 || j == 0) {
			dp[i][j] = 0;
			return 0;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}
		if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
			dp[i][j] = 1 + lcs(s1, s2, i - 1, j - 1, dp);
			return dp[i][j];
		}
		int top = lcs(s1, s2, i - 1, j, dp);
		int left = lcs(s1, s2, i, j - 1, dp);
		int max = max(top, left);
		return dp[i][j] = max;
	}

	public static void main(String[] args) {
		System.out.println(lcs("abcde", "ace")); // 3
		System.out.println(lcs("abc", "abc")); // 3
		System.out.println(lcs("abc", "def")); // 0
		System.out.println(lcs("abdca", "cbda")); // 3
		System.out.println(lcs("passport", "ppsspt")); // 5
		System.out.println(lcs("AGGTAB", "GXTXAYB")); // 4
	}
}
