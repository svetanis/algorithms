package com.svetanis.algorithms.backtracking.combinations.choosek;

import java.util.ArrayList;
import java.util.List;

// 77. Combinations
//
// Given two integers n and k, return every combination of k numbers chosen from 1 .. n, in any
// order. [1, 2] and [2, 1] are the same combination.
//
// Two methods, the same answers in the same order:
//   combinations             start-index: the loop tries every number from index on, and the
//                            next call starts at i + 1, so numbers only grow along a combination
//   combinationsTakeOrSkip   take-or-skip: decide each number in turn, take it or not
// Both record a combination only when it holds k numbers; the smaller ones on the way are not
// answers here, which is what makes this Combinations rather than Subsets.

public final class AllUniqueCombinationsSizeK {
	// Time Complexity: O(2^n + k * C(n, k)), the tree holds every set of up to k numbers, at most
	// 2^n, and each of the C(n, k) answers is copied in k steps
	// Space Complexity: O(n) besides the output, the recursion depth -- at most k for
	// combinations, n for combinationsTakeOrSkip -- and the combination being built

	public static List<List<Integer>> combinations(int n, int k) {
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(n, k, 1, combination, combinations);
		return combinations;
	}

	private static void dfs(int n, int k, int index, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (combination.size() == k) { // only full-size combinations are answers
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for (int i = index; i <= n; i++) {
			combination.add(i); // choose
			dfs(n, k, i + 1, combination, combinations); // i + 1: only larger numbers follow
			combination.remove(combination.size() - 1); // undo
		}
	}

	public static List<List<Integer>> combinationsTakeOrSkip(int n, int k) {
		List<List<Integer>> combinations = new ArrayList<>();
		takeOrSkip(n, k, 1, new ArrayList<>(), combinations);
		return combinations;
	}

	private static void takeOrSkip(int n, int k, int index, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (combination.size() == k) { // full before running out of numbers: one answer
			combinations.add(new ArrayList<>(combination));
			return;
		}
		if (index > n) { // every number decided and still short of k: not an answer
			return;
		}
		combination.add(index); // TAKE index
		takeOrSkip(n, k, index + 1, combination, combinations);
		combination.remove(combination.size() - 1); // SKIP index: undo the take
		takeOrSkip(n, k, index + 1, combination, combinations);
	}

	public static void main(String[] args) {
		// [[1, 2, 3], [1, 2, 4], [1, 2, 5], [1, 3, 4], [1, 3, 5], [1, 4, 5], [2, 3, 4], [2, 3, 5],
		// [2, 4, 5], [3, 4, 5]]
		System.out.println(combinations(5, 3));
		// [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
		System.out.println(combinations(4, 2));
		// [[1]]
		System.out.println(combinations(1, 1));
		// [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
		System.out.println(combinationsTakeOrSkip(4, 2));
	}
}
