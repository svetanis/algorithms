package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 90. Subsets II, with the answer in sorted order
//
// Given a list of integers that may repeat, return every distinct subset exactly once, the
// numbers inside each subset in non-decreasing order, and the subsets in lexicographic order.
//
// The start-index backtracking of SubsetsBacktracking, after sorting so equal numbers sit side by
// side. When a number's whole branch has returned, the while loop walks i past every equal copy,
// so at one level only the first of equal numbers starts a branch. A later copy can still be
// taken one level deeper, which is how [2, 2] is made. Sorting, and recording each subset on the
// way down, is what puts the subsets in lexicographic order.

public final class SubsetsIIBacktracking {
	// Time Complexity: O(n * 2^n), at most 2^n subsets, each copied with up to n elements
	// Space Complexity: O(n) besides the output, the recursion depth and the subset being built

	public static List<List<Integer>> generate(List<Integer> nums) {
		List<Integer> subset = new ArrayList<>();
		List<List<Integer>> subsets = new ArrayList<>();
		nums.sort(null); // equal numbers side by side
		dfs(nums, 0, subset, subsets);
		return subsets;
	}

	private static void dfs(List<Integer> nums, int index, //
			List<Integer> subset, List<List<Integer>> subsets) {
		subsets.add(new ArrayList<>(subset)); // every node is an answer, so record on entry
		for (int i = index; i < nums.size(); i++) {
			subset.add(nums.get(i)); // choose
			dfs(nums, i + 1, subset, subsets);
			subset.remove(subset.size() - 1); // undo
			// after the branch, skip the equal copies: they would start the same branch again
			while (i < nums.size() - 1 && nums.get(i).equals(nums.get(i + 1))) {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		// [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
		System.out.println(generate(new ArrayList<>(Arrays.asList(1, 2, 2))));
	}
}
