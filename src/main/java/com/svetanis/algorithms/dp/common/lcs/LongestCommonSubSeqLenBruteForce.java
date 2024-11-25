package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

// 1143. Longest Common Subsequence

public final class LongestCommonSubSeqLenBruteForce {
	// Time Complexity: O(2^n)

	public static int lcs(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		return lcs(s1, s2, n, m);
	}

	private static int lcs(String s1, String s2, int i, int j) {
		if (i == 0 || j == 0) {
			return 0;
		}
		if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
			return 1 + lcs(s1, s2, i - 1, j - 1);
		}
		int top = lcs(s1, s2, i - 1, j);
		int left = lcs(s1, s2, i, j - 1);
		return max(top, left);
	}

	public static void main(String[] args) {
		System.out.println(lcs("abcde", "ace")); // 3
		System.out.println(lcs("abc", "abc")); // 3
		System.out.println(lcs("abc", "def")); // 0
		System.out.println(lcs("abdca", "cbda")); // 3
		System.out.println(lcs("passport", "ppsspt")); // 5
		System.out.println(lcs("AGGTAB", "GXTXAYB")); // 4
	}
}
