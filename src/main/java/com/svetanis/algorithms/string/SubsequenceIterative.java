package com.svetanis.algorithms.string;

// 392. Is Subsequence
//
// Input: two strings s and t. A subsequence is what is left of a string after deleting some
// letters (maybe none) without changing the order of the rest; "ace" is a subsequence of "abcde",
// "aec" is not. Returns true when s is a subsequence of t. On LeetCode: 0 <= s.length <= 100,
// 0 <= t.length <= 10^4, lowercase letters.
//
// The one idea: match s against t greedily and never undo a match. One pointer marks the next
// letter of s still to be matched, another walks t; when the letters are equal the match is taken
// and the s pointer moves on. Taking the first available copy of a letter never hurts, because it
// leaves the longest part of t for the letters still to match. s is a subsequence exactly when
// every letter of s was matched.
//
// Time: O(m) -- one pass over t (m = t.length()).
// Space: O(1) -- two pointers.
//
// Siblings: SubsequenceRecursive is the same match as a recursion on the last letters (stack depth
// m). This file has three loop versions of the one method: subseq (while loop, from the left),
// isSubseq (for loop, from the left), isSubSequence (while loop, from the right, matching the last
// letters first).

public final class SubsequenceIterative {

	public static boolean subseq(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return true;
		}
		if (m == 0) {
			return false;
		}
		int first = 0; // next letter of s to match
		int second = 0; // current letter of t
		while (first < n && second < m) { // WALK t until s is matched or t runs out
			if (s.charAt(first) == t.charAt(second)) { // TAKE: match
				first++;
			}
			second++;
		}
		return first == n;
	}

	public static boolean isSubseq(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return true;
		}
		if (m == 0) {
			return false;
		}
		int index = 0; // next letter of s to match
		for (int i = 0; i < m && index < n; i++) { // WALK t until s is matched or t runs out
			if (s.charAt(index) == t.charAt(i)) { // TAKE: match
				index++;
			}
		}
		return index == n;
	}

	public static boolean isSubSequence(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return true;
		}
		if (m == 0) {
			return false;
		}
		int i = n - 1; // last unmatched letter of s
		int j = m - 1; // current letter of t, from the right
		int count = 0;
		while (i >= 0 && j >= 0) { // WALK t from the right
			if (s.charAt(i) == t.charAt(j)) { // TAKE: match the last letters
				i--;
				j--;
				count++;
			} else { // SKIP: drop this letter of t
				j--;
			}
		}
		return count == n;
	}

	public static void main(String[] args) {
		System.out.println(subseq("abc", "ahbgdc")); // true
		System.out.println(subseq("axc", "ahbgdc")); // false

		System.out.println(isSubseq("AXY", "ADXCPY")); // true
		System.out.println(isSubseq("AXY", "YADXCP")); // false
		System.out.println(isSubseq("gksrek", "geeksforgeeks")); // true

		System.out.println(isSubSequence("AXY", "ADXCPY")); // true
		System.out.println(isSubSequence("AXY", "YADXCP")); // false
		System.out.println(isSubSequence("gksrek", "geeksforgeeks")); // true
	}
}