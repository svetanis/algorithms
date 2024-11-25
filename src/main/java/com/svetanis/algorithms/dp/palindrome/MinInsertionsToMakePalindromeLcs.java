package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;
import static org.apache.commons.lang3.StringUtils.reverse;

// 1312. Minimum Insertion Steps to Make a String Palindrome

public final class MinInsertionsToMakePalindromeLcs {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static int palindrome(String str) {
		int n = str.length();
		String reversed = reverse(str);
		// the output is length of string
		// minus length of lcs of
		// str and its reverse
		int len = lcs(str, reversed);
		return (n - len);
	}

	private static int lcs(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(palindrome("zzazz")); // 0
		System.out.println(palindrome("mbadm")); // 2
		System.out.println(palindrome("leetcode")); // 5
		System.out.println(palindrome("geeks")); // 3
	}
}