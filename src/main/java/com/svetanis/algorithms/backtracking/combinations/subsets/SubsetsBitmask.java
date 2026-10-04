package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
//
// Given an array of distinct integers, return every subset of it (the power set), the empty
// subset and the whole array included, in any order.
//
// Every subset is one number from 0 to 2^n - 1: slot i of the number is on exactly when a[i]
// is in the subset. So count mask from 0 to 2^n - 1 (the outer loop, one subset per turn) and
// read each mask's slots (the inner loop, one position per turn). The slot is a POSITION in a,
// never a value. The backtracking version of the same problem is SubsetsBacktracking.

public final class SubsetsBitmask {
	// Time Complexity: O(n * 2^n), 2^n masks, each read across its n slots
	// Space Complexity: O(n) besides the output
	// n <= 30 for an int mask: 1 << 31 is negative, and the loop would return nothing

	public List<List<Integer>> subsets(int[] a) {
		int n = a.length;
		List<List<Integer>> subsets = new ArrayList<>();
		for (int mask = 0; mask < (1 << n); mask++) { // mask COUNTS 0, 1, 2, ...: one subset each
			List<Integer> subset = new ArrayList<>(); // a fresh, empty row each turn
			for (int i = 0; i < n; i++) { // i is a position, never a value of a
				if ((mask & (1 << i)) != 0) { // & tests the slot, &= would change mask
					subset.add(a[i]);
				}
			}
			subsets.add(subset);
		}
		return subsets;
	}

	public static void main(String[] args) {
		SubsetsBitmask ps = new SubsetsBitmask();
		// [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
		System.out.println(ps.subsets(new int[] { 1, 2, 3 }));
		// [[], [0]]
		System.out.println(ps.subsets(new int[] { 0 }));
	}
}
