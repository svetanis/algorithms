package com.svetanis.algorithms.string.reorder;

// 1758. Minimum Changes To Make Alternating Binary String
//
// Input: s, a string of '0' and '1'. One operation changes one character to the other digit.
// Returns the fewest operations that make s alternating -- no two neighbours equal. On LeetCode:
// 1 <= s.length <= 10^4, s[i] is '0' or '1'.
//
// The one idea: only two alternating strings of length n exist, "0101..." and "1010...", so the
// answer is the smaller of the number of places s differs from each. count(s, bit) walks s with
// the digit the pattern needs at this place (bit, flipped after every character) and counts the
// places where s holds something else.
//
// Time: O(n) -- two passes over s.
// Space: O(n) -- toCharArray copies the string; the counters are O(1).
//
// Siblings: todo/string/MinChangesAlterStrBits counts one pattern only: each place mismatches
// exactly one of the two patterns, so the other count is n minus the first.

public final class MinChangesAlterStrSimple {

	public static int minOperations(String s) {
		return Math.min(count(s, '0'), count(s, '1')); // RETURN: the nearer of the two patterns
	}

	private static int count(String s, char bit) {
		int count = 0;
		for (char c : s.toCharArray()) {
			if (c != bit) {
				count++; // COUNT: s differs from the pattern here, one change
			}
			bit = bit == '0' ? '1' : '0'; // FLIP: the pattern alternates
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(minOperations("0100")); // 1
		System.out.println(minOperations("10")); // 0
		System.out.println(minOperations("1111")); // 2
	}
}
