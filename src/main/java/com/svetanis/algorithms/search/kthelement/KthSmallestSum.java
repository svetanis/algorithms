package com.svetanis.algorithms.search.kthelement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows

// Given an m x n matrix whose rows are each sorted in non-decreasing order,
// and k (1 <= k <= min(200, n^m)), pick exactly one number from every row.
// Return the kth smallest of all the sums that can be made that way.
// Duplicates count separately.

// Add the rows one at a time, keeping a list of partial sums. After each row,
// only the k smallest partial sums can lead to any of the k smallest full
// sums: a partial sum ranked below k has at least k partial sums no larger
// than it, each of which can be finished with the very same choices, so none
// of its finished sums is needed for the first k. Cut the list back to k after
// every row, and the answer is the kth.

// Time: O(m * k*n * log(k*n)) -- each row extends at most k sums by n choices
//   and sorts the k*n results
// Space: O(k*n) -- the sums built from one row

// Siblings: none for 1439 in the repo. KthSmallestMaxHeapPQ is the plain kth
// smallest of one array.

public final class KthSmallestSum {

	public static int kthSmallest(int[][] matrix, int k) {
		List<Integer> prev = new ArrayList<>(); // KEPT: the k smallest sums of the rows so far
		List<Integer> curr = new ArrayList<>();
		prev.add(0); // SEED: no rows yet, one empty sum
		for (int[] row : matrix) {
			curr.clear();
			for (int sum : prev) {
				for (int val : row) {
					curr.add(sum + val); // EXTEND every kept sum by every choice in this row
				}
			}
			Collections.sort(curr); // SORT the new sums, smallest first
			prev.clear();

			for (int i = 0; i < Math.min(k, curr.size()); i++) {
				prev.add(curr.get(i)); // CUT back to the k smallest -- the rest can never rise into the first k
			}
		}
		return prev.get(k - 1); // REPORT: the kth smallest full sum
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 3, 11 }, { 2, 4, 6 } };
		System.out.println(kthSmallest(m1, 5)); // 7

		int[][] m2 = { { 1, 3, 11 }, { 2, 4, 6 } };
		System.out.println(kthSmallest(m2, 9));// 17

		int[][] m3 = { { 1, 10, 10 }, { 1, 4, 5 }, { 2, 3, 6 } };
		System.out.println(kthSmallest(m3, 7)); // 9
	}
}