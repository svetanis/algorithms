package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

// Count Pairs With Difference Less Than k
//
// Input: an array of integers, in any order, and a number k.
// Return: how many pairs of positions (i, j), i < j, have |a[i] - a[j]| < k.
//
// The one idea: after sorting, the partners of a[i] to its right that are close enough
// form one run a[i + 1..j - 1] -- the difference only grows as j moves right -- so walk
// j right from i + 1 and stop at the first difference that is too big.
//
// Siblings:
//   PairsLessGivenSum -- a sorted array, pairs with sum below k counted a run at a time
//   search.binary.invariant.KthSmallestPairDistance (LC 719) -- counts pairs with
//     difference at most d using a right end that never moves back
//
// Time: O(n log n + p) -- the sort, then one step per pair counted (p), which is
// O(n^2) when k is large.
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class CountPairsLessGivenDiff {

	public static int count(int[] a, int k) {
		sort(a);                               // SORT: the partners of a[i] form one run to its right
		int count = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int j = i + 1;                     // START: the nearest number to the right
			while (j < n && a[j] - a[i] < k) { // STOP at the first difference that is too big
				count++;                       // COUNT the pair (i, j)
				j++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 10, 4, 2 };
		System.out.println(count(a1, 3)); // 2

		int[] a2 = { 1, 8, 7 };
		System.out.println(count(a2, 7)); // 2
	}
}
