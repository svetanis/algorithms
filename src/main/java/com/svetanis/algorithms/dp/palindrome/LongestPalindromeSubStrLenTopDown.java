package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// Given a string, find the length of 
// its Longest Palindromic Substring (LPS). 
// In a palindromic string, elements 
// read the same backward and forward.

public final class LongestPalindromeSubStrLenTopDown {
	// Time Complexity: O(n^2)
	// Auxiliary Space O(n^2)

	public static int lps(String s) {
		int n = s.length();
		int[][] dp = new int[n][n];
		return lps(s, dp, 0, n - 1);
	}

	private static int lps(String s, int[][] dp, int low, int high) {
		if (low > high) {
			return 0;
		}
		// Base case 1:
		// if there is only 1 char
		if (low == high) {
			return 1;
		}
		// Base case 2:
		// if there are only 2 chars
		// and both are same
		if (s.charAt(low) == s.charAt(high) && low + 1 == high) {
			return 2;
		}
		if (dp[low][high] != 0) {
			return dp[low][high];
		}
		// if the first and last chars match
		if (s.charAt(low) == s.charAt(high)) {
			int len = high - low - 1;
			if (len == lps(s, dp, low + 1, high - 1)) {
				dp[low][high] = len + 2;
				return dp[low][high];
			}
		}

		// if first and last
		// chars don't match
		int left = lps(s, dp, low + 1, high);
		int right = lps(s, dp, low, high - 1);
		dp[low][high] = max(left, right);
		return dp[low][high];
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 3
		System.out.println(lps("cddpd")); // 3
		System.out.println(lps("pqr")); // 1
	}
}