package com.svetanis.algorithms.dp.common.scs;

// 1092. Shortest Common Supersequence

public final class ShortestCommonSuperSeqLcsBottomUp {
	// Time Complexity: O(n * m)

	public static String scs(String x, String y) {
		int[][] dp = lcs(x, y);
		return scs(x, y, dp);
	}

	private static String scs(String x, String y, int[][] dp) {
		int i = x.length();
		int j = y.length();
		StringBuilder sb = new StringBuilder();
		while (i > 0 || j > 0) {
			if (i == 0) {
				sb.append(y.charAt(--j));
			} else if (j == 0) {
				sb.append(x.charAt(--i));
			} else {
				// move diagonally if chars match
				if (dp[i][j] == 1 + dp[i - 1][j - 1]) {
					sb.append(x.charAt(--i));
					--j;
				} else if (dp[i][j] == dp[i - 1][j]) {
					// move up if char from x is part of superseq
					sb.append(x.charAt(--i));
				} else {
					// move left if char from y is part of superseq
					sb.append(y.charAt(--j));
				}
			}
		}
		return sb.reverse().toString();
	}

	private static int[][] lcs(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		System.out.println(scs("geek", "eke"));
		System.out.println(scs("AGGTAB", "GXTXAYB"));
		System.out.println(scs("ABCBDAB", "BDCABA")); // ABDCABDAB
		System.out.println(scs("abac", "cab")); // cabac
		System.out.println(scs("aaaaaaaa", "aaaaaaaa")); // aaaaaaaa
		System.out.println(scs("bbbaaaba", "bbababbb")); // bbbaaababbb
	}
}
