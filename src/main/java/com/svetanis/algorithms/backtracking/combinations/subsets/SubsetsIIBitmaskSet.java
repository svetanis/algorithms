package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 90. Subsets II
//
// Input: an array that may contain repeated numbers. Returns every subset of it, each distinct
// subset exactly once, in any order. On LeetCode: 1 <= nums.length <= 10, -10 <= nums[i] <= 10.
// The array is sorted in place.
//
// The one idea: the bitmask loop of 78 (SubsetsBitmask): every number from 0 to 2^n - 1 is one
// choice of positions, slot i on meaning nums[i] is taken. With repeated numbers two different
// choices of positions can hold the same values -- for {1, 2, 2}, slots {1} and {2} are both [2] --
// so the same subset comes out more than once. Sort first, so every subset comes out smallest first
// and one group of numbers always has one spelling ([2, 1] and [1, 2] are different lists to Java),
// then collect in a Set, which keeps each spelling once.
//
// The loop visits all 2^n masks however many repeats there are: ten 2s give 1024 masks for only 11
// distinct subsets.
//
// Time: O(n * 2^n) -- 2^n masks, each read across its n slots.
// Space: O(n * 2^n) for the Set of subsets, besides the output.
//
// Siblings: SubsetsIIBitmaskSkip never builds the repeats instead; SubsetsIIBacktracking,
// SubsetsIITakeOrSkip and SubsetsIICopyAndAdd solve 90 other ways.

public final class SubsetsIIBitmaskSet {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		int n = nums.length;
		Arrays.sort(nums); // SORT: the same numbers always come out in one order
		Set<List<Integer>> subsets = new HashSet<>();
		for (int mask = 0; mask < (1 << n); mask++) { // WALK: 2^n masks, one choice of positions each
			List<Integer> subset = new ArrayList<>();
			for (int i = 0; i < n; i++) { // i is a position in nums
				if ((mask & (1 << i)) != 0) { // TEST: bit i on: nums[i] is in
					subset.add(nums[i]); // TAKE nums[i]
				}
			}
			subsets.add(subset); // RECORD: a repeat is simply not added again
		}
		return new ArrayList<>(subsets);
	}

	public static void main(String[] args) {
		SubsetsIIBitmaskSet ps = new SubsetsIIBitmaskSet();
		// [[1], [], [1, 1], [2], [1, 1, 2], [1, 2]], in hash order
		System.out.println(ps.subsetsWithDup(new int[] { 1, 2, 1 }));
		// 12 subsets, in hash order: [[1], [1, 3, 3], [], [3, 3], [3], [1, 3, 5], [1, 3], [3, 5], [5],
		// [3, 3, 5], [1, 5], [1, 3, 3, 5]]
		System.out.println(ps.subsetsWithDup(new int[] { 1, 3, 3, 5 }));
	}
}
