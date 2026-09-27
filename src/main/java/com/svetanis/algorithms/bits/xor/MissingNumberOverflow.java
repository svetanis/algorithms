package com.svetanis.algorithms.bits.xor;

// given an array of n - 1 integers in
// the range from 1 to n, find the one
// number that is missing from the array

// the sum 1 + 2 + ... + n overflows an int
// once n passes 65,535 -- yet in Java the answer
// is still right: int arithmetic wraps, and the
// subtractions wrap it back by the same amount.
// It fails only where overflow is an error
// (Math.addExact) or undefined (C). The XOR
// version, MissingNumber, never overflows at all.

public final class MissingNumberOverflow {

	public static int single(int[] a) {
		// Time Complexity: O(n)
		// Space Complexity: O(1)

		int n = a.length + 1;
		// sum of all numbers from 1 to n
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i; // wraps past 2^31 - 1 for n > 65,535
		}
		// subtract all numbers in a[] from sum
		for (int i = 0; i < a.length; i++) {
			sum -= a[i]; // and wraps back by the same amount
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a = { 1, 5, 2, 6, 4 };
		System.out.println(single(a));
	}
}
