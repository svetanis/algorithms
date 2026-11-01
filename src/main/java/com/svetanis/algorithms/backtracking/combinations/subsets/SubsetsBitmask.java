package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
//
// Input: an array of distinct integers. Returns every subset of it (the power set), the empty
// subset and the whole array included, in any order. On LeetCode: 1 <= nums.length <= 10,
// -10 <= nums[i] <= 10, all distinct. Assumed here: n <= 30, since 1 << 31 is negative and the
// loop would return nothing.
//
// The one idea: every subset is one number from 0 to 2^n - 1: slot i of the number is on exactly
// when a[i] is in the subset. So count mask from 0 to 2^n - 1 (the outer loop, one subset per turn)
// and read each mask's slots (the inner loop, one position per turn). The slot is a POSITION in a,
// never a value.
//
// Time: O(n * 2^n) -- 2^n masks, each read across its n slots.
// Space: O(n) besides the output.
//
// Siblings: SubsetsBacktracking -- the same problem by backtracking; SubsetsCopyAndAdd -- no
// recursion, the list doubles per number. SubsetsIIBitmaskSet and SubsetsIIBitmaskSkip are this
// loop with repeats.

public final class SubsetsBitmask {
	public List<List<Integer>> subsets(int[] a) {
		int n = a.length;
		List<List<Integer>> subsets = new ArrayList<>();
		for (int mask = 0; mask < (1 << n); mask++) { // WALK: 2^n masks, one subset each
			List<Integer> subset = new ArrayList<>(); // a fresh, empty row each turn
			for (int i = 0; i < n; i++) { // WALK: i is a position, never a value of a
				if ((mask & (1 << i)) != 0) { // TEST: bit i of mask; &= would change mask
					subset.add(a[i]); // TAKE a[i]
				}
			}
			subsets.add(subset); // RECORD
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
