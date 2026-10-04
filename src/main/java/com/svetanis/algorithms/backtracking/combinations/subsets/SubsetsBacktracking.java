package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
//
// Given an array of distinct integers, return every subset of it (the power set), the empty
// subset and the whole array included, in any order.
//
// Backtracking with a start index: every call is one subset, so it is recorded on entry, not
// only at the leaves; the loop then extends it by each later element in turn. Passing i + 1
// means an element is never chosen before one to its left, so no subset is built twice in
// two orders. The bitmask version of the same problem is SubsetsBitmask.

public final class SubsetsBacktracking {
	// Time Complexity: O(n * 2^n), 2^n calls, each copying a subset of up to n elements
	// Space Complexity: O(n) besides the output, the recursion depth and the subset being built

	public List<List<Integer>> subsets(int[] a) {
		List<Integer> subset = new ArrayList<>();
		List<List<Integer>> subsets = new ArrayList<>();
		dfs(0, a, subset, subsets);
		return subsets;
	}

	private void dfs(int index, int[] a, List<Integer> subset, List<List<Integer>> subsets) {
		subsets.add(new ArrayList<>(subset)); // every node is an answer, so record on entry
		for (int i = index; i < a.length; i++) {
			subset.add(a[i]); // choose
			dfs(i + 1, a, subset, subsets); // i + 1: only elements to the right from here
			subset.remove(subset.size() - 1); // undo
		}
	}

	public static void main(String[] args) {
		// [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
		System.out.println(new SubsetsBacktracking().subsets(new int[] { 1, 2, 3 }));
	}
}
