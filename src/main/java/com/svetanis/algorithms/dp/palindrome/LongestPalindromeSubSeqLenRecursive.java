package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// 516. Longest Palindromic Subsequence

// Given a sequence, find the length of its 
// Longest Palindromic Subsequence (LPS). 
// In a palindromic subsequence, elements 
// read the same backward and forward.

public final class LongestPalindromeSubSeqLenRecursive {
	// Time Complexity: O(2^n)

	public static int lps(String str) {
		int n = str.length();
		return lps(str, 0, n - 1);
	}

	private static int lps(String str, int low, int high) {
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
		if (str.charAt(low) == str.charAt(high) && low + 1 == high) {
			return 2;
		}

		// if the first and last chars match
		if (str.charAt(low) == str.charAt(high)) {
			return 2 + lps(str, low + 1, high - 1);
		}

		// if first and last
		// chars don't match
		int left = lps(str, low + 1, high);
		int right = lps(str, low, high - 1);
		return max(left, right);
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 5
		System.out.println(lps("cddpd")); // 3
		System.out.println(lps("pqr")); // 1
		System.out.println(lps("bbbab")); // 4
		System.out.println(lps("cbbd")); // 2
	}
}