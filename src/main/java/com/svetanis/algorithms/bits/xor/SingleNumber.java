package com.svetanis.algorithms.bits.xor;

// in a non-empty array of integers,
// every number appears twice except
// for one, find that single number

// XOR of two same numbers returns zero
// XOR with zero returns the same number

public final class SingleNumber {

	public static int single(int[] a) {
		// Time Complexity: O(n)

		int xor = 0;
		for (int i = 0; i < a.length; i++) {
			xor = xor ^ a[i];
		}
		return xor;
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 1, 3, 2, 3 };
		System.out.println(single(a));

		int[] a2 = { 7, 9, 7 };
		System.out.println(single(a2));

	}
}
