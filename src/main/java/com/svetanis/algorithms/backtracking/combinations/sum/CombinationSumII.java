package com.svetanis.algorithms.backtracking.combinations.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 40. Combination Sum II
//
// Given a collection of candidates, which may repeat, and a target, return every distinct
// combination of candidates that sums to target, each candidate used at most once.
//
// Start-index backtracking, sorted first so equal candidates sit side by side. The next call
// starts at i + 1, so each position is used at most once. At one level only the first of equal
// candidates starts a branch (i > index and equal to the one before: skip it), because a later
// copy would start the same branch again; a copy can still be taken one level deeper, which is
// how [1, 1, 6] is made. Sorted also means that once a candidate overshoots, every later one does.

public final class CombinationSumII {
	// Time Complexity: O(n * 2^n), every call is a different set of positions, at most 2^n, and
	// an answer is copied in up to n steps
	// Space Complexity: O(n) besides the output, the recursion depth and the combination built

	public static List<List<Integer>> combinationSum(int target, int[] candidates) {
		Arrays.sort(candidates); // equal candidates side by side; overshoots at the end
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(target, 0, 0, candidates, combination, combinations);
		return combinations;
	}

	private static void dfs(int target, int index, int sum, int[] candidates, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (sum == target) { // only an exact sum is an answer
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			int candidate = candidates[i];
			if (i > index && candidates[i] == candidates[i - 1]) {
				continue; // a later equal copy at this level would start the same branch again
			}
			if (sum + candidate > target) {
				break; // sorted: every later candidate overshoots too
			}
			combination.add(candidate); // choose
			dfs(target, i + 1, sum + candidate, candidates, combination, combinations); // i + 1: once
			combination.remove(combination.size() - 1); // undo
		}
	}

	public static void main(String[] args) {
		// [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
		System.out.println(combinationSum(8, new int[] { 10, 1, 2, 7, 6, 1, 5 }));
		// [[1, 2, 2], [5]]
		System.out.println(combinationSum(5, new int[] { 2, 5, 2, 1, 2 }));
	}
}
