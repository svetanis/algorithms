package com.svetanis.algorithms.string;

// 392. Is Subsequence
//
// Input: two strings s and t. A subsequence is what is left of a string after deleting some
// letters (maybe none) without changing the order of the rest; "ace" is a subsequence of "abcde",
// "aec" is not. Returns true when s is a subsequence of t. On LeetCode: 0 <= s.length <= 100,
// 0 <= t.length <= 10^4, lowercase letters.
//
// The one idea: the state (n, m) means "the first n letters of s against the first m letters of t".
// If the last letters are equal, match them and shrink both. If not, the last letter of t is no
// use to s, so drop it. Matching a letter greedily never hurts, because it leaves the most of t
// for the letters still to match. n = 0 means every letter of s was matched; m = 0 with n > 0
// means t ran out first.
//
// Known limit: one call per letter of t, so the recursion is m deep. LeetCode's t has at most
// 10^4 letters and runs fine; a t of about 10^5 letters overflows the default stack.
// SubsequenceIterative is the same match as a loop.
//
// Time: O(m) -- each call removes one letter of t (m = t.length()).
// Space: O(m) -- the recursion depth.
//
// Sibling: SubsequenceIterative (loops, O(1) space).

public final class SubsequenceRecursive {

	public static boolean isSubsequence(String s, String t) {
		int n = s.length();
		int m = t.length();
		return isSubSeq(s, t, n, m);
	}

	private static boolean isSubSeq(String s, String t, int n, int m) {
		if (n == 0) { // BASE: every letter of s matched
			return true;
		}
		if (m == 0) { // BASE: t ran out first
			return false;
		}
		// if chars of two strings are matching
		if (s.charAt(n - 1) == t.charAt(m - 1)) { // TAKE: match the last letters, shrink both
			return isSubSeq(s, t, n - 1, m - 1);
		}
		// if last chars are not matching
		return isSubSeq(s, t, n, m - 1); // SKIP: drop the last letter of t
	}

	public static void main(String[] args) {
		System.out.println(isSubsequence("AXY", "ADXCPY")); // true
		System.out.println(isSubsequence("AXY", "YADXCP")); // false
		System.out.println(isSubsequence("gksrek", "geeksforgeeks")); // true
	}
}