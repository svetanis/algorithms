package com.svetanis.algorithms.backtracking.combinations;

import static com.svetanis.java.base.utils.Print.printLines;

import java.util.ArrayList;
import java.util.List;

// 77. Combinations

// Given two integers n and k, 
// return all possible combinations of k numbers out of 1 2 3 ... n.
// Make sure the combinations are sorted.
// Within every entry, elements should be sorted. 
// [1, 4] is a valid entry while [4, 1] is not.
// Entries should be sorted within themselves.

public final class AllUniqueCombinationsSizeK {
	// Time Complexity: O(2^n)

	public static List<List<Integer>> combinations(int n, int k) {
		List<Integer> combination = new ArrayList<>();
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(n, k, 1, combination, combinations);
		return combinations;
	}

	private static void dfs(int n, int k, int index, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (combination.size() == k) {
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for (int i = index; i <= n; i++) {
			combination.add(i);
			dfs(n, k, i + 1, combination, combinations);
			combination.remove(combination.size() - 1); // backtrack
		}
	}

	private static void dfs2(int n, int k, int index, List<Integer> combination, List<List<Integer>> combinations) {
		if (combination.size() == k) {
			combinations.add(new ArrayList<>(combination));
			return;
		}
		if (index > n) {
			return;
		}
		combination.add(index);
		dfs2(n, k, index + 1, combination, combinations);
		combination.remove(combination.size() - 1);
		dfs2(n, k, index + 1, combination, combinations);
	}

	public static void main(String[] args) {
		printLines(combinations(5, 3));
		System.out.println(combinations(4, 2));
		// [1,2] [1,3] [1,4] [2,3] [2,4] [3,4]
		System.out.println(combinations(1, 1));

	}
}

// [1, 2, 3]
// [1, 2, 4]
// [1, 2, 5]
// [1, 3, 4]
// [1, 3, 5]
// [1, 4, 5]
// [2, 3, 4]
// [2, 3, 5]
// [2, 4, 5]
// [3, 4, 5]
