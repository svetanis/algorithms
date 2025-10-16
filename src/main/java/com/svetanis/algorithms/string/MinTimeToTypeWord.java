package com.svetanis.algorithms.string;

// 1974. Minimum Time to Type Word Using Special Typewriter

public final class MinTimeToTypeWord {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minTimeToType(String word) {
		int total = word.length();
		int prev = 0;
		for (char c : word.toCharArray()) {
			int curr = c - 'a';
			int clockwise = Math.abs(curr - prev);
			int counterclockwise = 26 - clockwise;
			total += Math.min(counterclockwise, clockwise);
			prev = curr;
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(minTimeToType("abc")); // 5
		System.out.println(minTimeToType("bza")); // 7
		System.out.println(minTimeToType("zjpc")); // 34
	}
}
