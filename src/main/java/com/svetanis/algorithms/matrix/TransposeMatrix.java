package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 867. Transpose Matrix
//
// Input: a matrix of rows x cols integers. Returns its transpose: the matrix flipped over its main
// diagonal, so row r of the input becomes column r of the answer. On LeetCode:
// 1 <= rows, cols <= 1000, 1 <= rows * cols <= 10^5, -10^9 <= matrix[i][j] <= 10^9.
//
// The one idea: every value swaps its two indexes: m[r][c] lands at out[c][r]. The shape swaps
// too, so a rows x cols input gives a cols x rows answer. Two equivalent loops -- which grid you
// walk is a free choice:
//   push: walk the INPUT and send each value to where it goes
//   pull: walk the ANSWER and fetch each value from where it comes from
//
// Time: O(rows * cols) -- one copy per value, in both methods.
// Space: O(rows * cols) -- the answer.
//
// Two methods, one problem: transposePush and transposePull differ only in which grid the loops
// walk, and so in which indexes row and col name.

public final class TransposeMatrix {

	public static int[][] transposePush(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] transposed = new int[cols][rows]; // SHAPE: [cols][rows], not [rows][cols]
		for (int row = 0; row < rows; row++) { // WALK: row and col index the INPUT
			for (int col = 0; col < cols; col++) {
				transposed[col][row] = matrix[row][col]; // PUSH: where does this value go?
			}
		}
		return transposed;
	}

	public static int[][] transposePull(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] transposed = new int[cols][rows]; // SHAPE: [cols][rows]
		for (int row = 0; row < cols; row++) { // WALK: row and col index the ANSWER,
			for (int col = 0; col < rows; col++) { // which has cols rows and rows columns
				transposed[row][col] = matrix[col][row]; // PULL: where does this value come from?
			}
		}
		return transposed;
	}

	public static void main(String[] args) {
		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(Arrays.deepToString(transposePush(matrix1))); // [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
		System.out.println(Arrays.deepToString(transposePull(matrix1))); // [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
		int[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6 } }; // a square hides a wrong shape
		System.out.println(Arrays.deepToString(transposePush(matrix2))); // [[1, 4], [2, 5], [3, 6]]
		System.out.println(Arrays.deepToString(transposePull(matrix2))); // [[1, 4], [2, 5], [3, 6]]
	}
}
