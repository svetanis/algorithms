package com.svetanis.algorithms.dp.common.lcs;

import static java.lang.Math.max;

public final class LongestCommonSubSeqLenRecursive {

	public static int lcs(String s1, String s2) {
		// Time Complexity: O(2^n)

		return lcs(s1, s2, 0, 0);
	}

	private static int lcs(String s1, String s2, int i, int j) {
		if (i == s1.length() || j == s2.length()) {
			return 0;
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			return 1 + lcs(s1, s2, i + 1, j + 1);
		}
		int top = lcs(s1, s2, i + 1, j);
		int left = lcs(s1, s2, i, j + 1);
		return max(left, top);
	}

	public static void main(String[] args) {
		System.out.println(lcs("abcde", "ace")); // 3
		System.out.println(lcs("abc", "def")); // 0
		System.out.println(lcs("abdca", "cbda")); // 3
		System.out.println(lcs("passport", "ppsspt")); // 5
	}
}
