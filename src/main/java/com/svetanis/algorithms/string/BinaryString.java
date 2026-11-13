package com.svetanis.algorithms.string;

// 1016. Binary String With Substrings Representing 1 To N
//
// Input: a binary string s and a positive integer n. Returns true if the binary form of every
// integer from 1 to n is a substring of s. On LeetCode: 1 <= s.length <= 1000, 1 <= n <= 10^9.
//
// The one idea: the binary form of x is the beginning of the binary form of 2x and of 2x + 1
// (x = 5 is "101", 10 is "1010", 11 is "1011"). So if every number in the upper half, n/2 + 1 up
// to n, is a substring, then every number up to n/2 is too -- each one is the front of a
// number in the upper half. Only the upper half has to be searched for, one contains() per number,
// counting down from n; the first missing number answers false.
//
// The cut-off at n > 1023 answers false without searching: it is a shortcut for large n, so that
// the loop never runs for hundreds of millions of numbers. It is safe because no string of at most
// 1000 characters can hold every number from 1 to 1024: the 10-bit numbers 513..1023 alone need at
// least 1014 characters, and any larger n needs everything 1024 needs.
//
// Time: O(m) per number checked, and at most 511 numbers are checked because n is capped at 1023
// Space: O(1) -- one binary string at a time, at most 10 characters

public final class BinaryString {

	public static boolean query(String s, int n) {
		if (n > 1023) {
			return false; // SKIP: the shortcut for large n, see the header
		}
		for (int num = n; num > n / 2; num--) { // WALK: the upper half of 1..n only
			String binary = Integer.toBinaryString(num);
			if (!s.contains(binary)) {
				return false; // RETURN: one missing number is enough
			}
		}
		return true; // RETURN: the upper half is there, so the lower half is there too
	}

	public static void main(String[] args) {
		System.out.println(query("0110", 3)); // true
		System.out.println(query("0110", 4)); // false
	}
}
