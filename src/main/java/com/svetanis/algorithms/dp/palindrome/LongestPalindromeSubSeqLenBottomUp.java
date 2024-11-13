package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// 516. Longest Palindromic Subsequence

// Given a sequence, find the length of its 
// Longest Palindromic Subsequence (LPS). 
// In a palindromic subsequence, elements 
// read the same backward and forward.

public final class LongestPalindromeSubSeqLenBottomUp {
	// Time Complexity: O(n^2)
	// Space Complexity: (O(n^2)

	public static int lps(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];

		// strings of length 1 are
		// palindrome of length 1
		for (int i = 0; i < n; ++i) {
			dp[i][i] = 1;
		}

		// build the table.
		// the lower diagonal values
		// of table are useless and
		// not filled in the process.
		// the values are filled in
		// a manner similar to
		// MatrixChainMultiplicationDP
		for (int start = n - 1; start >= 0; start--) {
			for (int end = start + 1; end < n; end++) {
				if (str.charAt(start) == str.charAt(end)) {
					dp[start][end] = 2 + dp[start + 1][end - 1];
				} else {
					dp[start][end] = max(dp[start + 1][end], dp[start][end - 1]);
				}
			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 5
		System.out.println(lps("cddpd")); // 3
		System.out.println(lps("pqr")); // 1
		System.out.println(lps("bbbab")); // 4
		System.out.println(lps("cbbd")); // 2
	}
}