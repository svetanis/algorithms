package com.svetanis.algorithms.twopointers.triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// All unique triplets with a given sum -- 3Sum for any target
//
// Input: an unsorted array of integers, repeats allowed, and a target.
// Return: every distinct triplet of VALUES [x, y, z], x <= y <= z, taken from three
// different positions, with x + y + z == target. Each triplet appears once.
//
// The one idea: sort, then FIX the first number a[i] and converge on the numbers to its
// right -- Two Sum on a sorted array (LC 167). A repeated a[i] is skipped, and after a hit
// both pointers step past their repeats, so no triplet is found twice. The Sets are a
// second guard: with the skips in place they never have anything to remove.
//
// Siblings -- the same fix-one-and-converge loop:
//   twopointers.ThreeSum15                                    -- LC 15: target 0, no Set,
//                                                                stops once a[i] > 0
//   twopointers.triplet.AllUniqueTripletsGivenSumSortedSimple -- no skips at all, the Set
//                                                                removes every repeat
//
// Time: O(n^2) -- n fixed numbers, an O(n) converging pass for each; the sort is O(n log n).
// Space: O(t) for the Set of t triplets; the array is sorted in place.

public final class AllUniqueTripletsGivenSumSorted {

	public static List<List<Integer>> triplets(int[] a, int target) {
		Arrays.sort(a);                                          // SORT: the converging pass needs it
		Set<List<Integer>> set = new LinkedHashSet<>();
		for (int i = 0; i < a.length - 2; i++) {                 // FIX a[i]
			if (i > 0 && a[i - 1] == a[i]) {
				continue;                                        // SKIP 1: same first number as last time
			}
			set.addAll(triplets(a, target, i));
		}
		return new ArrayList<>(set);
	}

	private static Set<List<Integer>> triplets(int[] a, int target, int first) {
		int left = first + 1;                                    // START: the two ends right of first
		int right = a.length - 1;
		Set<List<Integer>> set = new LinkedHashSet<>();
		while (left < right) {                                   // STOP: a pair needs two positions
			int sum = a[first] + a[left] + a[right];             // COMPARE
			if (sum == target) {
				set.add(Arrays.asList(a[first], a[left], a[right])); // FOUND
				left++;                                          // MOVE both, then
				right--;
				while (left < right && a[left] == a[left - 1]) {
					left++;                                      // SKIP 2: repeats on the left
				}
				while (left < right && a[right] == a[right + 1]) {
					right--;                                     // SKIP 3: repeats on the right
				}
			} else if (sum < target) {
				left++;                                          // DROP LEFT: too small even with the largest
			} else {
				right--;                                         // DROP RIGHT: too big even with the smallest
			}
		}
		return set;
	}

	public static void main(String[] args) {
		int[] a1 = { -2, 2, 0, -1, 1 };
		System.out.println(triplets(a1, 0)); // [[-2, 0, 2], [-1, 0, 1]]

		int[] a2 = { 10, 3, -4, 1, -6, 9 };
		System.out.println(triplets(a2, 0)); // [[-6, -4, 10], [-4, 1, 3]]

		int[] a3 = { -31013930, -31013930, 9784175, 21229755 };
		System.out.println(triplets(a3, 0)); // [[-31013930, 9784175, 21229755]]

		int[] a4 = { -3, 0, 1, 2, -1, 1, -2 };
		System.out.println(triplets(a4, 0)); // [[-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]]

		int[] a5 = { -5, 2, -1, -2, 3 };
		System.out.println(triplets(a5, 0)); // [[-5, 2, 3], [-2, -1, 3]]
	}
}
