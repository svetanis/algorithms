package com.svetanis.algorithms.string;

// 521. Longest Uncommon Subsequence

public final class LongestUncommonSubsequence {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int lus(String s1, String s2) {
		if (s1.equals(s2)) {
			return -1;
		}
		return Math.max(s1.length(), s2.length());
	}

	public static void main(String[] args) {
		System.out.println(lus("aba", "cdc")); // 3
		System.out.println(lus("aaa", "bbb")); // 3
		System.out.println(lus("aaa", "aaa")); // -1
	}
}