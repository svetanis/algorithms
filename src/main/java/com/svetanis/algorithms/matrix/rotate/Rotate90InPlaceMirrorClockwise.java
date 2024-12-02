package com.svetanis.algorithms.matrix.rotate;

import com.svetanis.java.base.utils.Print;

// 48. Rotate Image

public final class Rotate90InPlaceMirrorClockwise {
	// Time Complexity: O(n^2)

	public static void rotate(int[][] matrix) {
		mirrorVertical(matrix);
		mirrorDiagonal(matrix);
	}

	private static void mirrorVertical(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n / 2; ++row) {
			for (int col = 0; col < n; ++col) {
				int temp = matrix[row][col];
				matrix[row][col] = matrix[n - row - 1][col];
				matrix[n - row - 1][col] = temp;
			}
		}

	}

	private static void mirrorDiagonal(int[][] matrix) {
		int n = matrix.length;
		// mirror the matrix along the diagonal line
		for (int row = 0; row < n; ++row) {
			for (int col = 0; col < row; ++col) {
				int temp = matrix[row][col];
				matrix[row][col] = matrix[col][row];
				matrix[col][row] = temp;
			}
		}
	}

	public static void main(String[] args) {
		// Rotate clockwise
		int m1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate(m1);
		Print.print(m1);

		int m2[][] = { { 5, 1, 9, 11 }, //
				{ 2, 4, 8, 10 }, //
				{ 13, 3, 6, 7 }, //
				{ 15, 14, 12, 16 } };//
		rotate(m2);
		Print.print(m2);

	}
}