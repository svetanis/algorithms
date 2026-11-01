package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
//
// Input: an array of distinct integers. Returns every subset of it (the power set), the empty
// subset and the whole array included, in any order. On LeetCode: 1 <= nums.length <= 10,
// -10 <= nums[i] <= 10, all distinct.
//
// The one idea: backtracking with a start index. One call is one subset (subset), so it is recorded
// on entry, not only at the leaves; the loop then extends it by each later element in turn. Passing
// i + 1 means an element is never chosen before one to its left, so no subset is built twice in two
// orders.
//
// Time: O(n * 2^n) -- 2^n calls, each copying a subset of up to n elements.
// Space: O(n) besides the output -- the recursion depth and the subset being built.
//
// Siblings: SubsetsBitmask -- the same problem with one number per subset; SubsetsCopyAndAdd -- no
// recursion, the list doubles per number. SubsetsIIBacktracking adds the repeat rule for 90.

public final class SubsetsBacktracking {
	public List<List<Integer>> subsets(int[] a) {
		List<Integer> subset = new ArrayList<>();
		List<List<Integer>> subsets = new ArrayList<>();
		dfs(0, a, subset, subsets);
		return subsets;
	}

	private void dfs(int index, int[] a, List<Integer> subset, List<List<Integer>> subsets) {
		subsets.add(new ArrayList<>(subset)); // RECORD: every node is an answer, so record on entry
		for (int i = index; i < a.length; i++) {
			subset.add(a[i]); // CHOOSE
			dfs(i + 1, a, subset, subsets); // RECURSE: i + 1, only elements to the right from here
			subset.remove(subset.size() - 1); // UNDO
		}
	}

	public static void main(String[] args) {
		// [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
		System.out.println(new SubsetsBacktracking().subsets(new int[] { 1, 2, 3 }));
	}
}
