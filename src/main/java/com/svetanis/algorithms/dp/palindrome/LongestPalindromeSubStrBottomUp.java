package com.svetanis.algorithms.dp.palindrome;

// 5. Longest Palindromic Substring

public final class LongestPalindromeSubStrBottomUp {
	// Time Complexity: O(n^2)
	// Auxiliary Space O(n^2)

	public static String lps(String s) {
		int n = s.length();
		int max = 1;
		int start = 0;

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
				start = i;
				max = 2;
			}
		}

		for (int left = n - 1; left >= 0; left--) {
			for (int right = left + 1; right < n; right++) {
				boolean match = s.charAt(left) == s.charAt(right);
				if (dp[left + 1][right - 1] && match) {
					dp[left][right] = true;
					int len = right - left + 1;
					if (len > max) {
						max = len;
						start = left;
					}
				}
			}
		}
		return s.substring(start, start + max);
	}

	public static void main(String[] args) {
		String str = "forgeeksskeegfor";
		System.out.println(lps(str)); // geeksskeeg
	}
}