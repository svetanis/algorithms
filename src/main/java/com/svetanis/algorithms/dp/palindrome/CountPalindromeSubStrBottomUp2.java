package com.svetanis.algorithms.dp.palindrome;

// 647. Palindromic Substrings

// Given a string, find the total number 
// of palindromic substrings in it. 

public final class CountPalindromeSubStrBottomUp2 {
	// Time Complexity: O(n^2)
	// Auxiliary Space O(n^2)

	public static int lps(String s) {
		int n = s.length();
		int count = 0;
		if (n == 0) {
			return 0;
		}
		boolean[][] dp = new boolean[n][n];
		// strings of length 1 are
		// palindrome of length 1
		for (int i = 0; i < n; ++i) {
			dp[i][i] = true;
			count += 1;
		}
		// check for substring of length 2
		for (int i = 0; i < n - 1; ++i) {
			dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
			count += (dp[i][i + 1] ? 1 : 0);
		}

		for (int len = 3; len <= n; len++) {
			for (int i = 0, j = i + len - 1; j < n; i++, j++) {
				dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
				count += (dp[i][j] ? 1 : 0);
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 7
		System.out.println(lps("cddpd")); // 7
		System.out.println(lps("pqr")); // 7
		System.out.println(lps("abc")); // 3
		System.out.println(lps("aaa")); // 6
	}
}