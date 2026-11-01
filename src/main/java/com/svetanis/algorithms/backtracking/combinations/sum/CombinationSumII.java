package com.svetanis.algorithms.backtracking.combinations.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 40. Combination Sum II
//
// Input: a collection of candidates, which may repeat, and a target. Returns every distinct
// combination of candidates that sums to target, each candidate used at most once. On LeetCode:
// 1 <= candidates.length <= 100, 1 <= candidates[i] <= 50, 1 <= target <= 30. The array is sorted
// in place.
//
// The one idea: start-index backtracking, sorted first so equal candidates sit side by side. One
// call holds the combination so far (combination) and its sum. The next call starts at i + 1, so
// each position is used at most once. At one level only the first of equal candidates starts a
// branch (i > index and equal to the one before: skip it), because a later copy would start the
// same branch again; a copy can still be taken one level deeper, which is how [1, 1, 6] is made.
// Sorted also means that once a candidate overshoots, every later one does.
//
// Time: O(n * 2^n) -- every call is a different set of positions, at most 2^n, and an answer is
// copied in up to n steps.
// Space: O(n) besides the output -- the recursion depth and the combination built.
//
// Siblings: AllUniqueCombinationsGivenSumWithoutRepetitions is the same problem on a list, with
// the repeat rule as a prev value; CombinationSum is 39, each candidate reusable.

public final class CombinationSumII {
	public static List<List<Integer>> combinationSum(int target, int[] candidates) {
		Arrays.sort(candidates); // PREPARE: equal candidates side by side; overshoots at the end
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(target, 0, 0, candidates, combination, combinations); // SEARCH: from the first, sum 0
		return combinations;
	}

	private static void dfs(int target, int index, int sum, int[] candidates, //
			List<Integer> combination, List<List<Integer>> combinations) {
		// RECORD: only an exact sum is an answer, at any depth -- so this check comes before
		// anything about index, or a combination ending with the last candidate is lost
		if (sum == target) {
			combinations.add(new ArrayList<>(combination));
			return; // no cleanup here: each add below has its own remove
		}
		// CHOOSE: the next candidate, from index on. Moving on to i + 1 is already "leave
		// candidate i out" -- no separate skip call
		for (int i = index; i < candidates.length; i++) {
			int candidate = candidates[i];
			// SKIP: this loop tried the same value one pass ago, and that branch already builds
			// everything this one could. Never on the loop's first try (i == index): there
			// candidates[i - 1] is already in the combination, not a choice of this loop
			if (i > index && candidates[i] == candidates[i - 1]) {
				continue;
			}
			if (sum + candidate > target) {
				break; // SKIP the rest: sorted, so every later candidate overshoots too
			}
			combination.add(candidate); // TAKE
			dfs(target, i + 1, sum + candidate, candidates, combination, combinations); // GO: i + 1
			combination.remove(combination.size() - 1); // PUT BACK
		}
	}

	public static void main(String[] args) {
		// [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
		System.out.println(combinationSum(8, new int[] { 10, 1, 2, 7, 6, 1, 5 }));
		// [[1, 2, 2], [5]]
		System.out.println(combinationSum(5, new int[] { 2, 5, 2, 1, 2 }));
	}
}
