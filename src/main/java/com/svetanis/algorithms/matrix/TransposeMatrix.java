package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 867. Transpose Matrix

public final class TransposeMatrix {
	// Time Complexity: O(n^2)

	public static int[][] transpose(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] transposed = new int[cols][rows];
		for (int row = 0; row < cols; row++) {
			for (int col = 0; col < rows; col++) {
				transposed[row][col] = matrix[col][row];
			}
		}
		return transposed;
	}

	public static void main(String[] args) {
		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Print.print(transpose(matrix1)); // [[1,4,7],[2,5,8],[3,6,9]]
		int[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		Print.print(transpose(matrix2)); // [[1,4],[2,5],[3,6]]
	}
}
