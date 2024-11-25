package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// Given a string, find the length of 
// its Longest Palindromic Substring (LPS). 
// In a palindromic string, elements 
// read the same backward and forward.

public final class LongestPalindromeSubStrLenRecursive {
	// Time Complexity: O(3^n)

	public static int lps(String s) {
		int n = s.length();
		return lps(s, 0, n - 1);
	}

	private static int lps(String s, int low, int high) {
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

		// if the first and last chars match
		if (s.charAt(low) == s.charAt(high)) {
			int len = high - low - 1;
			if (len == lps(s, low + 1, high - 1)) {
				return len + 2;
			}
		}

		// if first and last
		// chars don't match
		int left = lps(s, low + 1, high);
		int right = lps(s, low, high - 1);
		return max(left, right);
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 3
		System.out.println(lps("cddpd")); // 3
		System.out.println(lps("pqr")); // 1
	}
}