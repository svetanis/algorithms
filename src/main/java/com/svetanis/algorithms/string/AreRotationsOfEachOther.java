package com.svetanis.algorithms.string;

// 796. Rotate String

public final class AreRotationsOfEachOther {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static boolean areRotation(String s, String goal) {
		if (s.length() != goal.length()) {
			return false;
		}
		String concat = s.concat(s);
		return concat.contains(goal);
	}

	public static void main(String[] args) {
		System.out.println(areRotation("abcde", "cdeab")); // true
		System.out.println(areRotation("abcde", "abced")); // false
	}
}
