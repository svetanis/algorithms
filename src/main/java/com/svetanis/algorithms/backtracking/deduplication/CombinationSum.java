package com.svetanis.algorithms.backtracking.deduplication;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

// 39. Combination Sum

// given an array of distinct integers and a target
// return a list of all unique combinations of candidates
// where the chosen numbers sum to target. 
// the same number may be chosen from candidates an 
// unlimited number of times. two combinations are unique
// if the frequency of at least one of the chosen numbers
// is different. 

public final class CombinationSum {
	// Time Complexity: O(2^n)

	public static ImmutableList<ImmutableList<Integer>> combinationSum(int target, List<Integer> candidates) {
		List<Integer> combination = new ArrayList<>();
		List<ImmutableList<Integer>> combinations = new ArrayList<>();
		dfs(target, 0, 0, candidates, combination, combinations);
		return newList(combinations);
	}

	private static void dfs(int target, int index, int sum, List<Integer> candidates, //
			List<Integer> combination, List<ImmutableList<Integer>> combinations) {
		if (sum == target) {
			combinations.add(newList(combination));
			return;
		}
		for (int i = index; i < candidates.size(); i++) {
			int candidate = candidates.get(i);
			if (sum + candidate <= target) {
				combination.add(candidate);
				dfs(target, i, sum + candidate, candidates, combination, combinations);
				combination.remove(combination.size() - 1);
			}
		}
	}

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
			if (sum + candidate <= target) {
				combination.add(candidate);
				dfs(target, i, sum + candidate, candidates, combination, combinations);
				combination.remove(combination.size() - 1);
			}
		}
	}

	public static List<List<Integer>> combinationSum2(int target, int[] candidates) {
		Arrays.sort(candidates);
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		dfs2(0, target, candidates, combination, combinations);
		return combinations;
	}

	private static void dfs2(int index, int sum, int[] candidates, List<Integer> combination,
			List<List<Integer>> combinations) {
		if (sum == 0) {
			combinations.add(new ArrayList<>(combination));
			return;
		}
		if (index >= candidates.length || sum < candidates[index]) {
			return;
		}
		int candidate = candidates[index];
		dfs2(index + 1, sum, candidates, combination, combinations);
		combination.add(candidate);
		dfs2(index, sum - candidate, candidates, combination, combinations);
		combination.remove(combination.size() - 1);
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 6, 7 };
		System.out.println(combinationSum(7, a1));// [2,2,3],[7]
		int[] a2 = { 2, 3, 5 };
		System.out.println(combinationSum(8, a2)); // [2,2,2,2],[2,3,3],[3,5]
		int[] a3 = { 2 };
		System.out.println(combinationSum(1, a3)); // []
		int[] a4 = { 1 };
		System.out.println(combinationSum(1, a4)); // [1]
		int[] a5 = { 1 };
		System.out.println(combinationSum(2, a5)); // [1,1]

		System.out.println(combinationSum(7, asList(2, 3, 6, 7)));// [2,2,3],[7]
		System.out.println(combinationSum(8, asList(2, 3, 5))); // [2,2,2,2],[2,3,3],[3,5]
		System.out.println(combinationSum(1, asList(2))); // []
		System.out.println(combinationSum(1, asList(1))); // [1]
		System.out.println(combinationSum(2, asList(1))); // [1,1]
	}
}