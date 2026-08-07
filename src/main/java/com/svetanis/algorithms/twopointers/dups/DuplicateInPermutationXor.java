package com.svetanis.algorithms.twopointers.dups;

import static com.svetanis.java.base.utils.Arrays.max;


// given an array of n + 1 integers holding each of 1..n
// exactly once, plus a second copy of one of them
// find the value that appears twice

public final class DuplicateInPermutationXor {
	// Time Complexity: O(n)

	// the contract above is load-bearing: 1..n must each be present, so
	// that every value cancels itself except the one appearing twice.
	// on { 2, 2, 2, 2, 2 } this returns 1 -- see DuplicateInPlaceMarking.
	public static int duplicate(int[] a) {
		// xor all numbers in a[]
		int xor = a[0];
		for (int i = 1; i < a.length; i++) {
			xor = xor ^ a[i];
		}
		// xor all numbers from 1 to n
		int n = max(a);
		// int n = java.util.Arrays.stream(a).max().getAsInt();
		for (int i = 1; i <= n; i++) {
			xor = xor ^ i;
		}
		// return duplicate number
		return xor;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

	}
}
