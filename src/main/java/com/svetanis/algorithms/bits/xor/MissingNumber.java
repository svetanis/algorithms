package com.svetanis.algorithms.bits.xor;

// CSES: Missing Number

// given an array of n - 1 integers in
// the range from 1 to n, find the one
// number that is missing from the array

// XOR of two same numbers returns zero

public final class MissingNumber {
	// Time Complexity: O(n)

	public static int single(int[] a) {
		int xor = 1;
		int n = a.length + 1;
		// xor all numbers from 1 to n
		for (int i = 2; i <= n; i++) {
			xor = xor ^ i;
		}
		// xor all numbers in a[]
		for (int i = 0; i < a.length; i++) {
			xor = xor ^ a[i];
		}
		// return missing number
		return xor;
	}

	public static int missing(int n, int[] a) {
		int xor = 0;
		for (int i = 0; i < n - 1; i++) {
			// xor all numbers in a[]
			xor = xor ^ a[i];
			// xor all numbers from 1 to n - 1
			xor = xor ^ (i + 1);
		}
		xor ^= n;
		return xor;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 5, 2, 6, 4 };
		System.out.println(single(a1)); // 3
		System.out.println(missing(6, a1)); // 3

		int[] a2 = { 2, 3, 1, 5 };
		System.out.println(single(a2)); // 4
		System.out.println(missing(5, a2)); // 4
	}
}
