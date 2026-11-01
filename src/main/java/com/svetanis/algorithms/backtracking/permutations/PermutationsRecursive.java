package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 46. Permutations (the recursive insertion method)
//
// Input: an array a of distinct integers. On LeetCode: 1 <= a.length <= 6,
// -10 <= a[i] <= 10, all values distinct. Returns every ordering of the numbers, in any order.
// With repeated values it still returns n! lists, one per ordering of the positions.
//
// The one idea: permute(a, index, curr, lists) holds in curr one ordering of the first index
// numbers of a. The next number, a[index], goes into curr at every possible place -- before
// the first element, between each two, after the last -- and each result is handled the same
// way for a[index + 1]. A list of k numbers gives k + 1 lists of k + 1, and every ordering of
// k + 1 numbers comes from exactly one ordering of k (remove the new number from it). When
// index reaches the length of a, curr is a finished ordering. Each call makes a new list, so
// there is nothing to undo.
//
// Time: O(n * n!) -- the calls at depth k number k!, each making k + 1 lists of k numbers; the
// deepest level dominates with n! lists of n.
// Space: O(n^2) beyond the answer -- recursion depth n, each level holding a list of up to n numbers.
//
// Siblings: PermutationsIterative is this insertion done round by round with a queue instead of
// recursion; Permutations46 fills the positions left to right with a flag per number.

public final class PermutationsRecursive {

	public static List<List<Integer>> permute(int[] a) {
		List<Integer> list = new ArrayList<>();
		List<List<Integer>> lists = new ArrayList<>();
		permute(a, 0, list, lists);
		return lists;
	}

	public static void permute(int[] a, int index, List<Integer> curr, List<List<Integer>> lists) {
		if (index == a.length) {
			lists.add(new ArrayList<>(curr)); // RECORD: every number is in
		} else {
			// create a new permutations by adding
			// number at every position
			for (int i = 0; i <= curr.size(); i++) {
				List<Integer> next = new ArrayList<>();
				next.addAll(curr);
				next.add(i, a[index]); // INSERT: a[index] at place i
				permute(a, index + 1, next, lists);
			}
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5 };
		System.out.println(permute(a)); // [[5, 3, 1], [3, 5, 1], [3, 1, 5], [5, 1, 3], [1, 5, 3], [1, 3, 5]]
	}
}
