package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// All unique triplets with a given sum -- 3Sum for any target, repeats removed by a Set
//
// Input: an unsorted array of integers, repeats allowed, and a target.
// Return: every distinct triplet of VALUES [x, y, z], x <= y <= z, taken from three
// different positions, with x + y + z == target. Each triplet appears once.
//
// The one idea: sort, then FIX the first number a[i] and converge on the numbers to its
// right -- Two Sum on a sorted array (LC 167). Nothing skips repeats, so the same triplet
// can be found many times; the Set keeps one of each. Sorting first is what makes that
// work: every copy of a triplet comes out in the same ascending order, so the copies are
// equal lists.
//
// Siblings -- the same fix-one-and-converge loop:
//   twopointers.ThreeSum15                              -- LC 15: target 0, three skips, no Set
//   twopointers.triplet.AllUniqueTripletsGivenSumSorted -- skips repeats itself; its Set is
//                                                          a second guard
//
// Time: O(n^2) -- n fixed numbers, an O(n) converging pass for each; the sort is O(n log n).
// Space: O(t) for the Set of t triplets; the array is sorted in place.

public final class AllUniqueTripletsGivenSumSortedSimple {

	public static Set<List<Integer>> triplets(int[] a, int target) {
		int n = a.length;
		Arrays.sort(a);                                      // SORT: the converging pass needs it
		Set<List<Integer>> set = new LinkedHashSet<>();      // keeps one copy of each triplet
		for (int i = 0; i < n - 2; ++i) {                    // FIX a[i]
			int left = i + 1;                                // START: the two ends right of i
			int right = n - 1;
			while (left < right) {                           // STOP: a pair needs two positions
				int sum = a[i] + a[left] + a[right];         // COMPARE
				if (sum == target) {
					set.add(Arrays.asList(a[i], a[left], a[right])); // FOUND
					left++;                                  // MOVE both
					right--;
				} else if (sum < target) {
					left++;                                  // DROP LEFT: too small even with the largest
				} else {
					right--;                                 // DROP RIGHT: too big even with the smallest
				}
			}
		}
		return set;
	}

	public static void main(String[] args) {
		int[] a = { -2, 2, 0, -1, 1 };
		System.out.println(triplets(a, 0)); // [[-2, 0, 2], [-1, 0, 1]]

		int[] a1 = { 10, 3, -4, 1, -6, 9 };
		System.out.println(triplets(a1, 0)); // [[-6, -4, 10], [-4, 1, 3]]
	}
}