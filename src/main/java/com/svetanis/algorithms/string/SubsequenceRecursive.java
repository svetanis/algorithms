package com.svetanis.algorithms.string;

// Given two strings s and t, 
// return true if s is a subsequence of t, 
// or false otherwise.

// A subsequence of a string is a new string 
// that is formed from the original string by 
// deleting some (can be none) of the characters 
// without disturbing the relative positions of 
// the remaining characters. 
// (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

public final class SubsequenceRecursive {

	public static boolean isSubsequence(String s, String t) {
		int n = s.length();
		int m = t.length();
		return isSubSeq(s, t, n, m);
	}

	private static boolean isSubSeq(String s, String t, int n, int m) {
		if (n == 0) {
			return true;
		}
		if (m == 0) {
			return false;
		}
		// if chars of two strings are matching
		if (s.charAt(n - 1) == t.charAt(m - 1)) {
			return isSubSeq(s, t, n - 1, m - 1);
		}
		// if last chars are not matching
		return isSubSeq(s, t, n, m - 1);
	}

	public static void main(String[] args) {
		System.out.println(isSubsequence("AXY", "ADXCPY")); // true
		System.out.println(isSubsequence("AXY", "YADXCP")); // false
		System.out.println(isSubsequence("gksrek", "geeksforgeeks")); // true
	}
}