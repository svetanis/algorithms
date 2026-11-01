package com.svetanis.algorithms.backtracking.combinations.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 39. Combination Sum
//
// Input: an array of distinct integers candidates and a target. Returns every combination of
// candidates that sums to target, in any order. A candidate may be chosen any number of times; two
// combinations differ if some number appears a different number of times. On LeetCode:
// 1 <= candidates.length <= 30, 2 <= candidates[i] <= 40, all distinct, 1 <= target <= 40. The
// array is sorted in place. The candidates must be distinct: on {2, 2, 3} and 7 both methods return
// [2, 2, 3] three times, once for each way of starting it with one 2 or the other.
//
// The one idea: one call holds the combination so far (combination) and how much of the target it
// makes (sum). Two methods, the same answers:
//   combinationSum           start-index: the loop tries every candidate from index on, and the
//                            next call starts at i, not i + 1, so the same candidate can be chosen
//                            again -- [2, 2, 3] -- but never one to its left, so [2, 3, 2] is not
//                            made as well
//   combinationSumTakeOrSkip take-or-skip: skip candidate index for good, or take one more copy of
//                            it and stay on it
//
// Time: O(n^(t/m)) -- a loose upper bound, n candidates, t the target, m the smallest. One call per
// group of candidates with sum at most t; numbers only grow along a path, so no group is reached
// twice. Each level picks one of n, and a group has at most t/m numbers.
// Space: O(t/m) besides the output for combinationSum -- the recursion depth and the combination
// built. combinationSumTakeOrSkip is O(n + t/m): each skip also adds a level (candidates 2 to 31
// and target 40 reach depth 34, past t/m = 20).
//
// Siblings: AllUniqueCombinationsGivenSumWithRepetitions skips equal copies and returns
// [2, 2, 3] once. CombinationSumII is 40, each candidate used once.

public final class CombinationSum {
	public static List<List<Integer>> combinationSum(int target, int[] candidates) {
		Arrays.sort(candidates); // SORT: an overshoot also tells you about every later candidate
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(target, 0, 0, candidates, combination, combinations);
		return combinations;
	}

	private static void dfs(int target, int index, int sum, int[] candidates, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (sum == target) { // RECORD: only an exact sum is an answer
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			int candidate = candidates[i];
			if (sum + candidate <= target) { // PRUNE: an overshoot is never an answer
				combination.add(candidate); // CHOOSE
				dfs(target, i, sum + candidate, candidates, combination, combinations); // RECURSE: i, may repeat
				combination.remove(combination.size() - 1); // UNDO
			}
		}
	}

	public static List<List<Integer>> combinationSumTakeOrSkip(int target, int[] candidates) {
		Arrays.sort(candidates); // SORT: the smallest candidate left is candidates[index]
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		takeOrSkip(0, target, candidates, combination, combinations);
		return combinations;
	}

	// left: how much of the target is still to reach
	private static void takeOrSkip(int index, int left, int[] candidates, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (left == 0) { // RECORD: the target is reached exactly
			combinations.add(new ArrayList<>(combination));
			return;
		}
		if (index >= candidates.length || left < candidates[index]) { // BASE: none fits
			return;
		}
		int candidate = candidates[index];
		takeOrSkip(index + 1, left, candidates, combination, combinations); // SKIP: never again
		combination.add(candidate); // TAKE one more copy
		takeOrSkip(index, left - candidate, candidates, combination, combinations); // stay on it
		combination.remove(combination.size() - 1); // UNDO
	}

	public static void main(String[] args) {
		System.out.println(combinationSum(7, new int[] { 2, 3, 6, 7 })); // [[2, 2, 3], [7]]
		System.out.println(combinationSum(8, new int[] { 2, 3, 5 })); // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
		System.out.println(combinationSum(1, new int[] { 2 })); // []
		System.out.println(combinationSum(1, new int[] { 1 })); // [[1]]
		System.out.println(combinationSum(2, new int[] { 1 })); // [[1, 1]]
		System.out.println(combinationSumTakeOrSkip(7, new int[] { 2, 3, 6, 7 })); // [[7], [2, 2, 3]]
	}
}
