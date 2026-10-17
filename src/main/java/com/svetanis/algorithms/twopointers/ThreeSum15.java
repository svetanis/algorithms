package com.svetanis.algorithms.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 15. 3Sum
//
// Input: an unsorted array of integers, repeats allowed.
// Return: every distinct triplet of VALUES [x, y, z], taken from three different
// positions, with x + y + z == 0. Distinct by value: [-1, 0, 1] is listed once however
// many ways the array can form it.
//
// The one idea: sort, then FIX the first number a[i]. Finding the other two among the
// numbers to its right is Two Sum on a sorted array (LC 167), done by converging
// pointers in O(n). Repeats are skipped in three places -- a repeated a[i], and, after
// a hit, repeated values at left and at right -- so each triplet comes out once with no
// set. The loop also stops once a[i] > 0: in sorted order the other two are at least
// a[i], so three positives cannot sum to 0.
//
// Siblings -- the same fix-one-and-converge loop, any target, repeats removed by a Set:
//   twopointers.triplet.AllUniqueTripletsGivenSumSorted       -- the same three skips, plus a Set that never removes anything
//   twopointers.triplet.AllUniqueTripletsGivenSumSortedSimple -- no skips at all, the set does all of it
//
// Time: O(n^2) -- n fixed numbers, an O(n) converging pass for each; the sort is O(n log n).
// Space: O(1) besides the output; the array is sorted in place.
// The sum fits in int because the values are at most 10^5 in size.

public final class ThreeSum15 {

	public static List<List<Integer>> triplets(int[] a) {
		Arrays.sort(a);                                      // SORT: the converging pass needs it
		int n = a.length;
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n - 2 && a[i] <= 0; ++i) {      // FIX a[i]; STOP once it is positive
			if (i > 0 && a[i] == a[i - 1]) {
				continue;                                    // SKIP 1: same anchor as last time
			}
			int left = i + 1;                                // START: the two ends right of i
			int right = n - 1;
			while (left < right) {                           // STOP: a pair needs two positions
				int sum = a[i] + a[left] + a[right];         // COMPARE
				if (sum < 0) {
					left++;                                  // DROP LEFT: too small even with the largest
				} else if (sum > 0) {
					right--;                                 // DROP RIGHT: too big even with the smallest
				} else {
					list.add(Arrays.asList(a[i], a[left], a[right])); // FOUND
					while (left < right && a[left] == a[left + 1]) {
						left++;                              // SKIP 2: repeats on the left
					}
					while (left < right && a[right] == a[right - 1]) {
						right--;                             // SKIP 3: repeats on the right
					}
					left++;                                  // MOVE both off the last copies
					right--;
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[] a = { -1, 0, 1, 2, -1, -4 };
		System.out.println(triplets(a)); // [[-1, -1, 2], [-1, 0, 1]]

		int[] a1 = { 0, 1, 1 };
		System.out.println(triplets(a1)); // []

		int[] a2 = { 0, 0, 0 };
		System.out.println(triplets(a2)); // [[0, 0, 0]]
	}
}