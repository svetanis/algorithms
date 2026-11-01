package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
//
// Input: an array of distinct integers. Returns every subset of it (the power set), the empty
// subset and the whole array included, in any order. On LeetCode: 1 <= nums.length <= 10,
// -10 <= nums[i] <= 10, all distinct.
//
// The one idea: no recursion. subsets holds every subset of the numbers seen so far, one list
// each. Start from the one empty subset, and for each number copy every subset built so far and
// add the number to the copy. Each number doubles the list -- the subsets without it are already
// there, the copies are the ones with it -- so after n numbers there are 2^n.
//
// Time: O(n * 2^n) -- 2^n subsets, each made by copying one of up to n elements.
// Space: O(n * 2^n) -- the subsets themselves; nothing else is kept.
//
// Siblings: SubsetsBacktracking and SubsetsBitmask solve 78 by recursion and by bitmask.
// SubsetsIICopyAndAdd is the same loop for an array with repeated numbers.

public final class SubsetsCopyAndAdd {
	public static List<List<Integer>> subsets(int[] a) {
		List<List<Integer>> subsets = new ArrayList<>();
		subsets.add(new ArrayList<>()); // BASE: the empty subset
		for (int num : a) { // WALK: each number doubles the list
			int size = subsets.size(); // SIZE: read once; this turn's copies are not copied
			for (int i = 0; i < size; i++) {
				List<Integer> subset = new ArrayList<>(subsets.get(i)); // COPY: the old one stays
				subset.add(num); // TAKE num
				subsets.add(subset); // RECORD
			}
		}
		return subsets;
	}

	public static void main(String[] args) {
		// [[], [1], [3], [1, 3]]
		System.out.println(subsets(new int[] { 1, 3 }));
		// [[], [1], [5], [1, 5], [3], [1, 3], [5, 3], [1, 5, 3]]
		System.out.println(subsets(new int[] { 1, 5, 3 }));
	}
}
