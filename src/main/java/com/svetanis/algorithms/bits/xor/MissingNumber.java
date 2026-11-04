package com.svetanis.algorithms.bits.xor;

// CSES: Missing Number (no LeetCode number; LeetCode's version is 268)
//
// Input: n, and an array of the n - 1 distinct numbers from 1 to n that are present -- one number of
// 1 .. n is missing. Returns the missing number. On CSES: 2 <= n <= 2 * 10^5.
//
// The one idea: ^ of a number with itself is 0, ^ with 0 leaves a number as it is, and the order of
// the ^s does not matter. So ^ together every number from 1 to n and every number of the array:
// each present number appears twice and cancels, the missing one appears once and is all that is
// left. single works n out from the array's length; missing takes n as given (CSES reads it first)
// and walks both lists in one loop.
//
// Time: O(n) -- one pass over 1 .. n and one over the array.
// Space: O(1) -- one int.
//
// Siblings: MissingNumberOverflow gets the same answer from a sum, and MissingNumber268 is LeetCode's
// version, with the numbers 0 .. n.

public final class MissingNumber {
	// Time Complexity: O(n)

	public static int single(int[] a) {
		int xor = 1;
		int n = a.length + 1; // SIZE: one number is missing from 1 .. n
		// xor all numbers from 1 to n
		for (int i = 2; i <= n; i++) { // ALL: 1 is already in xor
			xor = xor ^ i;
		}
		// xor all numbers in a[]
		for (int i = 0; i < a.length; i++) { // PRESENT: each cancels its copy
			xor = xor ^ a[i];
		}
		// return missing number
		return xor; // RETURN: only the missing one is left
	}

	public static int missing(int n, int[] a) {
		int xor = 0;
		for (int i = 0; i < n - 1; i++) { // BOTH lists in one loop
			// xor all numbers in a[]
			xor = xor ^ a[i];
			// xor all numbers from 1 to n - 1
			xor = xor ^ (i + 1);
		}
		xor ^= n; // LAST: the loop stopped at n - 1
		return xor; // RETURN: only the missing one is left
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
