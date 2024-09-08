package com.svetanis.algorithms.bits.xor;

// given an array of n - 1 integers in
// the range from 1 to n, find the one
// number that is missing from the array

// XOR of two same numbers returns zero

public final class MissingNumber {

	public static int single(int[] a) {
		// Time Complexity: O(n)

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

	public static void main(String[] args) {
		int[] a = { 1, 5, 2, 6, 4 };
		System.out.println(single(a));
	}
}
