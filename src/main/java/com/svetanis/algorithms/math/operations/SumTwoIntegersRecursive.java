package com.svetanis.algorithms.math.operations;

// 371. Sum of Two Integers

// given two integers a and b
// sum the two integers without
// using the operators + and -

public final class SumTwoIntegersRecursive {
	// Time Complexity: O(1) -- at most 32 calls, one per bit of an int
	// (-1 + 1 takes all 32)
	// Space Complexity: O(1) -- at most 32 frames on the stack

	public static int sum(int x, int y) {
		if (y == 0) {
			return x;
		}
		// add without carrying
		int sum = x ^ y;
		// carry but don't add
		int carry = (x & y) << 1;
		return sum(sum, carry);
	}

	public static void main(String[] args) {
		System.out.println(sum(1, 2)); // 3
		System.out.println(sum(2, 3)); // 5
	}
}
