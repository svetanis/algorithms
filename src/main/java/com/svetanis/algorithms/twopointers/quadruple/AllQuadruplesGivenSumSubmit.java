package com.svetanis.algorithms.twopointers.quadruple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 18. 4Sum
//
// Input: an unsorted array of integers, repeats allowed, and a target. Values and target
// can be up to 10^9 in size.
// Return: every distinct quadruplet of VALUES [a, b, c, d] taken from four different
// positions with a + b + c + d == target, each listed once.
//
// The one idea: 3Sum (LC 15) with one more fixed number. Sort, FIX a[i] and then a[j], and
// converge on the numbers to the right of j for the last two. Repeats are skipped in four
// places -- a repeated a[i], a repeated a[j], and after a hit the repeats at left and at
// right -- so each quadruplet comes out once with no set. The sum is taken in long:
// four values of 10^9 make 4 * 10^9, past 2^31, and a wrapped sum is negative.
//
// Siblings -- the same problem:
//   twopointers.quadruple.AllQuadruplesGivenSum        -- the pair search as a helper method
//   twopointers.quadruple.AllQuadruplesGivenSumHashing -- pair sums in a map, a set removes repeats
//
// Time: O(n^3) -- n^2 fixed pairs, an O(n) converging pass for each; the sort is O(n log n).
// Space: O(1) besides the output; the array is sorted in place.

public final class AllQuadruplesGivenSumSubmit {

	public static List<List<Integer>> quadruplets(int[] a, int target) {
		int n = a.length;
		List<List<Integer>> quadruplets = new ArrayList<>();
		if (n < 4) {
			return quadruplets;                                  // RETURN: fewer than four numbers
		}
		Arrays.sort(a);                                          // SORT: the converging pass needs it
		for (int i = 0; i < n - 3; i++) {                        // FIX the first number a[i]
			if (i > 0 && a[i] == a[i - 1]) {
				continue;                                        // SKIP 1: same first number as last time
			}
			for (int j = i + 1; j < n - 2; j++) {                // FIX the second number a[j]
				if (j > i + 1 && a[j] == a[j - 1]) {
					continue;                                    // SKIP 2: same second number, for this i -- j > i + 1, not j > 0
				}
				int left = j + 1;                                // START: the two ends right of j
				int right = n - 1;
				while (left < right) {                           // STOP: a pair needs two positions
					long sum = (long) a[i] + a[j] + a[left] + a[right]; // COMPARE in long: the cast matters
					if (sum < target) {
						left++;                                  // DROP LEFT: too small even with the largest
					} else if (sum > target) {
						right--;                                 // DROP RIGHT: too big even with the smallest
					} else {
						quadruplets.add(Arrays.asList(a[i], a[j], a[left], a[right])); // FOUND
						left++;                                  // MOVE both, then
						right--;
						while (left < right && a[left] == a[left - 1]) {
							left++;                              // SKIP 3: repeats on the left
						}
						while (left < right && a[right] == a[right + 1]) {
							right--;                             // SKIP 4: repeats on the right
						}
					}
				}
			}
		}
		return quadruplets;
	}

	public static void main(String[] args) {
		int[] a = { 1, 0, -1, 0, -2, 2 };
		System.out.println(quadruplets(a, 0)); // [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]

		int[] a1 = { 2, 2, 2, 2, 2 };
		System.out.println(quadruplets(a1, 8)); // [[2, 2, 2, 2]]

		int[] a2 = { 1_000_000_000, 1_000_000_000, 1_000_000_000, 1_000_000_000 };
		System.out.println(quadruplets(a2, -294_967_296)); // [] -- in int the four would wrap to this target
	}
}
