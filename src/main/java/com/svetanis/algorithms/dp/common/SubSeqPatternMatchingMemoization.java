package com.svetanis.algorithms.dp.common;

// 115. Distinct Subsequences

// Given a string and a pattern, 
// write a method to count the number of times 
// the pattern appears in the string as a subsequence.

public final class SubSeqPatternMatchingMemoization {
	// Time Complexity: O(n * m)

	public static int count(String str, String pat) {
		int n = str.length();
		int m = pat.length();
		Integer[][] dp = new Integer[n + 1][m + 1];
		return count(str, pat, n, m, dp);
	}

	private static int count(String str, String pat, int n, int m, Integer[][] dp) {
		if ((n == 0 && m == 0) || m == 0) {
			return 1;
		}
		if (n == 0) {
			return 0;
		}
		if (dp[n][m] != null) {
			return dp[n][m];
		}

		int incl = 0;
		if (str.charAt(n - 1) == pat.charAt(m - 1)) {
			incl = count(str, pat, n - 1, m - 1, dp);
		}
		int excl = count(str, pat, n - 1, m, dp);
		dp[n][m] = incl + excl;
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(count("baxmx", "ax"));
		System.out.println(count("tomorrow", "tor"));
		System.out.println(count("rabbbit", "rabbit")); // 3
		System.out.println(count("babgbag", "bag")); // 5
	}
}
