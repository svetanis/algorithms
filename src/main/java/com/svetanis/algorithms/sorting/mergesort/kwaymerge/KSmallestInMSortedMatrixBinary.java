package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.svetanis.algorithms.sorting.mergesort.interval.Interval;
import com.svetanis.java.base.Pair;

// given an n x n matrix where each row
// and col is sorted in ascending order
// find the k-th smallest element in the matrix

public final class KSmallestInMSortedMatrixBinary {
	// Time Complexity: O(n * log(max - min))
	// Space Complexity: O(1)

	public static int kSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		int start = matrix[0][0];
		int end = matrix[n - 1][n - 1];
		while (start < end) {
			int mid = start + (end - start) / 2;
			Pair<Interval, Integer> pair = count(matrix, mid);
			Interval interval = pair.getLeft();
			int count = pair.getRight();
			if (count == k) {
				return interval.start;
			}
			if (count < k) {
				start = interval.end;
			} else {
				end = interval.start;
			}
		}
		return start;
	}

	private static Pair<Interval, Integer> count(int[][] matrix, int mid) {
		int n = matrix.length;
		int count = 0;
		int row = n - 1;
		int col = 0;
		int left = matrix[0][0];
		int right = matrix[n - 1][n - 1];
		while (row >= 0 && col < n) {
			if (matrix[row][col] > mid) {
				// smallest number greater than mid
				right = min(right, matrix[row][col]);
				row--;
			} else {
				// largest number less than mid
				left = max(left, matrix[row][col]);
				col++;
				count += row + 1;
			}
		}
		return Pair.build(new Interval(left, right), count);
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 4 }, { 2, 5 } };
		System.out.println(kSmallest(m1, 2));

		int[][] m2 = { { -5 } };
		System.out.println(kSmallest(m2, 1));

		int[][] m3 = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
		System.out.println(kSmallest(m3, 5));

		int[][] m4 = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		System.out.println(kSmallest(m4, 8));
	}
}