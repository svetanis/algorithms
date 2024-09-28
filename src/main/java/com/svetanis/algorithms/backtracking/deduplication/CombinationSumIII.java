package com.svetanis.algorithms.backtracking.deduplication;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

// find all valid combinations of k numbers that
// sum up to n such that the following conditions are true
// only numbers 1 through 9 are used
// each number is used at most once
// return the list of all possible valid combinations

public final class CombinationSumIII {
	// Time Complexity: O(n * 2^n)

	public static ImmutableList<ImmutableList<Integer>> combinationSum(int k, int target) {
		List<ImmutableList<Integer>> lists = newArrayList();
		List<Integer> path = newArrayList();
		dfs(target, 1, 0, k, path, lists);
		return newList(lists);
	}

	private static void dfs(int target, int index, int sum, int k, List<Integer> path,
			List<ImmutableList<Integer>> list) {
		if (sum == target && path.size() == k) {
			list.add(newList(path));
			return;
		}

		for (int candidate = index; candidate <= 9; candidate++) {
			if (sum + candidate <= target && path.size() < k) {
				path.add(candidate);
				dfs(target, candidate + 1, sum + candidate, k, path, list);
				path.remove(path.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(combinationSum(3, 7)); // [[1,2,4]]
		System.out.println(combinationSum(3, 9)); // [[1,2,6],[1,3,5],[2,3,4]]
		System.out.println(combinationSum(4, 1)); // [[]]
	}
}