package com.svetanis.algorithms.string;

// 796. Rotate String
//
// Input: strings s and goal. A shift moves the leftmost character of s to the right end. Returns
// true if some number of shifts turns s into goal. On LeetCode: 1 <= s.length, goal.length <= 100,
// both lowercase letters.
//
// The one idea: a rotation of s is s cut at one place and the two pieces swapped, so it reads
// s[k..n-1] followed by s[0..k-1]. Write s twice, s + s: starting at index k, the next n characters
// of s + s are exactly that rotation. So goal is a rotation of s exactly when it is the same
// length as s and appears somewhere inside s + s. The length check matters: "ab" appears inside
// "abab", but a goal shorter or longer than s is never a rotation of it.
//
// Time: O(n^2) -- String.contains compares goal against up to n starting places, n characters each
// Space: O(n) -- the doubled string

public final class AreRotationsOfEachOther {

	public static boolean areRotation(String s, String goal) {
		if (s.length() != goal.length()) {
			return false; // SKIP: different lengths can never be rotations
		}
		String concat = s.concat(s); // DOUBLE: every rotation of s is a window of s + s
		return concat.contains(goal); // RETURN: goal is one of those windows
	}

	public static void main(String[] args) {
		System.out.println(areRotation("abcde", "cdeab")); // true
		System.out.println(areRotation("abcde", "abced")); // false
	}
}
