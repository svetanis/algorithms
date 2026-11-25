package com.svetanis.algorithms.search.treemap;

import java.util.TreeSet;

// 363. Max Sum of Rectangle No Larger Than K

// Given an n x m grid of integers and a limit k (named target here),
// return the largest sum of a rectangle inside the grid that is still no
// larger than k. As the code assumes: at least one rectangle is, which
// LeetCode promises; otherwise it returns -Integer.MAX_VALUE.

// Fix a band of rows, start..end, and squash it: colSum[c] is column c's
// sum over those rows, so every rectangle inside the band is a run of
// neighbouring colSum entries. A run's sum is the difference of two
// running prefix sums, sum - earlier, and it is <= k exactly when
// earlier >= sum - k. The best run ending here wants the smallest such
// earlier prefix -- which is what TreeSet.ceiling(sum - k) returns over
// the prefix sums seen so far in this band. The 0 put in first is the
// empty prefix, for runs that start at column 0.

// Time: O(n^2 * m log m) -- n^2 / 2 bands, m prefix sums in each, and a
//   TreeSet search and insert per prefix sum
// Space: O(m) -- the column sums and the set

// Siblings:
//   MyCalendar (729) -- the same TreeMap neighbour lookup, floorEntry and
//     ceilingEntry, used to find the one or two events a booking can clash with.
//   dp.sum.max.subarr.MaxSumSubArrayKadane -- the one-row problem with no
//     limit k, where no search is needed.

public final class MaxSumRectangleNoLargerK {

	public static int search(int[][] matrix, int target) {
		final int infinity = Integer.MAX_VALUE;
		int max = -infinity;
		int n = matrix.length;
		int m = matrix[0].length;
		for (int start = 0; start < n; start++) { // BAND: its top row
			int[] colSum = new int[m];
			for (int end = start; end < n; end++) { // BAND: its bottom row
				for (int col = 0; col < m; col++) {
					colSum[col] += matrix[end][col]; // SQUASH: add row end into the band's column sums
				}
				int sum = 0;
				TreeSet<Integer> set = new TreeSet<>();
				set.add(0); // the empty prefix
				for (int s : colSum) {
					sum += s; // PREFIX: the band's columns 0..here
					Integer min = set.ceiling(sum - target); // SEARCH: the smallest earlier prefix >= sum - k
					if (min != null) {
						max = Math.max(max, sum - min); // KEEP: the best run ending here that is still <= k
					}
					set.add(sum); // REMEMBER this prefix
				}
			}
		}
		return max; // REPORT
	}

	public static void main(String[] args) {
		int[][] matrix = { //
				{ 1, 0, 1 }, //
				{ 0, -2, 3 } };//
		System.out.println(search(matrix, 2)); // 2
		int[][] matrix1 = { //
				{ 2, 2, -1 }, //
				{ 0, -2, 3 } };//
		System.out.println(search(matrix1, 3)); // 3
		int[][] matrix2 = { //
				{ 1, 0, 1 }, //
				{ 0, -2, 3 }, //
				{ 1, 2, -1 } };//
		System.out.println(search(matrix2, 8)); // 5
		int[][] matrix3 = { //
				{ 2, 2, -1 } };//
		System.out.println(search(matrix3, 0)); // -1
		int[][] matrix4 = { //
				{ 5, -4, -3, 4 }, //
				{ -3, -4, 4, 5 }, //
				{ 5, 1, 5, -4 } };//
		System.out.println(search(matrix4, 10)); // 10
	}
}
