package com.svetanis.algorithms.math.operations;

// 371. Sum of Two Integers

// given two integers a and b
// sum the two integers without
// using the operators + and -

public final class SumTwoIntegersIterative {
	// Time Complexity: O(n)

	public static int sum(int x, int y) {
		// iterate till there is no carry
		while (y != 0) {
			// carry now contains
			// common set bits of x and y
			int carry = x & y;
			// sum of bits of x and y where
			// at least one of the bits is not set
			x = x ^ y;
			// carry is shifted by one so that
			// adding it to x gives the required sum
			y = carry << 1;
		}
		return x;
	}

	public static void main(String[] args) {
		System.out.println(sum(1, 2)); // 3
		System.out.println(sum(2, 3)); // 5
	}
}
