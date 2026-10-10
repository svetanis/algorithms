package com.svetanis.algorithms.twopointers.quadruple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// All unique quadruples with a given sum -- the same problem as 18. 4Sum
//
// Input: an unsorted array of integers, repeats allowed, and a target k.
// Return: every distinct quadruple of VALUES from four different positions summing to k,
// each listed once, its values in ascending order.
//
// The one idea: 3Sum with one more fixed number. Sort, FIX a[i] and then a[j], and converge
// on the numbers to the right of j for a pair summing to k - a[i] - a[j]. Repeats are
// skipped at every level -- a repeated a[i], a repeated a[j] (compared only from j > i + 1
// on), and after a hit the repeats at left and right -- so each quadruple comes out once,
// with no set. The sums are taken in long: four values near 10^9 pass 2^31.
//
// Siblings -- the same problem:
//   twopointers.quadruple.AllQuadruplesGivenSumSubmit  -- LC 18, the pair search written inline
//   twopointers.quadruple.AllQuadruplesGivenSumHashing -- pair sums in a map, a set removes repeats
//
// Time: O(n^3) -- n^2 fixed pairs, an O(n) converging pass for each.
// Space: O(1) besides the output; the array is sorted in place.

public final class AllQuadruplesGivenSum {

	public static List<List<Integer>> quadruples(int[] a, int k) {
		Arrays.sort(a);                                  // SORT: the converging pass needs it
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < a.length - 3; i++) {         // FIX the first number a[i]
			if (i > 0 && a[i] == a[i - 1]) {
				continue;                                // SKIP 1: same first number as last time
			}
			for (int j = i + 1; j < a.length - 2; j++) { // FIX the second number a[j]
				if (j > i + 1 && a[j] == a[j - 1]) {
					continue;                            // SKIP 2: same second number, for this i
				}
				long target = (long) k - a[i] - a[j];    // the last two must sum to this, in long
				list.addAll(pairs(a, target, i, j));
			}
		}
		return list;
	}

	private static List<List<Integer>> pairs(int[] a, long k, int i, int j) {
		int left = j + 1;                                // START: the two ends right of j
		int right = a.length - 1;
		List<List<Integer>> list = new ArrayList<>();
		while (left < right) {                           // STOP: a pair needs two positions
			long sum = (long) a[left] + a[right];        // COMPARE
			if (sum < k) {
				left++;                                  // DROP LEFT: too small even with the largest
			} else if (sum > k) {
				right--;                                 // DROP RIGHT: too big even with the smallest
			} else {
				list.add(Arrays.asList(a[i], a[j], a[left], a[right])); // FOUND
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
		return list;
	}

	public static void main(String[] args) {
		int[] a = { 4, 1, 2, -1, 1, -3 };
		System.out.println(quadruples(a, 1)); // [[-3, -1, 1, 4], [-3, 1, 1, 2]]

		int[] a1 = { 2, 0, -1, 1, -2, 2 };
		System.out.println(quadruples(a1, 2)); // [[-2, 0, 2, 2], [-1, 0, 1, 2]]

		int[] a2 = { 1_000_000_000, 1_000_000_000, 1_000_000_000, 1_000_000_000 };
		System.out.println(quadruples(a2, -294_967_296)); // [] -- in int the four would wrap to this target
	}
}
