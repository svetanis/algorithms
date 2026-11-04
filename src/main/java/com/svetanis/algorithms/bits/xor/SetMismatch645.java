package com.svetanis.algorithms.bits.xor;

import java.util.Arrays;

// 645. Set Mismatch
//
// Input: an int array of length n that should hold every number from 1 to n once, but one number
// was replaced by a copy of another: one number appears twice (dup) and one is missing. Returns
// { dup, missing }. On LeetCode: 2 <= nums.length <= 10^4, 1 <= nums[i] <= 10^4.
//
// The one idea: ^ the array together with 1 .. n. Every other number appears twice and cancels, dup
// appears three times and missing once, so dup ^ missing is left. The two are different numbers, so
// that ^ has a 1 somewhere -- a slot where dup and missing differ; take its lowest 1. Split both
// lists, the array and 1 .. n, on that slot and ^ only the half with the slot on: every other number
// lands there with all its copies and cancels, and exactly one of dup and missing lands there, so
// that half's ^ is one of the two, x. The other is (dup ^ missing) ^ x. A last pass looks for x in
// the array: if it is there, x is dup.
//
// Time: O(n) -- three passes: the ^ of everything, the split, the search.
// Space: O(1) -- a few ints, plus the two-number answer.
//
// Siblings: SingleNumberIII (260) uses the same split on one slot to separate two numbers. xor2 is
// an unused second way to compute xor's value, with the two lists in separate loops.

public final class SetMismatch645 {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int[] setMismatch(int[] a) {
		int xor = xor(a); // CANCEL: dup ^ missing is left
		int x = separate(a, xor); // SPLIT: one of the two
		int y = xor ^ x; // OTHER: xor is x ^ y, so ^ x leaves y
		return segregate(a, x, y); // ORDER: which one is dup
	}

	private static int xor(int[] a) {
		int xor = 0;
		// xor all numbers in a[] and from 1 to n
		for (int i = 1; i <= a.length; i++) {
			xor = xor ^ i ^ a[i - 1]; // BOTH lists in one loop
		}
		return xor;
	}

	private static int xor2(int[] a) {
		int n = a.length;
		int xor = n; // START: n, which the first loop does not reach
		// xor all numbers from 1 to n
		for (int i = 1; i < n; i++) { // ALL: 1 .. n - 1
			xor = xor ^ i;
		}
		// xor all numbers in a[]
		for (int i = 0; i < n; i++) { // PRESENT
			xor = xor ^ a[i];
		}
		return xor;
	}

	private static int separate(int[] a, int xor) {
		// a slot where dup and missing differ
		int rsb = xor & -xor; // SLOT: the lowest 1; the same as xor & ~(xor - 1)
		int x = 0;
		for (int i = 1; i <= a.length; ++i) { // BOTH lists, one half of each
			if ((i & rsb) > 0) { // TAKE: only the half with that slot on
				x = x ^ i;
			}
			if ((a[i - 1] & rsb) > 0) {
				x = x ^ a[i - 1];
			}
		}
		return x;
	}

	// find which one is a duplicate
	private static int[] segregate(int[] a, int x, int y) {
		for (int i = 0; i < a.length; i++) { // FIND x in the array
			if (a[i] == x) {
				return new int[] { x, y }; // RETURN: x is there, so x is dup
			}
		}
		return new int[] { y, x }; // RETURN: x is missing, so y is dup
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 2, 4 };
		System.out.println(Arrays.toString(setMismatch(a1))); // [2, 3]

		int[] a2 = { 1, 1 };
		System.out.println(Arrays.toString(setMismatch(a2))); // [1, 2]
	}
}
