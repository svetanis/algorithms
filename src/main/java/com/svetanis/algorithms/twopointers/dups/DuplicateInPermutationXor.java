package com.svetanis.algorithms.twopointers.dups;

// Find the duplicate in a permutation with one extra copy
//
// Input: n + 1 integers holding each of 1..n exactly once, plus a second copy of one
// of them.
// Return: the value that appears twice.
//
// The one idea: x ^ x == 0, so XOR cancels pairs. Fold every array value and every
// value 1..n into one XOR: each value that is not repeated appears twice in total and
// cancels, the repeated one appears three times and is what is left.
//
// Siblings:
//   DuplicateInPermutationSum -- the same contract; a subtraction instead of XOR, n taken from the length
//   DuplicateInPlaceFloyd     -- LC 287, where the repeat may crowd other values out: no XOR works there
//   DuplicateInPlaceBinary    -- LC 287 by counting the numbers <= mid, O(n log n)
//
// Time: O(n) -- one pass over the array, one over 1..n.
// Space: O(1).

public final class DuplicateInPermutationXor {

	// the contract above is load-bearing: 1..n must each be present, so
	// that every value cancels itself except the one appearing twice.
	// on { 2, 2, 2, 2, 2 } this returns 1 -- see DuplicateInPlaceMarking.
	public static int duplicate(int[] a) {
		int xor = a[0];
		int n = a[0];
		for (int i = 1; i < a.length; i++) {
			xor = xor ^ a[i];        // COUNT: every array value folded in
			n = Math.max(n, a[i]);   // RECORD the largest value: it is n under the contract
		}
		for (int i = 1; i <= n; i++) {
			xor = xor ^ i;           // COUNT: every value 1..n folded in once more
		}
		return xor;                  // RETURN the one value left uncancelled
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
