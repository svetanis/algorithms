package com.svetanis.algorithms.bits.xor;

// given two integers a and b
// sum the two integers without
// using the operators + and -

public final class SumTwoIntegersRecursive {

	public static int sum(int x, int y) {
		// Time Complexity: O(n)

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
		System.out.println(sum(2, 3));

		System.out.println(sum(1, 2));

	}
}
