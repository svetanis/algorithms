package com.svetanis.algorithms.backtracking.deduplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 40. Combination Sum II

// given an array of distinct integers and a target
// return a list of all unique combinations of candidates
// where the chosen numbers sum to target. 
// the same number may only be used once. 

public final class CombinationSumII {
	// Time Complexity: O(2^n)

	public static List<List<Integer>> combinationSum(int target, int[] candidates) {
		Arrays.sort(candidates);
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(target, 0, 0, candidates, combination, combinations);
		return combinations;
	}

	private static void dfs(int target, int index, int sum, int[] candidates, List<Integer> combination,
			List<List<Integer>> combinations) {
		if (sum == target) {
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			int candidate = candidates[i];
			if (i > index && candidates[i] == candidates[i - 1]) {
				continue;
			}
			if (sum + candidate <= target) {
				combination.add(candidate);
				dfs(target, i + 1, sum + candidate, candidates, combination, combinations);
				combination.remove(combination.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 1, 2, 7, 6, 1, 5 };
		System.out.println(combinationSum(8, a1));
		int[] a2 = { 2, 5, 2, 1, 2 };
		System.out.println(combinationSum(5, a2));
	}
}