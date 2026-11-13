package com.svetanis.algorithms.string;

// 522. Longest Uncommon Subsequence II
//
// Input: an array a of strings. An uncommon subsequence is a subsequence of one string of a that
// is not a subsequence of any other string of a. Returns the length of the longest one, or -1 if
// there is none. On LeetCode: 2 <= a.length <= 50, 1 <= a[i].length <= 10, lowercase letters.
//
// The one idea: the best candidate is always a whole string of a. If some subsequence u of a[i]
// is not inside any other string, then a[i] itself is not inside any other string either (if it
// were, u would be too) and a[i] is at least as long. So test each whole string: a[i] counts
// when no other a[j] has it as a subsequence. Equal strings count each other out, because a
// string is a subsequence of its twin. The answer is the longest string that passes.
//
// Time: O(n^2 * m) -- n strings against n others, a subsequence test of O(m) each, m the longest
// length.
// Space: O(1).

public final class LongestUncommonSubsequenceII {

	public static int lus(String[] a) {
		int n = a.length;
		int max = -1;
		for (int i = 0, j = 0; i < n; i++) { // WALK: each candidate a[i]
			for (j = 0; j < n; j++) {
				if (j == i) { // SKIP: itself
					continue;
				}
				if (isSubseq(a[i], a[j])) { // REJECT: a[i] lives inside a[j]
					break;
				}
			}
			if (j == n) { // TAKE: no other string holds a[i] (the loop never broke)
				max = Math.max(max, a[i].length());
			}
		}
		return max;
	}

	private static boolean isSubseq(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return true;
		}
		if (m == 0) {
			return false;
		}
		int index = 0; // index: the next character of s still to find in t
		for (int i = 0; i < m && index < n; i++) { // WALK: t, matching s from the left
			if (s.charAt(index) == t.charAt(i)) {
				index++;
			}
		}
		return index == n; // RETURN: every character of s was found, in order
	}

	public static void main(String[] args) {
		String[] a1 = { "aba", "cdc", "eae" };
		System.out.println(lus(a1)); // 3
		String[] a2 = { "aaa", "aaa", "aa" };
		System.out.println(lus(a2)); // -1
	}
}