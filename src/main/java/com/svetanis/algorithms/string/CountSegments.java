package com.svetanis.algorithms.string;

// 434. Number of Segments in a String
//
// Input: a string s. A segment is a run of characters that are not spaces. Returns the number
// of segments. On LeetCode: 0 <= s.length <= 300; s holds letters, digits and "!@#$%^&*()_+-=',.:",
// and the only space character is ' '.
//
// The one idea: every segment has exactly one first character -- a non-space whose left neighbour
// is a space, or that sits at index 0. Count those characters instead of splitting the string.
// Runs of several spaces, and spaces at either end, then cost nothing extra.
//
// Time: O(n) -- one pass
// Space: O(1)

public final class CountSegments {

	public static int countSegments(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) { // START: first character of a run
				count++; // RECORD: one more segment
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countSegments("Hello, my name is John")); // 5
		System.out.println(countSegments("Hello")); // 1
		System.out.println(countSegments("                ")); // 0
	}
}
