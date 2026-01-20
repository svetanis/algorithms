package com.svetanis.algorithms.dp.common.scs;

// 1092. Shortest Common Supersequence

public final class ShortestCommonSuperSeqBottomUp {
	// Time Complexity: O(n * m)

	public static String scs(String x, String y) {
		int[][] dp = length(x, y);
		return scs(x, y, dp);
	}

	private static String scs(String x, String y, int[][] dp) {
		int i = x.length();
		int j = y.length();
		StringBuilder sb = new StringBuilder();
		while (i > 0 && j > 0) {
			if (x.charAt(i - 1) == y.charAt(j - 1)) {
				sb.append(x.charAt(i - 1));
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				sb.append(y.charAt(j - 1));
				j--;
			} else {
				sb.append(x.charAt(i - 1));
				i--;
			}
		}
		while (i > 0) {
			sb.append(x.charAt(i - 1));
			i--;
		}
		while (j > 0) {
			sb.append(y.charAt(j - 1));
			j--;
		}
		return sb.reverse().toString();
	}

	private static int[][] length(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		System.out.println(scs("geek", "eke")); // gekek
		System.out.println(scs("AGGTAB", "GXTXAYB")); // AGXGTXAYB
		System.out.println(scs("ABCBDAB", "BDCABA")); // ABDCABDAB
		System.out.println(scs("abac", "cab")); // cabac
		System.out.println(scs("aaaaaaaa", "aaaaaaaa")); // aaaaaaaa
	}
}
