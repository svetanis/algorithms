package com.svetanis.algorithms.string;

// CESES: Repetitions

public final class Repetitions {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int longestRepetition(String s) {
		int max = 1;
		int count = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				count += 1;
			} else {
				count = 1;
			}
			max = Math.max(max, count);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(longestRepetition("ATTCGGGA")); // 3
	}
}
