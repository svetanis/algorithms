package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 47. Permutations II
//
// Input: an array a that may hold repeated integers. On LeetCode: 1 <= a.length <= 8,
// -10 <= a[i] <= 10. Returns every DISTINCT ordering, in any order. The array is sorted in place.
//
// The one idea: the same walk as Permutations46 -- fill the positions left to right, trying
// every unused number at each -- but equal values would make the same ordering twice. After
// sorting, equal values sit side by side, and the rule is: of a run of equal values, they are
// always used in left-to-right order. So a[i] may be placed only if its equal left neighbour
// a[i - 1] is already placed (visited[i - 1]); otherwise it is skipped. Placing the right copy
// before the left one would give an ordering the left-first rule already gives.
//
// Time: O(n * n!) -- at most n! orderings, each copied out in O(n); each tree node scans n flags.
// Space: O(n) beyond the answer -- recursion depth n and the flags; the answer holds up to n! lists of n.
//
// Siblings: Permutations46 is the same walk for distinct values and does not sort.

public final class Permutations47 {

	public static List<List<Integer>> permutations(int[] a) {
		Arrays.sort(a); // SORT: equal values side by side
		List<List<Integer>> permutations = new ArrayList<>();
		List<Integer> permutation = new ArrayList<>();
		boolean[] visited = new boolean[a.length];
		dfs(a, visited, permutation, permutations);
		return permutations;
	}

	private static void dfs(int[] a, boolean[] visited,
			List<Integer> permutation, List<List<Integer>> permutations) {
		if (permutation.size() == a.length) {
			permutations.add(new ArrayList<>(permutation)); // RECORD: a copy -- the list keeps changing
			return;
		}
		for (int i = 0; i < a.length; i++) {
			boolean dups = i > 0 && a[i] == a[i - 1];
			if (visited[i] || (dups && !visited[i - 1])) { // SKIP: placed already, or its equal left copy is not placed yet
				continue;
			}
			permutation.add(a[i]); // PLACE: a[i] takes the next position
			visited[i] = true;
			dfs(a, visited, permutation, permutations);
			permutation.remove(permutation.size() - 1); // UNDO: take it back
			visited[i] = false;
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 2 };
		System.out.println(permutations(a)); // [[1, 1, 2], [1, 2, 1], [2, 1, 1]]

		int[] a1 = { 1, 2, 3 };
		// [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
		System.out.println(permutations(a1));
	}
}
