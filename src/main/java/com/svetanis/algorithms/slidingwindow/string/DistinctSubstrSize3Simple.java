package com.svetanis.algorithms.slidingwindow.string;

// 1876. Substrings of Size Three with Distinct Characters
//
// Input: a string.
// Return: how many substrings of exactly three characters have three different
// characters. Each position counts separately, so a repeated substring is counted
// every time it occurs.
//
// The one idea: the window is only three characters wide, so there is nothing to
// maintain -- at each start, compare the three characters pairwise. Three
// comparisons decide it.
//
// Siblings: DistinctSubstrSize3 -- the same count with a map of last positions and
//   a window whose left edge jumps past a repeat, as in LeetCode 3.
//
// Time: O(n) -- three comparisons per start.
// Space: O(1).

public final class DistinctSubstrSize3Simple {

	public static int count(String s) {
		int count = 0;
		for (int i = 0; i < s.length() - 2; i++) { // STOP: the last window of three starts at n - 3
			char first = s.charAt(i);
			char second = s.charAt(i + 1);
			char third = s.charAt(i + 2);
			if (first != second && first != third && second != third) {
				count++; // COUNT: all three differ
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String s1 = "xyzzaz";
		System.out.println(count(s1)); // 1

		String s2 = "aababcabc";
		System.out.println(count(s2)); // 4

		String s3 = "owuxoelszb";
		System.out.println(count(s3)); // 8
	}
}
