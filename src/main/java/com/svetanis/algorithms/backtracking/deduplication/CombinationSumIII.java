package com.svetanis.algorithms.backtracking.deduplication;

import java.util.ArrayList;
import java.util.List;

// 216. Combination Sum III

// find all valid combinations of k numbers that
// sum up to n such that the following conditions are true
// only numbers 1 through 9 are used
// each number is used at most once
// return the list of all possible valid combinations

public final class CombinationSumIII {
	// Time Complexity: O(n * 2^n)

	public static List<List<Integer>> combinationSum(int k, int target) {
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> combination = new ArrayList<>();
		dfs(target, 1, 0, k, combination, combinations);
		return combinations;
	}

	private static void dfs(int target, int index, int sum, int k, List<Integer> combination,
			List<List<Integer>> combinations) {
		if (sum == target && combination.size() == k) {
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for (int candidate = index; candidate <= 9; candidate++) {
			if (sum + candidate <= target && combination.size() < k) {
				combination.add(candidate);
				dfs(target, candidate + 1, sum + candidate, k, combination, combinations);
				combination.remove(combination.size() - 1);
			}
		}
	}

	public static List<List<Integer>> combinationSum2(int k, int target) {
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> combination = new ArrayList<>();
		dfs2(1, target, k, combination, combinations);
		return combinations;
	}

	private static void dfs2(int index, int sum, int k, List<Integer> combination, List<List<Integer>> combinations) {
		if (sum == 0) {
			if (combination.size() == k) {
				combinations.add(new ArrayList<>(combination));
			}
			return; // backtrack
		}
		if (index > 9 || index > sum || combination.size() >= k) {
			return;
		}
		combination.add(index);
		dfs2(index + 1, sum - index, k, combination, combinations);
		combination.remove(combination.size() - 1);
		dfs2(index + 1, sum, k, combination, combinations);
	}

	public static void main(String[] args) {
		System.out.println(combinationSum2(3, 7)); // [[1,2,4]]
		System.out.println(combinationSum2(3, 9)); // [[1,2,6],[1,3,5],[2,3,4]]
		System.out.println(combinationSum2(4, 1)); // [[]]
	}
}