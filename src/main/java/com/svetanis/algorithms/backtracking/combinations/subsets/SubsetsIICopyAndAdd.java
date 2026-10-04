package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 90. Subsets II
//
// Given an array that may contain repeated numbers, return every subset of it, each distinct
// subset exactly once, in any order.
//
// The copy-and-add loop of SubsetsCopyAndAdd, after sorting so equal numbers sit side by side.
// A new number is added to a copy of every subset so far. A repeat of the number just before it
// is added only to the subsets the previous turn made: an older subset plus this copy is the same
// as that older subset plus the first copy, which the previous turn already made.

public final class SubsetsIICopyAndAdd {
	// Time Complexity: O(n * 2^n), at most 2^n subsets, each made by copying up to n elements
	// Space Complexity: O(n * 2^n), the subsets themselves; nothing else is kept

	public static List<List<Integer>> subsets(int[] a) {
		Arrays.sort(a); // equal numbers side by side
		List<List<Integer>> subsets = new ArrayList<>();
		subsets.add(new ArrayList<>()); // the empty subset, the one every other subset grows from
		int start = 0;
		int end = 0; // the last subset that existed before the previous turn
		for (int i = 0; i < a.length; i++) {
			start = 0; // a new number extends every subset so far
			if (i > 0 && a[i] == a[i - 1]) {
				start = end + 1; // a repeat extends only what the previous turn made
			}
			end = subsets.size() - 1; // read before this turn adds anything
			for (int j = start; j <= end; j++) {
				List<Integer> subset = new ArrayList<>(subsets.get(j)); // a copy: the old one stays
				subset.add(a[i]);
				subsets.add(subset);
			}
		}
		return subsets;
	}

	public static void main(String[] args) {
		// [[], [1], [3], [1, 3], [3, 3], [1, 3, 3], [5], [1, 5], [3, 5], [1, 3, 5], [3, 3, 5], [1, 3, 3, 5]]
		System.out.println(subsets(new int[] { 1, 3, 3, 5 }));
	}
}
