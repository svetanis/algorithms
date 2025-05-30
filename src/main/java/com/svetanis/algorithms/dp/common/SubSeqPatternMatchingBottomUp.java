package com.svetanis.algorithms.dp.common;

// 115. Distinct Subsequences

// Given a string and a pattern, 
// write a method to count the number of times 
// the pattern appears in the string as a subsequence.

public final class SubSeqPatternMatchingBottomUp {
	// Time Complexity: O(n * m)

	public static int count(String str, String pat) {
		int n = str.length();
		int m = pat.length();

		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (str.charAt(i - 1) == pat.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1]; // incl
				}
				dp[i][j] += dp[i - 1][j]; // excl
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(count("baxmx", "ax"));
		System.out.println(count("tomorrow", "tor"));
		System.out.println(count("rabbbit", "rabbit")); // 3
		System.out.println(count("babgbag", "bag")); // 5
	}
}