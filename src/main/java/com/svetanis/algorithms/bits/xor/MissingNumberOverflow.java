package com.svetanis.algorithms.bits.xor;

// given an array of n - 1 integers in
// the range from 1 to n, find the one
// number that is missing from the array

// potential integer overflow
// when n is large

public final class MissingNumberOverflow {

	public static int single(int[] a) {
		// Time Complexity: O(n)

		int n = a.length + 1;
		// sum of all numbers from 1 to n
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		// subtract all numbers in a[] from sum
		for (int i = 0; i < a.length; i++) {
			sum -= a[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a = { 1, 5, 2, 6, 4 };
		System.out.println(single(a));
	}
}
