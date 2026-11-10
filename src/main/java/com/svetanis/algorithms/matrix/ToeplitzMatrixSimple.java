package com.svetanis.algorithms.matrix;

// 766. Toeplitz Matrix
//
// Input: a matrix of n rows and m columns. Returns true if every diagonal running from top-left to
// bottom-right holds one value throughout. On LeetCode: 1 <= n, m <= 20, 0 <= matrix[i][j] <= 99.
//
// The one idea: cell (i, j) lies on the same diagonal as (i - 1, j - 1), the cell one up and one to
// the left. If every cell that has such a neighbour equals it, the equalities chain down each
// diagonal from its first cell to its last, so the whole diagonal holds one value; and one
// unequal pair is enough to say no.
//
// Time: O(n * m) -- each cell is compared once.
// Space: O(1) -- and only the row above is ever read, so the rows could arrive one at a time.
//
// Siblings: ToeplitzMatrix walks each diagonal from its start on row 0 or column 0 and compares
// every cell with the first.

public final class ToeplitzMatrixSimple {

	public static boolean isToeplitz(int[][] matrix) {

		int n = matrix.length;
		int m = matrix[0].length;
		for (int i = 1; i < n; i++) { // WALK: every cell with a neighbour up and to the left
			for (int j = 1; j < m; j++) {
				if (matrix[i][j] != matrix[i - 1][j - 1]) { // COMPARE: same diagonal, one step back
					return false;
				}
			}
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
