package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 90. Subsets II
//
// Input: an array that may contain repeated numbers. Returns every subset of it, each distinct
// subset exactly once, in any order. On LeetCode: 1 <= nums.length <= 10, -10 <= nums[i] <= 10.
// The array is sorted in place.
//
// The one idea: the bitmask loop of 78 (SubsetsBitmask), with the repeats never built. One mask is
// one row: slot i on means nums[i] is in it. Sort, so equal copies sit side by side, then take
// copies of a value from the left: the second 2 only if the first 2 is taken too. A mask that takes
// a copy while leaving out the equal copy just before it is a repeat of another mask, and the whole
// row is skipped -- so no Set is needed. It is the backtracking duplicate rule written in bits.
//
// The loop still visits all 2^n masks however many repeats there are: ten 2s give 1024 masks for
// only 11 distinct subsets.
//
// Time: O(n * 2^n) -- 2^n masks, each read across its n slots.
// Space: O(n) besides the output.
//
// Siblings: SubsetsIIBitmaskSet builds every row and lets a Set drop the repeats instead;
// SubsetsIIBacktracking, SubsetsIITakeOrSkip and SubsetsIICopyAndAdd solve 90 other ways.

public final class SubsetsIIBitmaskSkip {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		int n = nums.length;
		Arrays.sort(nums); // SORT: equal copies side by side
		List<List<Integer>> subsets = new ArrayList<>();
		for (int mask = 0; mask < (1 << n); mask++) { // WALK: 2^n masks
			boolean duplicate = false; // fresh for every row, like the subset
			List<Integer> subset = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (i > 0 //
						&& nums[i - 1] == nums[i] // an equal copy just before
						&& (mask & (1 << i)) != 0 // this copy taken
						&& (mask & (1 << (i - 1))) == 0) { // the one before it left out
					duplicate = true; // SKIP ROW: a repeat, copies are taken from the left
					break;
				}
				if ((mask & (1 << i)) != 0) { // TEST: slot i on
					subset.add(nums[i]); // TAKE nums[i]
				}
			}
			if (!duplicate) { // RECORD: the decision is about the whole row, not one number
				subsets.add(subset);
			}
		}
		return subsets;
	}

	public static void main(String[] args) {
		SubsetsIIBitmaskSkip ps = new SubsetsIIBitmaskSkip();
		// [[], [1], [1, 1], [2], [1, 2], [1, 1, 2]]
		System.out.println(ps.subsetsWithDup(new int[] { 1, 2, 1 }));
		// [[], [1], [3], [1, 3], [3, 3], [1, 3, 3], [5], [1, 5], [3, 5], [1, 3, 5], [3, 3, 5], [1, 3, 3, 5]]
		System.out.println(ps.subsetsWithDup(new int[] { 1, 3, 3, 5 }));
	}
}
