package com.svetanis.algorithms.search.binary.matrix;

// 240. Search a 2D Matrix II

// Let A be an n x n 2D array where rows and columns 
// are sorted in increasing sorted order. 
// Design an efficient algorithm that decides 
// whether a number x appears in A.

public final class SortedMatrixSaddlebackSearchSubmit {
	// Time Complexity: O(n + m)

	public static boolean search(int[][] matrix, int target) {
		int n = matrix.length;
		int m = matrix[0].length;
		int row = 0;
		int col = m - 1;
		if (target < matrix[0][0] || target > matrix[n - 1][m - 1]) {
			return false;
		}
		while (row < n && col >= 0) {
			if (matrix[row][col] == target) {
				return true;
			}
			if (matrix[row][col] > target) {
				--col;
			} else {
				++row;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix1 = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };
		System.out.println(search(matrix1, 29));

		int[][] matrix2 = { { 2, 2, 3, 5 }, { 3, 4, 5, 6 }, { 3, 5, 6, 8 }, { 3, 6, 7, 9 } };
		System.out.println(search(matrix2, 6));

		int[][] matrix3 = { { 2, 2, 3, 5 }, { 3, 4, 6, 6 }, { 3, 5, 6, 6 }, { 3, 6, 6, 9 } };
		System.out.println(search(matrix3, 7));

		int[][] matrix4 = { { 6, 6, 6, 6 }, { 6, 6, 6, 6 }, { 6, 6, 6, 6 }, { 6, 6, 6, 6 } };
		System.out.println(search(matrix4, 6));
	}
}
