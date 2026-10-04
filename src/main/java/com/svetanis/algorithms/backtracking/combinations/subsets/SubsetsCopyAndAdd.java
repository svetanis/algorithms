package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
//
// Given an array of distinct integers, return every subset of it (the power set), the empty
// subset and the whole array included, in any order.
//
// No recursion: start from the one empty subset, and for each number copy every subset built so
// far and add the number to the copy. Each number doubles the list -- the subsets without it are
// already there, the copies are the ones with it -- so after n numbers there are 2^n.
// SubsetsIICopyAndAdd is the same loop for an array with repeated numbers.

public final class SubsetsCopyAndAdd {
	// Time Complexity: O(n * 2^n), 2^n subsets, each made by copying one of up to n elements
	// Space Complexity: O(n * 2^n), the subsets themselves; nothing else is kept

	public static List<List<Integer>> subsets(int[] a) {
		List<List<Integer>> subsets = new ArrayList<>();
		subsets.add(new ArrayList<>()); // the empty subset, the one every other subset grows from
		for (int num : a) {
			int size = subsets.size(); // read once: the copies added this turn are not copied again
			for (int i = 0; i < size; i++) {
				List<Integer> subset = new ArrayList<>(subsets.get(i)); // a copy: the old one stays
				subset.add(num);
				subsets.add(subset);
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
