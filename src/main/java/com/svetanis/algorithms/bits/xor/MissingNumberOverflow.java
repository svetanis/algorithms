package com.svetanis.algorithms.bits.xor;

// CSES: Missing Number, by a sum -- and why the overflow does no harm in Java
//
// Input: an array of the n - 1 distinct numbers from 1 to n that are present -- one number of
// 1 .. n is missing. Returns the missing number. On CSES: 2 <= n <= 2 * 10^5.
//
// The one idea: 1 + 2 + ... + n minus the sum of the array is the missing number, since every
// present number is subtracted back out. The sum overflows an int once n passes 65,535 -- at
// n = 65,536 it is 2,147,516,416, above 2^31 - 1 = 2,147,483,647 -- yet in Java the answer is still
// right. int arithmetic wraps: every result is the true result plus or minus a multiple of 2^32.
// The subtractions wrap back by the same amount, and the true answer, between 1 and n, fits an int,
// so it is exactly what comes out. The same code fails only where overflow is an error
// (Math.addExact) or undefined (signed ints in C).
//
// Time: O(n) -- one pass over 1 .. n and one over the array.
// Space: O(1) -- one int.
//
// Siblings: MissingNumber gets the same answer with ^, which never leaves the range of the numbers.

public final class MissingNumberOverflow {

	public static int single(int[] a) {
		// Time Complexity: O(n)
		// Space Complexity: O(1)

		int n = a.length + 1; // SIZE: one number is missing from 1 .. n
		// sum of all numbers from 1 to n
		int sum = 0;
		for (int i = 1; i <= n; i++) { // ADD all of 1 .. n
			sum += i; // wraps past 2^31 - 1 for n > 65,535
		}
		// subtract all numbers in a[] from sum
		for (int i = 0; i < a.length; i++) { // SUBTRACT the present ones
			sum -= a[i]; // and wraps back by the same amount
		}
		return sum; // RETURN: only the missing one is left
	}

	public static void main(String[] args) {
		int[] a = { 1, 5, 2, 6, 4 };
		System.out.println(single(a)); // 3

		int[] a2 = { 2, 3, 1, 5 };
		System.out.println(single(a2)); // 4

		int n = 100000; // 1 + 2 + ... + n is 5,000,050,000, past 2^31 - 1
		int[] big = new int[n - 1]; // 1 .. n without 77,777
		for (int i = 0; i < n - 1; i++) {
			big[i] = i + 1 < 77777 ? i + 1 : i + 2; // SKIP 77,777
		}
		System.out.println(single(big)); // 77777
	}
}
