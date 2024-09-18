package com.svetanis.algorithms.backtracking.deduplication;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

// given a set of distinct integers,
// return all possible subsets (power set)
// the solutions set must not contain
// duplicate subsets

public final class PowerSet {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<ImmutableList<Integer>> powerSet(List<Integer> nums) {
		List<Integer> path = new ArrayList<>();
		List<ImmutableList<Integer>> list = new ArrayList<>();
		dfs(0, nums, path, list);
		return newList(list);
	}

	private static void dfs(int index, List<Integer> nums, List<Integer> path, 
			List<ImmutableList<Integer>> list) {
		list.add(newList(path));
		for (int i = index; i < nums.size(); i++) {
			path.add(nums.get(i));
			dfs(i + 1, nums, path, list);
			path.remove(path.size() - 1);
		}
	}

	public static void main(String[] args) {
		// [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
		System.out.println(powerSet(asList(1, 2, 3)));
	}
}