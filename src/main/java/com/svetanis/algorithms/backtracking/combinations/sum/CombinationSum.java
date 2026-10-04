package com.svetanis.algorithms.backtracking.combinations.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 39. Combination Sum
//
// Given an array of distinct integers candidates and a target, return every combination of
// candidates that sums to target, in any order. A candidate may be chosen any number of times;
// two combinations differ if some number appears a different number of times.
//
// Two methods, the same answers:
//   combinationSum           start-index: the loop tries every candidate from index on, and the
//                            next call starts at i, not i + 1, so the same candidate can be chosen
//                            again -- [2, 2, 3] -- but never one to its left, so [2, 3, 2] is not
//                            made as well
//   combinationSumTakeOrSkip take-or-skip: skip candidate index for good, or take one more copy of
//                            it and stay on it
// The candidates must be distinct: on {2, 2, 3} and 7 both return [2, 2, 3] three times, once for
// each way of starting it with one 2 or the other. AllUniqueCombinationsGivenSumWithRepetitions
// skips equal copies and returns it once.

public final class CombinationSum {
	// Time Complexity: one call per group of candidates with sum at most t, the target -- numbers
	// only grow along a path, so no group is reached twice. A loose upper bound is O(n^(t/m)),
	// n candidates, m the smallest: each level picks one of n, and a group has at most t/m numbers
	// Space Complexity: O(t/m) besides the output, the recursion depth and the combination built

	public static List<List<Integer>> combinationSum(int target, int[] candidates) {
		Arrays.sort(candidates); // so an overshoot also tells you about every later candidate
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
			if (sum + candidate <= target) { // an overshoot is never an answer
				combination.add(candidate); // choose
				dfs(target, i, sum + candidate, candidates, combination, combinations); // i: may repeat
				combination.remove(combination.size() - 1); // undo
			}
		}
	}

	public static List<List<Integer>> combinationSumTakeOrSkip(int target, int[] candidates) {
		Arrays.sort(candidates); // so the smallest candidate left is candidates[index]
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		takeOrSkip(0, target, candidates, combination, combinations);
		return combinations;
	}

	// left: how much of the target is still to reach
	private static void takeOrSkip(int index, int left, int[] candidates, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (left == 0) { // the target is reached exactly
			combinations.add(new ArrayList<>(combination));
			return;
		}
		if (index >= candidates.length || left < candidates[index]) { // nothing left that fits
			return;
		}
		int candidate = candidates[index];
		takeOrSkip(index + 1, left, candidates, combination, combinations); // SKIP: never again
		combination.add(candidate); // TAKE one more copy
		takeOrSkip(index, left - candidate, candidates, combination, combinations); // stay on it
		combination.remove(combination.size() - 1); // undo
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
