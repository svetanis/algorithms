package com.svetanis.algorithms.bits.xor;

import static com.svetanis.java.base.utils.Print.print;

// 260. Single Number III

// in a non-empty array of integers,
// every number appears exactly twice
// except two numbers that appear only once
// find the two numbers that appear
// only once

// XOR of everything is x ^ y, which has a 1 wherever x and y differ.
// Split the array on one such slot: x and y land in different halves,
// both copies of every pair land in the same half, so each half is 136

public final class SingleNumberIII {
	// Time Complexity: O(n)

	public static int[] single(int[] a) {
		// XOR of all the numbers
		int xor = 0;
		for (int i = 0; i < a.length; i++) {
			xor = xor ^ a[i];
		}
		// the lowest 1 of x ^ y: a slot where x and y differ, already a mask
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
		print(single(a1)); // [3,5]

		int[] a2 = { -1, 0 };
		print(single(a2)); // [-1,0]

		int[] a3 = { 0, 1 };
		print(single(a3)); // [1,0]

		int[] a4 = { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
		print(single(a4)); // [6,4]

		int[] a5 = { 2, 1, 3, 2 };
		print(single(a5)); // [3,1]
	}
}
