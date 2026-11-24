package com.svetanis.algorithms.search.median;

// Median of two sorted arrays of the same size, by binary search.

// Given two sorted arrays a1 and a2, each of length n >= 1, return the
// median of the 2n values together: the average of the n-th and the
// (n+1)-th smallest. The return type is int, so the average is truncated
// toward zero -- {1, 2} and {3, 4} give 2, not 2.5. Values whose pairwise
// sum fits in an int, as the code assumes.

// The upper middle is the (n+1)-th smallest: exactly n values sit below
// it. Binary search a1 for the index i that holds it. Pair i with
// j = n - i - 1 in a2: a1[0 .. i-1] and a2[0 .. j] are n values, so a1[i]
// is the upper middle when it is above a2[j] and not above a2[j + 1].
// The lower middle is then the larger of a2[j] and a1[i - 1].
// a1[i] above a2[j + 1] as well: i is too far right. a1[i] not above
// a2[j]: too far left. If the search in a1 runs out, the upper middle is
// in a2, so search a2 the same way. Ties can hide it from both searches;
// then the merge answers.

// Time: O(log n) -- one binary search per array at most;
//   O(n) on the merge fallback, which ties can force.
// Space: O(log n) -- the recursion depth.

// Siblings:
//   MedianTwoSortedEqualSizeMerge -- walks both arrays to the middle,
//     O(n); this file falls back to it.
//   MedianTwoSortedEqualSizeCompare -- compares the two arrays' own
//     medians and halves both ranges; returns a double, O(log n).
//   MedianTwoSortedArraysKthElement, MedianTwoSortedArraysPartition -- arrays of any
//     sizes (LC 4).

public final class MedianTwoSortedEqualSizeBinary {

	public static int median(int[] a1, int[] a2) {
		int n = a1.length;

		if (a1[n - 1] < a2[0]) { // APART: all of a1 is below a2
			return (a1[n - 1] + a2[0]) / 2;
		}

		if (a2[n - 1] < a1[0]) { // APART: all of a2 is below a1
			return (a2[n - 1] + a1[0]) / 2;
		}

		return binary(a1, a2, 0, n - 1, false);
	}

	// swapped records that the arrays have already traded places once.
	// without it the two calls swap forever and the stack runs out
	private static int binary(int[] a1, int[] a2, int left, int right, boolean swapped) {
		int n = a1.length;

		// we have reached at the end (left or right) of a1
		if (left > right) { // EXHAUSTED: no index of a1 holds the upper middle
			if (swapped) {
				// neither array holds the upper median at an index this
				// search can name -- ties do that. fall back to the merge
				// rather than guess: O(n) on this path only
				return MedianTwoSortedEqualSizeMerge.median(a1, a2); // FALLBACK
			}
			return binary(a2, a1, 0, n - 1, true); // SWAP: search a2 for it
		}

		// middle element of a1
		int i = left + (right - left) / 2;
		int j = n - i - 1; // PAIR: a1[0 .. i-1] and a2[0 .. j] are n values

		if (a1[i] > a2[j] && (j == n - 1 || a1[i] <= a2[j + 1])) { // FOUND: a1[i] is the upper middle
			// a1[i] is decided as median2, now select the median1
			// (element just before a1[i] in merged array)
			// to get the average of both
			// i == 0 FIRST: || is left to right, so a1[i - 1] is
			// evaluated before any guard written to its right
			if (i == 0 || a2[j] > a1[i - 1]) {
				return (a1[i] + a2[j]) / 2; // LOWER: a2[j]
			} else {
				return (a1[i] + a1[i - 1]) / 2; // LOWER: a1[i - 1]
			}
		} else if (a1[i] > a2[j] && j != n - 1 && a1[i] > a2[j + 1]) { // LEFT: a1[i] is too big
			return binary(a1, a2, left, i - 1, swapped);
		} else { // RIGHT: a1[i] is not above a2[j], too small
			return binary(a1, a2, i + 1, right, swapped);
		}
	}

	public static void main(String[] args) {
		int a1[] = { 1, 12, 15, 26, 38 };
		int a2[] = { 2, 13, 17, 30, 45 };
		System.out.println(median(a1, a2)); // 16
	}
}
