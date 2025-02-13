package com.svetanis.algorithms.search.binary.matrix;

import static java.lang.Math.max;
import static java.lang.Math.min;

//378. Kth Smallest Element in a Sorted Matrix

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
			Result result = count(matrix, mid);
			int count = result.count;
			if (count == k) {
				return result.start;
			}
			if (count < k) {
				start = result.end;
			} else {
				end = result.start;
			}
		}
		return start;
	}

	private static Result count(int[][] matrix, int mid) {
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
		return new Result(left, right, count);
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 4 }, { 2, 5 } };
		System.out.println(kSmallest(m1, 2));

		int[][] m2 = { { -5 } };
		System.out.println(kSmallest(m2, 1)); // -5

		int[][] m3 = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
		System.out.println(kSmallest(m3, 5));

		int[][] m4 = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		System.out.println(kSmallest(m4, 8)); // 13
	}

	private static final class Result {
		private int start;
		private int end;
		private int count;

		public Result(int start, int end, int count) {
			this.start = start;
			this.end = end;
			this.count = count;
		}
	}
}