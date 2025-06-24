package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// 5. Longest Palindromic Substring

public final class LongestPalindromeSubStrExpandAroundCenter {
	// Time Complexity: O(n^2)
	// Auxiliary Space: O(1)

	public static String lps(String s) {
		int n = s.length();
		int start = 0;
		int end = 0;
		// one by one consider every char
		// as center point of even and
		// odd length palindromes
		for (int i = 0; i < n; ++i) {
			int odd = expand(s, i, i);
			int even = expand(s, i, i + 1);
			int len = max(odd, even);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private static int expand(String s, int left, int right) {
		int n = s.length();
		int low = left;
		int high = right;
		while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
			low--;
			high++;
		}
		return high - low - 1;
	}

	public static void main(String[] args) {
		System.out.println(lps("babad")); // bab / aba
		System.out.println(lps("cbbd")); // bb
		System.out.println(lps("aybabtu")); // bab
	}
}