package com.svetanis.algorithms.dp.common.lcs;

// 1143. Longest Common Subsequence

// LONGEST COMMON SUBSEQUENCE LENGTH

// in each iteration of outer loop we only, 
// need values from all columns of previous row. 
// we can just store two rows at a time and use them, 
// in that way used space will reduce from dp[m+1][n+1] to dp[2][n+1]

public final class LongestCommonSubSeqLenSpaceOptimized {
	// Time Complexity: O(n*m)
	// Space Complexity: (O(m)

	public static int lcs(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[] prev = new int[m + 1];
		for (int i = 1; i <= n; ++i) {
			int[] curr = new int[m + 1];
			for (int j = 1; j <= m; ++j) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					curr[j] = 1 + prev[j - 1];
				} else {
					curr[j] = Math.max(prev[j], curr[j - 1]);
				}
			}
			prev = curr;
		}
		return prev[m];
	}

	public static int lcs2(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int index = 0;
		int[][] dp = new int[2][m + 1];
		for (int i = 0; i <= n; ++i) {
			index = i & 1;
			for (int j = 0; j <= m; ++j) {
				if (i == 0 || j == 0) {
					dp[index][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[index][j] = 1 + dp[1 - index][j - 1];
				} else {
					dp[index][j] = Math.max(dp[1 - index][j], dp[index][j - 1]);
				}
			}
		}
		// len[n][m] contains length of LCS
		// for X[0 ... n-1] and Y[0 ... m-1]
		return dp[index][m];
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
