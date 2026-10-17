package com.svetanis.algorithms.twopointers;

// 165. Compare Version Numbers
//
// Input: two version strings, revisions of digits separated by dots, like "1.01.2".
// A revision's value is its integer value, so leading zeros do not count ("01" is 1),
// and a revision missing from the shorter version counts as 0 ("1.0" equals "1").
// Return: -1 if version1 is smaller, 1 if it is larger, otherwise 0, comparing the
// revisions from left to right.
//
// The one idea: walk both strings at once, one revision at a time. Each pointer reads
// digits up to the next dot or the end, building that revision's value; a pointer
// already past its end reads nothing and leaves 0, which is exactly the rule that a
// missing revision counts as 0. The first pair of unequal revisions decides.
// Building the value digit by digit drops leading zeros with no extra work.
//
// Time: O(n + m) -- each character of either string is read once.
// Space: O(1) -- the strings are never split into arrays of revisions.
// A revision fits in int because the statement guarantees 32-bit revisions.

public final class CompareVersionNumbers {

	public static int compare(String v1, String v2) {
		int n = v1.length();
		int m = v2.length();
		// STOP: both used up; MOVE: i++ and j++ step over the dots
		for (int i = 0, j = 0; i < n || j < m; i++, j++) {
			int num1 = 0, num2 = 0;                      // START: a missing revision counts as 0
			while (i < n && v1.charAt(i) != '.') {
				num1 = num1 * 10 + (v1.charAt(i) - '0'); // READ one digit of version1's revision
				i++;
			}
			while (j < m && v2.charAt(j) != '.') {
				num2 = num2 * 10 + (v2.charAt(j) - '0'); // READ one digit of version2's revision
				j++;
			}
			if (num1 != num2) {
				return num1 < num2 ? -1 : 1;             // FOUND: the first unequal revisions decide
			}
		}
		return 0;                                        // RETURN 0: every revision is equal
	}

	public static void main(String[] args) {
		System.out.println(compare("1.2", "1.10"));    // -1
		System.out.println(compare("1.01", "1.001"));  // 0
		System.out.println(compare("1.0", "1.0.0.0")); // 0
	}
}
