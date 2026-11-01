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
// The one idea: the copy-and-add loop of SubsetsCopyAndAdd, after sorting so equal numbers sit side
// by side. subsets holds every subset of the numbers seen so far; start..end is the range of
// subsets the previous turn made. A new number is added to a copy of every subset so far. A repeat
// of the number just before it is added only to the subsets the previous turn made: an older subset
// plus this copy is the same as that older subset plus the first copy, which the previous turn
// already made.
//
// Time: O(n * 2^n) -- at most 2^n subsets, each made by copying up to n elements.
// Space: O(n * 2^n) -- the subsets themselves; nothing else is kept.
//
// Siblings: SubsetsCopyAndAdd is the same loop for 78; SubsetsIIBacktracking, SubsetsIITakeOrSkip,
// SubsetsIIBitmaskSet and SubsetsIIBitmaskSkip solve 90 other ways.

public final class SubsetsIICopyAndAdd {
	public static List<List<Integer>> subsets(int[] a) {
		Arrays.sort(a); // SORT: equal numbers side by side
		List<List<Integer>> subsets = new ArrayList<>();
		subsets.add(new ArrayList<>()); // BASE: the empty subset
		int start = 0;
		int end = 0; // the last subset that existed before the previous turn
		for (int i = 0; i < a.length; i++) {
			start = 0; // EXTEND ALL: a new number extends every subset so far
			if (i > 0 && a[i] == a[i - 1]) {
				start = end + 1; // EXTEND NEW: a repeat extends only what the previous turn made
			}
			end = subsets.size() - 1; // read before this turn adds anything
			for (int j = start; j <= end; j++) {
				List<Integer> subset = new ArrayList<>(subsets.get(j)); // COPY: the old one stays
				subset.add(a[i]); // TAKE a[i]
				subsets.add(subset); // RECORD
			}
		}
		return subsets;
	}

	public static void main(String[] args) {
		// [[], [1], [3], [1, 3], [3, 3], [1, 3, 3], [5], [1, 5], [3, 5], [1, 3, 5], [3, 3, 5], [1, 3, 3, 5]]
		System.out.println(subsets(new int[] { 1, 3, 3, 5 }));
	}
}
