package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

// 532. K-diff Pairs in an Array
//
// Input: an array of integers, values may repeat, and k >= 0.
// Return: how many different pairs of VALUES (x, x + k) occur at two different
// positions. Copies of the same pair count once.
//
// The one idea: sort, then for each value x look for x + k to its RIGHT by binary
// search. Searching only to the right keeps a position from pairing with itself, so
// k == 0 needs a second copy of x. Each value is the smaller end of at most one pair,
// so only the FIRST copy of x searches; later copies are skipped, or the same pair
// would be counted once per copy.
//
// Siblings:
//   CountPairsGivenDiffHashing -- the same count in one pass over two hash sets, no sort
//   CountPairsGivenAbsDiffHashing (LC 2006) -- counts pairs of POSITIONS, values 1..100
//
// Time: O(n log n) -- the sort, then one binary search per value.
// Space: O(log n) -- the sort and the recursive search. The array is sorted in place:
// the caller's order is lost.

public final class CountPairsGivenDiffBinary {

	public static int count(int[] a, int k) {
		int count = 0;
		int n = a.length;
		sort(a);                                         // SORT: x + k can only lie to the right of x
		for (int i = 0; i < n - 1; i++) {
			if (i > 0 && a[i] == a[i - 1]) {
				continue;                                // SKIP: the first copy of this value has searched already
			}
			int target = a[i] + k;
			if (binary(a, i + 1, n - 1, target) != -1) { // SEE x + k among the numbers after i
				count++;                                 // COUNT the pair (x, x + k) once
			}
		}
		return count;
	}

	private static int binary(int[] a, int low, int high, int x) {
		if (high >= low) {
			int mid = low + (high - low) / 2;
			if (x == a[mid]) {
				return mid;                         // FOUND
			}
			if (x > a[mid]) {
				return binary(a, mid + 1, high, x); // DROP LEFT half: all of it is below x
			} else {
				return binary(a, low, mid - 1, x);  // DROP RIGHT half: all of it is above x
			}
		}
		return -1;                                  // STOP: empty range, x is absent
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 5, 3, 4, 2 };
		System.out.println(count(a1, 3)); // 2

		int[] a2 = { 8, 12, 16, 4, 0, 20 };
		System.out.println(count(a2, 4)); // 5

		int[] a3 = { 3, 1, 4, 1, 5 };
		System.out.println(count(a3, 2)); // 2

		int[] a4 = { 1, 3, 1, 5, 4 };
		System.out.println(count(a4, 0)); // 1
	}
}
