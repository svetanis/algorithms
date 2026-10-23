package com.svetanis.algorithms.slidingwindow.string;

import java.util.Arrays;

// 1358. Number of Substrings Containing All Three Characters
//
// Input: a string made only of the letters a, b and c (any other letter indexes
// outside the three counts and throws).
// Return: how many substrings contain at least one a, one b and one c.
//
// The one idea (countSubStrs): containing all three survives growth -- once
// left..right holds an a, a b and a c, every longer substring starting at left
// holds them too. So the first time the window from left is complete, every end
// from right to the last index gives a valid substring: s.length() - right of
// them, counted in one step. Then left moves on, and the loop keeps doing that
// while the window is still complete. Each start is counted once, at the first
// end that completes it.
//
// The one idea (countSubStrs2): count by end instead of by start. For a fixed end
// right, a substring contains all three exactly when it starts at or before the
// last position of each letter, that is at or before the smallest of the three
// last positions. That gives (smallest last position + 1) starts. A letter not
// yet seen has last position -1, which gives 0.
//
// Siblings: none -- no other file in the repo solves 1358. The two methods above
//   are its two solutions.
//
// Time: O(n) for both -- countSubStrs adds each letter once and removes it at
//   most once; countSubStrs2 does constant work per letter.
// Space: O(1) -- three counts or three positions.

public final class CountSubStrs3Chars {

	public static int countSubStrs(String s) {
		int[] counts = new int[3];
		int total = 0;
		int left = 0;
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			counts[c - 'a']++; // ADD
			while (hasAllChars(counts)) { // SHRINK: while the window still holds all three
				total += s.length() - right; // COUNT: start left with every end from right to the last
				char lc = s.charAt(left++);
				counts[lc - 'a']--;
			}
		}
		return total;
	}

	private static boolean hasAllChars(int[] counts) {
		return counts[0] > 0 && counts[1] > 0 && counts[2] > 0;
	}

	public static int countSubStrs2(String s) {
		int[] counts = new int[3]; // the last position of a, b and c -- positions, not counts
		Arrays.fill(counts, -1); // START: no letter seen yet
		int total = 0;
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			counts[c - 'a'] = right; // RECORD: c was last seen at right
			int left = Math.min(counts[0], Math.min(counts[1], counts[2])); // the latest start holding all three
			total += left + 1; // COUNT: starts 0..left, each ending at right
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(countSubStrs("abcabc")); // 10
		System.out.println(countSubStrs("aaacb")); // 3
		System.out.println(countSubStrs("abc")); // 1
	}
}
