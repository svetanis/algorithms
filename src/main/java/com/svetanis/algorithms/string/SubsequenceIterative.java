package com.svetanis.algorithms.string;

// 392. Is Subsequence

// Given two strings s and t, 
// return true if s is a subsequence of t, 
// or false otherwise.

// A subsequence of a string is a new string 
// that is formed from the original string by 
// deleting some (can be none) of the characters 
// without disturbing the relative positions of 
// the remaining characters. 
// (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

public final class SubsequenceIterative {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static boolean subseq(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n == 0) {
			return true;
		}
		if (m == 0) {
			return false;
		}
		int first = 0;
		int second = 0;
		while (first < n && second < m) {
			if (s.charAt(first) == t.charAt(second)) {
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
		int index = 0;
		for (int i = 0; i < m && index < n; i++) {
			if (s.charAt(index) == t.charAt(i)) {
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
		int i = n - 1;
		int j = m - 1;
		int count = 0;
		while (i >= 0 && j >= 0) {
			if (s.charAt(i) == t.charAt(j)) {
				i--;
				j--;
				count++;
			} else {
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