package com.svetanis.algorithms.bits.xor;

// 136. Single Number

// in a non-empty array of integers,
// every number appears twice except
// for one, find that single number

// XOR of two same numbers returns zero
// XOR with zero returns the same number

public final class SingleNumber {
	// Time Complexity: O(n)

	public static int single(int[] a) {
		int xor = 0;
		for (int num : a) {
			xor = xor ^ num;
		}
		return xor;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 2, 1 };
		System.out.println(single(a1)); // 1

		int[] a2 = { 4, 1, 2, 1, 2 };
		System.out.println(single(a2)); // 4

		int[] a3 = { 1 };
		System.out.println(single(a3)); // 1
	}
}
