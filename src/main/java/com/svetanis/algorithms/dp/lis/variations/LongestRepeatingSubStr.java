package com.svetanis.algorithms.dp.lis.variations;

// 1062. Longest Repeating Substring

public final class LongestRepeatingSubStr {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static int lrs(String s) {
		int max = 0;
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = i == 0 ? 1 : dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}

	public static int lrs2(String s) {
		int max = 0;
		int n = s.length();
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (s.charAt(i - 1) == s.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lrs2("abab")); // 2
		System.out.println(lrs2("aabba")); // 1
	}
}
