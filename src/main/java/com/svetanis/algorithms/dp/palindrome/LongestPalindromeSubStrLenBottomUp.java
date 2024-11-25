package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// Given a string, find the length of 
// its Longest Palindromic Substring (LPS). 
// In a palindromic string, elements 
// read the same backward and forward.

public final class LongestPalindromeSubStrLenBottomUp {
	// Time Complexity: O(n^2)
	// Auxiliary Space O(n^2)

	public static int lps(String s) {
		int n = s.length();
		boolean[][] dp = new boolean[n][n];

		// strings of length 1 are
		// palindrome of length 1
		for (int i = 0; i < n; ++i) {
			dp[i][i] = true;
		}

		// check for substring of length 2
		for (int i = 0; i < n - 1; ++i) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				dp[i][i + 1] = true;
			}
		}

		int max = 1;
		for (int start = n - 1; start >= 0; start--) {
			for (int end = start + 1; end < n; end++) {
				boolean match = s.charAt(start) == s.charAt(end);
				if (dp[start + 1][end - 1] && match) {
					dp[start][end] = true;
					max = max(max, end - start + 1);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 3
		System.out.println(lps("cddpd")); // 3
		System.out.println(lps("pqr")); // 1
	}
}