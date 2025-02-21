package com.svetanis.algorithms.bits.xor;

// 260. Single Number III

// in a non-empty array of integers,
// every number appears exactly twice
// except two numbers that appear only once
// find the two numbers that appear
// only once

public final class TwoSingleNumbers {
	// Time Complexity: O(n)

	public static int[] single(int[] a) {
		// XOR of all the numbers
		int xor = 0;
		for (int i = 0; i < a.length; i++) {
			xor = xor ^ a[i];
		}
		// rightmost bit that is 1
		int rmb = xor & ~(xor - 1);
		int x = 0, y = 0;
		for (int i = 0; i < a.length; i++) {
			// the rmb is set
			if ((a[i] & rmb) != 0) {
				x ^= a[i];
			} else { // the rmb is not set
				y ^= a[i];
			}
		}
		return new int[] { x, y };
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1, 3, 2, 5 };
		System.out.println(single(a1)); // [3,5]

		int[] a2 = { -1, 0 };
		System.out.println(single(a2)); // [-1,0]

		int[] a3 = { 0, 1 };
		System.out.println(single(a3)); // [1,0]

		int[] a4 = { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
		System.out.println(single(a4));

		int[] a5 = { 2, 1, 3, 2 };
		System.out.println(single(a5));
	}
}
