package com.svetanis.algorithms.backtracking.deduplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 90. Subsets II

// given a set, generate all distinct subsets
// i.e. find distinct power set of a set

// to exclude duplicates, sort the set
// and exclude all adjacent duplicates

// Power set of any set S is the set of all
// subsets of S, including the empty set 
// and S itself

// similar to 0/1 knapsack problem where
// for each element in set S, we have 2 options:
// 1. consider that element
// 2. don't consider that element

public final class PowerSetRecursive {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static List<List<Integer>> subsets(int[] a) {
		Arrays.sort(a);
		List<Integer> list = new ArrayList<>();
		List<List<Integer>> lists = new ArrayList<>();
		dfs(a, a.length - 1, list, lists);
		return lists;
	}

	private static void dfs(int[] a, int index, List<Integer> list, List<List<Integer>> lists) {
		if (index < 0) {
			lists.add(new ArrayList<>(list));
			return;
		}
		// include current element
		// in the current subset
		list.add(a[index]);
		// and recurse
		dfs(a, index - 1, list, lists);
		// exclude current element
		// from the current subset
		list.remove(list.size() - 1);
		// remove adjacent duplicate elements
		while (index > 0 && a[index] == a[index - 1]) {
			index--;
		}
		// recurse with excluded current element
		dfs(a, index - 1, list, lists);
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 1 };
		System.out.println(subsets(a));

		int[] a1 = { 1, 3, 3, 5 };
		System.out.println(subsets(a1));
	}
}
