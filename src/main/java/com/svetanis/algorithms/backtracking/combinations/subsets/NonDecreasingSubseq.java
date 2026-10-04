package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.List;

// 491. Non-decreasing Subsequences
//
// Given an integer array, return every different non-decreasing subsequence of it with at least
// two elements, in any order. [4, 6, 7, 7] gives [4, 6], [4, 6, 7], [4, 6, 7, 7], [4, 7],
// [4, 7, 7], [6, 7], [6, 7, 7], [7, 7].
//
// Take-or-skip, carrying prev, the last value taken. A number may be taken only if it is at
// least prev, which keeps the subsequence non-decreasing. Repeats are stopped by one rule: a
// number equal to prev is never skipped. Skipping it would only make again what the branch that
// skipped the earlier copy makes -- for [6, 7, 7], "take the first 7, skip the second" and
// "skip the first 7, take the second" are both [6, 7]. With the rule, only the second spelling
// is built. The array is not sorted (order is the point), so equal values need not sit side by
// side; the rule does not need them to.

public final class NonDecreasingSubseq {
	// Time Complexity: O(n * 2^n), every number taken or skipped, at most 2^n leaves, each answer
	// copied in up to n steps
	// Space Complexity: O(n) besides the output, the recursion depth and the subsequence built

	public static List<List<Integer>> subsequences(int[] nums) {
		List<Integer> subsequence = new ArrayList<>();
		List<List<Integer>> subsequences = new ArrayList<>();
		dfs(nums, 0, Integer.MIN_VALUE, subsequence, subsequences); // nothing taken yet: any number fits
		return subsequences;
	}

	private static void dfs(int[] nums, int index, int prev, //
			List<Integer> subsequence, List<List<Integer>> subsequences) {
		if (index == nums.length) { // every number decided
			if (subsequence.size() > 1) { // at least two elements
				subsequences.add(new ArrayList<>(subsequence));
			}
			return;
		}
		if (nums[index] >= prev) { // TAKE: only if it keeps the order non-decreasing
			subsequence.add(nums[index]);
			dfs(nums, index + 1, nums[index], subsequence, subsequences);
			subsequence.remove(subsequence.size() - 1); // undo
		}
		if (nums[index] != prev) { // SKIP: never a copy of the last value taken
			dfs(nums, index + 1, prev, subsequence, subsequences);
		}
	}

	public static void main(String[] args) {
		// [[4, 6, 7, 7], [4, 6, 7], [4, 6], [4, 7, 7], [4, 7], [6, 7, 7], [6, 7], [7, 7]]
		System.out.println(subsequences(new int[] { 4, 6, 7, 7 }));
		// [[4, 4]]
		System.out.println(subsequences(new int[] { 4, 4, 3, 2, 1 }));
	}
}
