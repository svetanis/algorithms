package com.svetanis.algorithms.string.search;

// 459. Repeated Substring Pattern
//
// Input: a string s. Returns true if s is some shorter block written two or more times in a row.
// On LeetCode: 1 <= s.length <= 10^4, lowercase letters.
//
// The one idea: try every block. A block is a prefix of s, and it can only tile s if its length
// divides n. For each prefix (shorter than s) whose length divides n, write the prefix n / length
// times and compare with s. One prefix that rebuilds s is enough; none means false.
//
// Time: O(n^2) -- the prefix copies add up to O(n^2), and each of the divisors of n rebuilds and
// compares a string of n letters.
// Space: O(n) -- the prefix and the rebuilt string.
//
// Siblings: RepeatedSubstrPattern looks for s inside s + s instead, with no loop over blocks.

public final class RepeatedSubstrPatternBruteForce {

	public static boolean rsp(String s) {
		for (int i = 0; i < s.length(); i++) {
			String prefix = s.substring(0, i); // TRY: the first i letters as the block
			if (concat(prefix, s)) {
				return true;
			}
		}
		return false;
	}

	private static boolean concat(String prefix, String s) {
		if (prefix.length() == 0) {
			return false; // SKIP: an empty block builds nothing
		}
		if (s.length() % prefix.length() != 0) {
			return false; // SKIP: the block length must divide n
		}
		int n = s.length() / prefix.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(prefix);
		}
		return s.equals(sb.toString()); // RETURN: the block repeated is exactly s
	}

	public static void main(String[] args) {
		System.out.println(rsp("abab")); // true
		System.out.println(rsp("aba")); // false
		System.out.println(rsp("abcabcabcabc")); // true
	}
}
