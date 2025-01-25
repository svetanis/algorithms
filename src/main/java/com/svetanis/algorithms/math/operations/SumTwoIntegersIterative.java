package com.svetanis.algorithms.math.operations;

// 371. Sum of Two Integers

// given two integers a and b
// sum the two integers without
// using the operators + and -

public final class SumTwoIntegersIterative {
	// Time Complexity: O(n)

	public static int sum(int x, int y) {
		while (y != 0) {
			int carry = x & y;
			x = x ^ y;
			y = carry << 1;
		}
		return x;
	}

	public static void main(String[] args) {
		System.out.println(sum(1, 2)); // 3
		System.out.println(sum(2, 3)); // 5
	}
}
