package com.svetanis.algorithms.math;

// 1056. Confusing Number

public final class ConfusingNumber {
	// Time Complexity: O(log n)

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
		System.out.println(confusingNum(69));
		System.out.println(confusingNum(25));
		System.out.println(confusingNum(89));
	}
}