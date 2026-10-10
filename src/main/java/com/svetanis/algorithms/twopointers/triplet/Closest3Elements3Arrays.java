package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// Three closest elements from three arrays
//
// Input: three non-empty arrays a, b, c, in any order; they are sorted here, in place.
// Return: {a[i], b[j], c[k]} that makes max(|a[i] - b[j]|, |b[j] - c[k]|, |c[k] - a[i]|)
// as small as possible -- the first such triple met. That maximum is simply the largest of
// the three minus the smallest, their SPREAD.
//
// The one idea: one pointer per array, all starting at the smallest numbers. Only stepping
// past the MINIMUM of the three can help: every triple still ahead that keeps the minimum
// has a maximum at least the current one, so none beats the spread just computed, and the
// minimum can be dropped for good. Stop when any array runs out, or the spread hits 0.
//
// Sibling: twopointers.triplet.ThreeClosestElementsFrom3SortedArrays -- the same loop on
//   arrays that are already sorted, so no sort.
//
// Time: O(n log n + m log m + l log l) for the sorts, then O(n + m + l) -- every step moves
// one pointer forward.
// Space: O(1) besides the sorts, which work in place.

public final class Closest3Elements3Arrays {

	public static int[] triplet(int[] a, int[] b, int[] c) {
		Arrays.sort(a);                                  // SORT all three
		Arrays.sort(b);
		Arrays.sort(c);

		int target = Integer.MAX_VALUE;                  // the smallest spread so far

		int n = a.length;
		int m = b.length;
		int l = c.length;

		int first = 0;                                   // the positions that gave it
		int second = 0;
		int third = 0;

		int i = 0;                                       // START: the smallest of each array
		int j = 0;
		int k = 0;

		while (i < n && j < m && k < l) {                // STOP: one array ran out
			int min = Math.min(a[i], Math.min(b[j], c[k]));
			int max = Math.max(a[i], Math.max(b[j], c[k]));
			int dif = max - min;                         // COMPARE: the spread of these three
			if (dif < target) {
				target = dif;                            // RECORD a smaller spread
				first = i;
				second = j;
				third = k;
			}

			if (target == 0) {
				break;                                   // STOP: a spread of 0 cannot be beaten
			}

			if (a[i] == min) {
				i++;                                     // DROP the minimum: it has met its best partners
			} else if (b[j] == min) {
				j++;
			} else {
				k++;
			}
		}
		return new int[] { a[first], b[second], c[third] };
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 10 };
		int[] b = { 2, 15, 20 };
		int[] c = { 10, 12 };
		System.out.println(Arrays.toString(triplet(a, b, c))); // [10, 15, 10]

		int[] a1 = { 5, 2, 8 };
		int[] a2 = { 10, 7, 12 };
		int[] a3 = { 9, 14, 6 };
		System.out.println(Arrays.toString(triplet(a1, a2, a3))); // [5, 7, 6]

		int[] b1 = { 15, 12, 18, 9 };
		int[] b2 = { 10, 17, 13, 8 };
		int[] b3 = { 14, 16, 11, 5 };
		System.out.println(Arrays.toString(triplet(b1, b2, b3))); // [9, 10, 11]
	}
}
