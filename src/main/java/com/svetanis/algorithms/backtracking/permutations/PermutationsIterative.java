package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 46. Permutations (the iterative method)
//
// Input: an array a of distinct integers. On LeetCode: 1 <= a.length <= 6,
// -10 <= a[i] <= 10, all values distinct. Returns every ordering of the numbers, in any order.
// With repeated values it still returns n! lists, one per ordering of the positions.
//
// The one idea: the queue holds every ordering of the first k numbers of a, for the k numbers
// handled so far. To add the next number, take each ordering off the queue and put the number
// into it at every possible place -- before the first element, between each two, after the
// last. An ordering of k numbers gives k + 1 orderings of k + 1 numbers, and each ordering of
// the k + 1 numbers comes from exactly one ordering of k (remove the new number from it). Once
// the lists reach the length of a, they are finished and go to the answer.
//
// Time: O(n * n!) -- the lists of length k number k!, and each makes k + 1 new lists in O(k)
// work apiece; the last round, which dominates, makes n! lists of length n.
// Space: O(n * n!) -- the queue holds up to (n - 1)! lists of n - 1 numbers, and the answer n! lists of n.
//
// Siblings: Permutations46 and PermutationsBacktracking fill the positions left to right with
// a flag per number; PermutationsRecursive is this insertion done by recursion instead of a queue.

public final class PermutationsIterative {

	public static List<List<Integer>> permute(int[] a) {
		List<List<Integer>> lists = new ArrayList<>();
		Queue<List<Integer>> queue = new ArrayDeque<>();
		queue.add(new ArrayList<>());
		for (int num : a) { // WALK: one more number at a time
			int size = queue.size(); // one round = the orderings made before this number
			for (int i = 0; i < size; i++) {
				List<Integer> prev = queue.poll();
				// create a new permutation by adding
				// the current number at every position
				for (int j = 0; j <= prev.size(); j++) {
					List<Integer> list = new ArrayList<>();
					list.addAll(prev);
					list.add(j, num); // INSERT: num at place j
					if (list.size() == a.length) {
						lists.add(new ArrayList<>(list)); // RECORD: every number is in
					} else {
						queue.add(list); // KEEP: the next round inserts into it
					}
				}
			}
		}
		return lists;
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5 };
		System.out.println(permute(a)); // [[5, 3, 1], [3, 5, 1], [3, 1, 5], [5, 1, 3], [1, 5, 3], [1, 3, 5]]
	}
}
