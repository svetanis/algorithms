package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

public final class LongestCommonSubSeqLenMemoization {

	public static int lcs(String s1, String s2) {
		// Time Complexity: O(n * m)
		// Space Complexity: O(n * m)

		int n = s1.length();
		int m = s2.length();
		Integer[][] memo = new Integer[n + 1][m + 1];
		return lcs(s1, s2, n, m, memo);
	}

	private static int lcs(String s1, String s2, int i, int j, Integer[][] memo) {
		if (i == 0 || j == 0) {
			return 0;
		}
		if (memo[i][j] != null) {
			return memo[i][j];
		}
		if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
			return 1 + lcs(s1, s2, i - 1, j - 1, memo);
		}
		int top = lcs(s1, s2, i - 1, j, memo);
		int left = lcs(s1, s2, i, j - 1, memo);
		int max = max(top, left);
		memo[i][j] = max;
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lcs("abcde", "ace")); // 3
		System.out.println(lcs("abc", "def")); // 0
		System.out.println(lcs("abdca", "cbda")); // 3
		System.out.println(lcs("passport", "ppsspt")); // 5
	}
}
