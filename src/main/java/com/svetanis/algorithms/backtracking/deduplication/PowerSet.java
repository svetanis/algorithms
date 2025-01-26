package com.svetanis.algorithms.backtracking.deduplication;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

// 78. Subsets

// given a set of distinct integers,
// return all possible subsets (power set)
// the solutions set must not contain
// duplicate subsets

public final class PowerSet {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public List<List<Integer>> subsets(int[] a) {
		List<Integer> list = new ArrayList<>();
		List<List<Integer>> lists = new ArrayList<>();
		dfs(0, a, list, lists);
		return lists;
	}

	private void dfs(int index, int[] a, List<Integer> list, List<List<Integer>> lists) {
		lists.add(new ArrayList<>(list));
		for (int i = index; i < a.length; i++) {
			list.add(a[i]);
			dfs(i + 1, a, list, lists);
			list.remove(list.size() - 1);
		}
	}

	public static ImmutableList<ImmutableList<Integer>> powerSet(List<Integer> nums) {
		List<Integer> list = new ArrayList<>();
		List<ImmutableList<Integer>> lists = new ArrayList<>();
		dfs(0, nums, list, lists);
		return newList(lists);
	}

	private static void dfs(int index, List<Integer> nums, List<Integer> list, List<ImmutableList<Integer>> lists) {
		lists.add(newList(list));
		for (int i = index; i < nums.size(); i++) {
			list.add(nums.get(i));
			dfs(i + 1, nums, list, lists);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		// [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
		System.out.println(powerSet(asList(1, 2, 3)));
	}
}