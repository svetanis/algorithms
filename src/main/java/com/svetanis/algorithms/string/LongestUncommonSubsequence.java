package com.svetanis.algorithms.string;

// 521. Longest Uncommon Subsequence I
//
// Input: two strings a and b. An uncommon subsequence is a subsequence of one string that is not
// a subsequence of the other. Returns the length of the longest one, or -1 if there is none.
// On LeetCode: 1 <= a.length, b.length <= 100, lowercase letters.
//
// The one idea: take a whole string. If the strings differ, the longer one (either one, if they
// have the same length) is a subsequence of itself, and it cannot be a subsequence of the other:
// a subsequence of a string that is no longer than it can only be the string itself, and the
// strings differ. Nothing longer exists, since every candidate is inside one of the two. If the
// strings are equal, every subsequence of one is a subsequence of the other, so the answer is -1.
//
// Time: O(min(n, m)) -- the equality test stops at the first difference or the shorter length;
// the lengths are read in O(1).
// Space: O(1).

public final class LongestUncommonSubsequence {

	public static int lus(String s1, String s2) {
		if (s1.equals(s2)) { // SAME: every subsequence is shared
			return -1;
		}
		return Math.max(s1.length(), s2.length()); // RETURN: the longer string itself
	}

	public static void main(String[] args) {
		System.out.println(lus("aba", "cdc")); // 3
		System.out.println(lus("aaa", "bbb")); // 3
		System.out.println(lus("aaa", "aaa")); // -1
	}
}