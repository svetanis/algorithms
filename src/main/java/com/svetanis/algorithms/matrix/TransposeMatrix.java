package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 867. Transpose Matrix

// Every value swaps its two indexes: m[r][c] lands at out[c][r].
// The shape swaps too, so a rows x cols input gives a cols x rows answer.
// Two equivalent loops - which grid you walk is a free choice:
//   push: walk the INPUT and send each value to where it goes
//   pull: walk the ANSWER and fetch each value from where it comes from

public final class TransposeMatrix {
	// Time Complexity: O(rows * cols)
	// Space Complexity: O(rows * cols) for the answer

	public static int[][] transposePush(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] transposed = new int[cols][rows]; // [cols][rows], not [rows][cols]
		for (int row = 0; row < rows; row++) { // row and col index the INPUT
			for (int col = 0; col < cols; col++) {
				transposed[col][row] = matrix[row][col]; // where does this value go?
			}
		}
		return transposed;
	}

	public static int[][] transposePull(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] transposed = new int[cols][rows];
		for (int row = 0; row < cols; row++) { // row and col index the ANSWER,
			for (int col = 0; col < rows; col++) { // which has cols rows and rows columns
				transposed[row][col] = matrix[col][row]; // where does this value come from?
			}
		}
		return transposed;
	}

	public static void main(String[] args) {
		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Print.print(transposePush(matrix1)); // [[1,4,7],[2,5,8],[3,6,9]]
		Print.print(transposePull(matrix1)); // [[1,4,7],[2,5,8],[3,6,9]]
		int[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6 } }; // a square hides a wrong shape
		Print.print(transposePush(matrix2)); // [[1,4],[2,5],[3,6]]
		Print.print(transposePull(matrix2)); // [[1,4],[2,5],[3,6]]
	}
}
