package com.svetanis.algorithms.matrix.rotate;

import com.svetanis.java.base.utils.Print;

// 48. Rotate Image, turned the other way: 90 degrees counter-clockwise, in place

// The same two moves as clockwise, with the second move swapped:
//   rotate:            reverse each row, then transpose (upper half)
//   rotateByTranspose: transpose (upper half), then flip the rows upside down
// Which pair to use is a free choice. The order inside a pair is not:
// do either pair the other way round and the grid turns clockwise.
// Forgot the order? Transpose the example and compare it with the expected output.

public final class Rotate90InPlaceMirrorCounterClockwise {
	// Time Complexity: O(n^2)
	// Space Complexity: O(1), in place

	public static void rotate(int[][] matrix) {
		reverseEachRow(matrix);
		transposeUpperHalf(matrix);
	}

	public static void rotateByTranspose(int[][] matrix) {
		transposeUpperHalf(matrix);
		flipUpsideDown(matrix);
	}

	// transpose: m[row][col] trades places with m[col][row]
	private static void transposeUpperHalf(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n; row++) {
			for (int col = row + 1; col < n; col++) { // upper half: each pair swapped once
				int temp = matrix[row][col];
				matrix[row][col] = matrix[col][row];
				matrix[col][row] = temp;
			}
		}
	}

	// mirrors the grid left-right: col trades places with n - 1 - col
	private static void reverseEachRow(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n / 2; col++) { // left half only; an odd middle stays put
				int temp = matrix[row][col];
				matrix[row][col] = matrix[row][n - 1 - col]; // last index is n - 1, not n
				matrix[row][n - 1 - col] = temp;
			}
		}
	}

	// flips the grid upside down: row trades places with n - 1 - row
	private static void flipUpsideDown(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n / 2; row++) { // top half only, or every row swaps back
			int[] temp = matrix[row]; // a row is one array: swap the references
			matrix[row] = matrix[n - 1 - row];
			matrix[n - 1 - row] = temp;
		}
	}

	public static void main(String[] args) {
		// expected [[3,6,9],[2,5,8],[1,4,7]]
		int m1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate(m1);
		Print.print(m1);
		int t1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotateByTranspose(t1);
		Print.print(t1);

		// expected [[11,10,7,16],[9,8,6,12],[1,4,3,14],[5,2,13,15]]
		int m2[][] = { { 5, 1, 9, 11 }, //
				{ 2, 4, 8, 10 }, //
				{ 13, 3, 6, 7 }, //
				{ 15, 14, 12, 16 } };//
		rotate(m2);
		Print.print(m2);
		int t2[][] = { { 5, 1, 9, 11 }, //
				{ 2, 4, 8, 10 }, //
				{ 13, 3, 6, 7 }, //
				{ 15, 14, 12, 16 } };//
		rotateByTranspose(t2);
		Print.print(t2);
	}
}