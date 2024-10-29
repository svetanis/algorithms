package com.svetanis.algorithms.bits.xor;

import static com.svetanis.algorithms.bits.Bits.rightMostSetBit;

import com.svetanis.java.base.Pair;

// in a non-empty array of integers,
// every number appears exactly twice
// except two numbers that appear only once
// find the two numbers that appear
// only once

public final class TwoSingleNumbers {

	public static Pair<Integer, Integer> single(int[] a) {
		// Time Complexity: O(n)

		// XOR of all the numbers
		int xor = 0;
		for (int i = 0; i < a.length; i++) {
			xor = xor ^ a[i];
		}
		// rightmost bit that is 1
		int rmb = rightMostSetBit(xor);
		int x = 0, y = 0;
		for (int i = 0; i < a.length; i++) {
			// the rmb is set
			if ((a[i] & rmb) != 0) {
				x ^= a[i];
			} else { // the rmb is not set
				y ^= a[i];
			}
		}
		return Pair.build(x, y);
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
		System.out.println(single(a));

		int[] a2 = { 2, 1, 3, 2 };
		System.out.println(single(a2));

	}
}
