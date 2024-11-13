package com.svetanis.algorithms.dp.common.scs;

import static java.lang.Math.min;

// 1092. Shortest Common Supersequence

// Given two sequences ‘s1’ and ‘s2’, 
// write a method to find the len of 
// the shortest sequence which has 
// ‘s1’ and ‘s2’ as subsequences.

public final class ShortestCommonSuperSeqLenBottomUp {
	// Time Complexity: O(n * m)

	public static int scs(String s1, String s2) {
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
					dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(scs("geek", "eke")); // 5
		System.out.println(scs("AGGTAB", "GXTXAYB")); // 9
		System.out.println(scs("abac", "cab")); // 5
		System.out.println(scs("aaaaaaaa", "aaaaaaaa")); // 8
	}
}