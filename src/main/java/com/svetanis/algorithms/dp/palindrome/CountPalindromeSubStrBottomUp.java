package com.svetanis.algorithms.dp.palindrome;

// 647. Palindromic Substrings

// Given a string, find the total number 
// of palindromic substrings in it. 

public final class CountPalindromeSubStrBottomUp {
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
			count++;
		}
		// check for substring of length 2
		for (int i = 0; i < n - 1; ++i) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				dp[i][i + 1] = true;
				count++;
			}
		}

		for (int start = n - 1; start >= 0; start--) {
			for (int end = start + 1; end < n; end++) {
				if (dp[start + 1][end - 1] && s.charAt(start) == s.charAt(end)) {
					dp[start][end] = true;
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 7
		System.out.println(lps("cddpd")); // 7
		System.out.println(lps("pqr")); // 3
		System.out.println(lps("abc")); // 3
		System.out.println(lps("aaa")); // 6
	}
}