package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;
import static org.apache.commons.lang3.StringUtils.reverse;

//516. Longest Palindromic Subsequence

public final class LongestPalindromeSubSeqLenLcs {
	// Time Complexity: O(n^2)
	// Space Complexity: (O(n^2)

	public static int lps(String str) {
		int n = str.length();
		String reversed = reverse(str);
		Integer[][] memo = new Integer[n + 1][n + 1];
		return lcs(str, reversed, n, n, memo);
	}

	private static int lcs(String s1, String s2, int i, int j, Integer[][] memo) {
		if (i == 0 || j == 0) {
			memo[i][j] = 0;
			return 0;
		}
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
			memo[i][j] = 1 + lcs(s1, s2, i - 1, j - 1, memo);
			return memo[i][j];
		}
		int top = lcs(s1, s2, i - 1, j, memo);
		int left = lcs(s1, s2, i, j - 1, memo);
		int max = max(top, left);
		memo[i][j] = max;
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 5
		System.out.println(lps("cddpd")); // 3
		System.out.println(lps("pqr")); // 1
		System.out.println(lps("bbbab")); // 4
		System.out.println(lps("cbbd")); // 2
	}
}