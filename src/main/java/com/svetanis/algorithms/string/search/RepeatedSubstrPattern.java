package com.svetanis.algorithms.string.search;

// 459. Repeated Substring Pattern
//
// Input: a string s. Returns true if s is some shorter block written two or more times in a row.
// On LeetCode: 1 <= s.length <= 10^4, lowercase letters.
//
// The one idea: look for s inside s + s, ignoring the two obvious finds at index 0 and at index
// n. If s is a block b written k >= 2 times, then s + s is b written 2k times, and s also starts
// at index |b|. If s starts in s + s at some index p with 0 < p < n, then s is s read from p
// onward and then wrapped round to its front, so s equals itself moved by p letters, which can
// only happen when s is a repeated block. rsp asks for the first find at or after index 1 and
// checks it is before n; rsp2 cuts off the first and last letter of s + s so that neither obvious
// find can appear, and asks whether s is still inside.
//
// Time: O(n^2) worst case -- indexOf and contains are plain scans of the 2n letters, trying each
// start. An O(n) answer needs the KMP prefix table.
// Space: O(n) -- the doubled string.
//
// Siblings: RepeatedSubstrPatternBruteForce tries every block length that divides n and rebuilds
// s from the block.

public final class RepeatedSubstrPattern {

	public static boolean rsp(String s) {
		String concat = s.concat(s); // DOUBLE: s + s
		if (concat.indexOf(s, 1) < s.length()) { // FIND: the first find after index 0 must be before n
			return true;
		}
		return false;
	}

	public static boolean rsp2(String s) {
		String concat = s.concat(s); // DOUBLE: s + s
		String substr = concat.substring(1, concat.length() - 1); // CUT: drop the finds at 0 and at n
		if (substr.contains(s)) { // FIND: any find left is a shifted copy
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(rsp2("abab")); // true
		System.out.println(rsp2("aba")); // false
		System.out.println(rsp2("abcabcabcabc")); // true
		System.out.println(rsp("abab")); // true
		System.out.println(rsp("aba")); // false
		System.out.println(rsp("abcabcabcabc")); // true
	}
}
