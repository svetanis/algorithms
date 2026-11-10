package com.svetanis.algorithms.matrix;

// 766. Toeplitz Matrix
//
// Input: a matrix of n rows and m columns. Returns true if every diagonal running from top-left to
// bottom-right holds one value throughout. On LeetCode: 1 <= n, m <= 20, 0 <= matrix[i][j] <= 99.
//
// The one idea: one diagonal is the set of cells whose row minus column is the same number. Each
// diagonal starts on row 0 or on column 0, so there are m + n - 1 of them. Walk each one from its
// start, stepping one row down and one column right, and compare every cell with the first.
//
// Time: O(n * m) -- every cell is on exactly one diagonal and is read once.
// Space: O(1) -- the walk keeps one value and two indexes.
//
// Siblings: ToeplitzMatrixSimple compares each cell with the one up and to its left, row by row,
// with no diagonal walk.

public final class ToeplitzMatrix {

	public static boolean isToeplitz(int[][] matrix) {

		int n = matrix.length;
		int m = matrix[0].length;
		for (int i = 0; i < m; i++) { // START: each diagonal beginning on row 0
			if (!isDiagonal(matrix, 0, i)) {
				return false;
			}
		}
		for (int i = 1; i < n; i++) { // START: each diagonal beginning on column 0, below (0, 0)
			if (!isDiagonal(matrix, i, 0)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isDiagonal(int[][] matrix, int i, int j) {
		int n = matrix.length;
		int m = matrix[0].length;
		int first = matrix[i][j]; // FIRST: the value the whole diagonal must hold
		while (i < n && j < m) { // WALK: down and right until an edge
			if (matrix[i][j] != first) {
				return false;
			}
			i++;
			j++;
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] matrix = { //
				{ 6, 7, 8, 9 }, //
				{ 4, 6, 7, 8 }, //
				{ 1, 4, 6, 7 }, //
				{ 0, 1, 4, 6 }, //
				{ 2, 0, 1, 4 }//
		};//
		System.out.println(isToeplitz(matrix)); // true
		int[][] matrix1 = { { 1, 2, 3, 4 }, { 5, 1, 2, 3 }, { 9, 5, 1, 2 } };
		System.out.println(isToeplitz(matrix1)); // true
		int[][] matrix2 = { { 1, 2 }, { 2, 2 } };
		System.out.println(isToeplitz(matrix2)); // false
	}
}
