package com.svetanis.algorithms.matrix.rotate;

import java.util.Arrays;

// 48. Rotate Image, turned the other way: 90 degrees counter-clockwise, in place
//
// Input: matrix -- an n x n grid of numbers. Turns it a quarter-turn counter-clockwise in place:
// the caller's grid is changed and nothing is returned. LeetCode's 48 asks for clockwise; its
// limits are 1 <= n <= 20, -1000 <= matrix[i][j] <= 1000.
//
// The one idea: one cell holds one number, and a quarter-turn counter-clockwise sends the number
// at (row r, column c) to (row n - 1 - c, column r). The same two moves as clockwise do it, with
// the second move of each pair changed:
//   rotate:            reverse each row, then transpose (upper half)
//   rotateByTranspose: transpose (upper half), then flip the rows upside down
// Check the first pair on one cell: reversing the row sends (r, c) to (r, n - 1 - c), and the
// transpose then sends that to (n - 1 - c, r), which is the quarter-turn.
// Which pair to use is a free choice. The order inside a pair is not:
// do either pair the other way round and the grid turns clockwise.
// Forgot the order? Transpose the example and compare it with the expected output.
//
// Time: O(n^2) -- each move touches every cell once at most.
// Space: O(1) -- in place; one temporary number, or one row reference, per swap.
//
// Siblings: Rotate90InPlaceMirrorClockwise turns the grid clockwise with the same moves. Its
// flipUpsideDown swaps the rows cell by cell; the one here swaps the row arrays whole.

public final class Rotate90InPlaceMirrorCounterClockwise {

	public static void rotate(int[][] matrix) {
		reverseEachRow(matrix); // REVERSE: (r, c) goes to (r, n - 1 - c)
		transposeUpperHalf(matrix); // TRANSPOSE: then to (n - 1 - c, r)
	}

	public static void rotateByTranspose(int[][] matrix) {
		transposeUpperHalf(matrix); // TRANSPOSE: (r, c) goes to (c, r)
		flipUpsideDown(matrix); // FLIP: then to (n - 1 - c, r)
	}

	// transpose: m[row][col] trades places with m[col][row]
	private static void transposeUpperHalf(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n; row++) {
			for (int col = row + 1; col < n; col++) { // WALK: upper half: each pair swapped once
				int temp = matrix[row][col]; // SWAP: across the diagonal
				matrix[row][col] = matrix[col][row];
				matrix[col][row] = temp;
			}
		}
	}

	// mirrors the grid left-right: col trades places with n - 1 - col
	private static void reverseEachRow(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n / 2; col++) { // WALK: left half only; an odd middle stays put
				int temp = matrix[row][col]; // SWAP: with the mirrored column
				matrix[row][col] = matrix[row][n - 1 - col]; // last index is n - 1, not n
				matrix[row][n - 1 - col] = temp;
			}
		}
	}

	// flips the grid upside down: row trades places with n - 1 - row
	private static void flipUpsideDown(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n / 2; row++) { // WALK: top half only, or every row swaps back
			int[] temp = matrix[row]; // SWAP: a row is one array: swap the references
			matrix[row] = matrix[n - 1 - row];
			matrix[n - 1 - row] = temp;
		}
	}

	public static void main(String[] args) {
		int m1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate(m1);
		System.out.println(Arrays.deepToString(m1)); // [[3, 6, 9], [2, 5, 8], [1, 4, 7]]
		int t1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotateByTranspose(t1);
		System.out.println(Arrays.deepToString(t1)); // [[3, 6, 9], [2, 5, 8], [1, 4, 7]]

		int m2[][] = { { 5, 1, 9, 11 }, //
				{ 2, 4, 8, 10 }, //
				{ 13, 3, 6, 7 }, //
				{ 15, 14, 12, 16 } };//
		rotate(m2);
		System.out.println(Arrays.deepToString(m2)); // [[11, 10, 7, 16], [9, 8, 6, 12], [1, 4, 3, 14], [5, 2, 13, 15]]
		int t2[][] = { { 5, 1, 9, 11 }, //
				{ 2, 4, 8, 10 }, //
				{ 13, 3, 6, 7 }, //
				{ 15, 14, 12, 16 } };//
		rotateByTranspose(t2);
		System.out.println(Arrays.deepToString(t2)); // [[11, 10, 7, 16], [9, 8, 6, 12], [1, 4, 3, 14], [5, 2, 13, 15]]
	}
}