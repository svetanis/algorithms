package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

// 1143. Longest Common Subsequence

public final class LongestCommonSubSeqLenTopDown {
	// Time Complexity: O(n*m)

	public static int lcs(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[n][m];
		return lcs(s1, s2, dp, 0, 0);
	}

	private static int lcs(String s1, String s2, int[][] dp, int i, int j) {
		int n = s1.length();
		int m = s2.length();
		if (i == n || j == m) {
			return 0;
		}
		if (dp[i][j] != 0) {
			return dp[i][j];
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			dp[i][j] = 1 + lcs(s1, s2, dp, i + 1, j + 1);
		} else {
			int top = lcs(s1, s2, dp, i + 1, j);
			int left = lcs(s1, s2, dp, i, j + 1);
			dp[i][j] = max(top, left);
		}
		return dp[i][j];
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
