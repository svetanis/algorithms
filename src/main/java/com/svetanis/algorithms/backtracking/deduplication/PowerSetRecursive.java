package com.svetanis.algorithms.backtracking.deduplication;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

// 78. Subsets

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

	public static ImmutableList<ImmutableList<Integer>> subsets(int[] a) {
		sort(a);
		List<ImmutableList<Integer>> lists = newArrayList();
		List<Integer> list = newArrayList();
		subsets(a, a.length - 1, list, lists);
		return newList(lists);
	}

	private static void subsets(int[] a, int i, List<Integer> list, 
			List<ImmutableList<Integer>> lists) {
		if (i < 0) {
			lists.add(newList(list));
			return;
		}
		// include current element
		// in the current subset
		list.add(a[i]);
		// and recurse
		subsets(a, i - 1, list, lists);
		// exclude current element
		// from the current subset
		list.remove(list.size() - 1);
		// remove adjacent duplicate elements
		while (i > 0 && a[i] == a[i - 1]) {
			i--;
		}
		// recurse with excluded current element
		subsets(a, i - 1, list, lists);
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 1 };
		System.out.println(subsets(a));

		int[] a1 = { 1, 3, 3, 5 };
		System.out.println(subsets(a1));
	}
}
