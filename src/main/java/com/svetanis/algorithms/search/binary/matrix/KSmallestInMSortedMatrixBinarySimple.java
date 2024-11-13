package com.svetanis.algorithms.search.binary.matrix;

//378. Kth Smallest Element in a Sorted Matrix

// given an n x n matrix where each row
// and col is sorted in ascending order
// find the k-th smallest element in the matrix

public final class KSmallestInMSortedMatrixBinarySimple {
	// Time Complexity: O(n * log(max - min))
	// Space Complexity: O(1)

	public static int kSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		int low = matrix[0][0];
		int high = matrix[n - 1][n - 1];
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (countLessOrEqual(matrix, mid, k)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static boolean countLessOrEqual(int[][] matrix, int mid, int k) {
		int n = matrix.length;
		int count = 0;
		int row = n - 1;
		int col = 0;
		while (row >= 0 && col < n) {
			if (matrix[row][col] > mid) {
				row--;
			} else {
				col++;
				count += row + 1;
			}
		}
		return count >= k;
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
}