package com.svetanis.algorithms.math;

// 1056. Confusing Number

// Turn the number upside down one digit at a time, from its last digit,
// which becomes the first. It is confusing when every digit survives the
// turn and the result is a different number.

public final class ConfusingNumber {
	// Time Complexity: O(log n), one pass per digit
	// Space Complexity: O(1)

	// ROTATION[d] is d turned upside down; -1 when it turns into no digit
	// (2, 3, 4, 5, 7)
	private static final int[] ROTATION = { 0, 1, -1, -1, -1, -1, 9, -1, 8, 6 };

	public static boolean confusingNum(int n) {
		int original = n;
		int rotated = 0;
		while (original > 0) {
			int curr = original % 10;
			if (ROTATION[curr] < 0) {
				return false;
			}
			rotated = rotated * 10 + ROTATION[curr];
			original /= 10;
		}
		return rotated != n;
	}

	public static void main(String[] args) {
		System.out.println(confusingNum(69)); // false, 69 turns into 69
		System.out.println(confusingNum(25)); // false, 2 has no upside down
		System.out.println(confusingNum(89)); // true, 89 turns into 68
		System.out.println(confusingNum(6)); // true, 6 turns into 9
	}
}