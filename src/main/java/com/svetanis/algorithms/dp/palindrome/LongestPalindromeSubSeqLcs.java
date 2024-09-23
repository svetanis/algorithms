package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;
import static org.apache.commons.lang3.StringUtils.reverse;

public final class LongestPalindromeSubSeqLcs {

	public static String lps(String str) {
		// Time Complexity: O(n^2)
		// Space Complexity: (O(n^2)
		int n = str.length();
		String reversed = reverse(str);
		Integer[][] memo = new Integer[n + 1][n + 1];
		lcs(str, reversed, n, n, memo);
		// start from the right-most-bottom-most
		// corner and one by one store chars in lcs[]
		return reconstruct(str, reversed, memo, n, n);
	}

	private static int lcs(String s1, String s2, int i, int j, 
			Integer[][] memo) {
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

	private static String reconstruct(String s1, String s2, 
			Integer[][] dp, int n, int m) {
		if (n == 0 || m == 0) {
			return "";
		}
		// if last char of s1 and s2 matches
		if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
			// append current char to lcs of substring s1(0...n-1) and s2(0...m-1)
			return reconstruct(s1, s2, dp, n - 1, m - 1) + s1.charAt(n - 1);
		}
		// if top cell of current cell has more value than the left cell
		// then drop the current char of s1 and find lcs of substring
		// s1(0...n-2) and s2(0...m-1)
		if (dp[n - 1][m] > dp[n][m - 1]) {
			return reconstruct(s1, s2, dp, n - 1, m);
		}
		// if left cell of current cell has more value than the top cell
		// then drop the current char of s2 and find lcs of substring
		// s1(0...n-1) and s2(0...m-2)
		return reconstruct(s1, s2, dp, n, m - 1);
	}

	public static void main(String[] args) {
		System.out.println(lps("ABBDCACB")); // 5 - BCACB
	}
}