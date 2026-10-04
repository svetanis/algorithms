package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 90. Subsets II
//
// Given an array that may contain repeated numbers, return every subset of it, each distinct
// subset exactly once, in any order.
//
// Take-or-skip recursion, from the last element down, after sorting so equal numbers sit side
// by side. Taking a copy just moves on: {3, 3} is a real subset. Skipping a copy must skip
// every equal copy before it too, because "took one 3" and "took the other 3" are the same
// subset; the while loop is that "all of them".

public final class SubsetsIITakeOrSkip {
	// Time Complexity: O(n * 2^n), at most 2^n leaves, each copying a subset of up to n elements
	// Space Complexity: O(n) besides the output, the recursion depth and the subset being built

	public static List<List<Integer>> subsets(int[] a) {
		Arrays.sort(a);
		List<Integer> subset = new ArrayList<>();
		List<List<Integer>> subsets = new ArrayList<>();
		dfs(a, a.length - 1, subset, subsets);
		return subsets;
	}

	private static void dfs(int[] a, int index, List<Integer> subset, List<List<Integer>> subsets) {
		if (index < 0) { // every element decided: one subset
			subsets.add(new ArrayList<>(subset));
			return;
		}
		// TAKE a[index]: later copies still matter, so just move on
		subset.add(a[index]);
		dfs(a, index - 1, subset, subsets);
		// SKIP a[index]: undo the take
		subset.remove(subset.size() - 1);
		// skipping one copy skips all its equal neighbours, or the same subset comes out twice
		while (index > 0 && a[index] == a[index - 1]) {
			index--;
		}
		dfs(a, index - 1, subset, subsets);
	}

	public static void main(String[] args) {
		// [[2, 1, 1], [2, 1], [2], [1, 1], [1], []]
		System.out.println(subsets(new int[] { 1, 2, 1 }));
		// [[5, 3, 3, 1], [5, 3, 3], [5, 3, 1], [5, 3], [5, 1], [5], [3, 3, 1], [3, 3], [3, 1], [3], [1], []]
		System.out.println(subsets(new int[] { 1, 3, 3, 5 }));
	}
}
