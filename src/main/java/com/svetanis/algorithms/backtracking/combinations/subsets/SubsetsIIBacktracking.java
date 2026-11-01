package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 90. Subsets II, with the answer in sorted order
//
// Input: a list of integers that may repeat. Returns every distinct subset exactly once, the
// numbers inside each subset in non-decreasing order, and the subsets in lexicographic order. On LeetCode:
// 1 <= nums.length <= 10, -10 <= nums[i] <= 10. The list is sorted in place.
//
// The one idea: the start-index backtracking of SubsetsBacktracking (one call is one subset, the
// index is the first position still allowed), after sorting so equal numbers sit side by side.
// When a number's whole branch has returned, the while loop walks i past every equal copy, so at
// one level only the first of equal numbers starts a branch. A later copy can still be taken one
// level deeper, which is how [2, 2] is made. Sorting, and recording each subset on the way down, is
// what puts the subsets in lexicographic order.
//
// Time: O(n * 2^n) -- at most 2^n subsets, each copied with up to n elements.
// Space: O(n) besides the output -- the recursion depth and the subset being built.
//
// Siblings: SubsetsIITakeOrSkip, SubsetsIIBitmaskSet, SubsetsIIBitmaskSkip and SubsetsIICopyAndAdd
// solve 90 other ways; AllUniqueCombinationsSizeKFromArray has the same repeat rule.

public final class SubsetsIIBacktracking {
	public static List<List<Integer>> generate(List<Integer> nums) {
		List<Integer> subset = new ArrayList<>();
		List<List<Integer>> subsets = new ArrayList<>();
		nums.sort(null); // SORT: equal numbers side by side
		dfs(nums, 0, subset, subsets);
		return subsets;
	}

	private static void dfs(List<Integer> nums, int index, //
			List<Integer> subset, List<List<Integer>> subsets) {
		subsets.add(new ArrayList<>(subset)); // RECORD: every node is an answer, so record on entry
		for (int i = index; i < nums.size(); i++) {
			subset.add(nums.get(i)); // CHOOSE
			dfs(nums, i + 1, subset, subsets); // RECURSE: i + 1
			subset.remove(subset.size() - 1); // UNDO
			// SKIP: after the branch, skip the equal copies: they would start the same branch again
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
