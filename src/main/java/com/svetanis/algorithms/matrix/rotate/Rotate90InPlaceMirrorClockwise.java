package com.svetanis.algorithms.matrix.rotate;

import java.util.Arrays;

// 48. Rotate Image
//
// Input: matrix -- an n x n grid of numbers. Turns it a quarter-turn clockwise in place: the
// caller's grid is changed and nothing is returned. On LeetCode: 1 <= n <= 20,
// -1000 <= matrix[i][j] <= 1000.
//
// The one idea: one cell holds one number, and a quarter-turn clockwise sends the number at
// (row r, column c) to (row c, column n - 1 - r). That move is two simpler moves, each made of
// swaps of two cells, so no second grid is needed -- and either pair works:
//   rotate:            flip the rows upside down, then transpose (lower half)
//   rotateByTranspose: transpose (upper half), then reverse each row
// Check the first pair on one cell: the flip sends (r, c) to (n - 1 - r, c), and the transpose
// then sends that to (c, n - 1 - r), which is the quarter-turn.
// Which pair to use is a free choice. The one thing NOT free is the order inside a pair: swap
// the two moves and the grid turns counter-clockwise.
// Forgot the order? Transpose Example 1 and compare it with the expected output.
//
// Time: O(n^2) -- each move swaps the cells of half the grid, each pair once.
// Space: O(1) -- in place; one temporary number per swap.
//
// Siblings: Rotate90InPlaceMirrorCounterClockwise turns the grid the other way with the same
// moves, the second move of each pair changed. Its flipUpsideDown swaps the row arrays whole; the
// one here swaps the rows cell by cell.

public final class Rotate90InPlaceMirrorClockwise {

	public static void rotate(int[][] matrix) {
		flipUpsideDown(matrix); // FLIP: (r, c) goes to (n - 1 - r, c)
		mirrorDiagonal(matrix); // TRANSPOSE: then to (c, n - 1 - r)
	}

	public static void rotateByTranspose(int[][] matrix) {
		transposeUpperHalf(matrix); // TRANSPOSE: (r, c) goes to (c, r)
		reverseEachRow(matrix); // REVERSE: then to (c, n - 1 - r)
	}

	// flips the grid upside down: row r trades places with row n - 1 - r
	private static void flipUpsideDown(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n / 2; ++row) { // WALK: top half only, or every row swaps back
			for (int col = 0; col < n; ++col) {
				int temp = matrix[row][col]; // SWAP: with the same column, mirrored row
				matrix[row][col] = matrix[n - row - 1][col];
				matrix[n - row - 1][col] = temp;
			}
		}
	}

	// transpose: m[row][col] trades places with m[col][row]
	private static void mirrorDiagonal(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n; ++row) {
			for (int col = 0; col < row; ++col) { // WALK: lower half: each pair swapped once
				int temp = matrix[row][col]; // SWAP: across the diagonal
				matrix[row][col] = matrix[col][row];
				matrix[col][row] = temp;
			}
		}
	}

	// transpose again, walking the other half - either half is correct
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

	public static void main(String[] args) {
		int m1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate(m1);
		System.out.println(Arrays.deepToString(m1)); // [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
		int t1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotateByTranspose(t1);
		System.out.println(Arrays.deepToString(t1)); // [[7, 4, 1], [8, 5, 2], [9, 6, 3]]

		int m2[][] = { { 5, 1, 9, 11 }, //
				{ 2, 4, 8, 10 }, //
				{ 13, 3, 6, 7 }, //
				{ 15, 14, 12, 16 } };//
		rotate(m2);
		System.out.println(Arrays.deepToString(m2)); // [[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]
		int t2[][] = { { 5, 1, 9, 11 }, //
				{ 2, 4, 8, 10 }, //
				{ 13, 3, 6, 7 }, //
				{ 15, 14, 12, 16 } };//
		rotateByTranspose(t2);
		System.out.println(Arrays.deepToString(t2)); // [[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]
	}
}