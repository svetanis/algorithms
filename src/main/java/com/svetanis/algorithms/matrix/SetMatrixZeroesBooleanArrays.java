package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 73. Set Matrix Zeroes
//
// Input: an n x m matrix of ints. Wherever a cell holds 0, set its whole row and its whole column
// to 0, in place; returns nothing. On LeetCode: 1 <= m, n <= 200,
// -2^31 <= matrix[i][j] <= 2^31 - 1.
//
// The one idea: one entry of rows[] is a yes/no for one row -- "this row holds a zero, clear it" --
// and one entry of cols[] the same for one column. A first pass only reads the matrix and sets the
// flags; a second pass clears every cell whose row or column is flagged. Reading everything before
// writing anything is what keeps a new 0 from being mistaken for an original one. No value in the
// matrix is used as a mark, so this works for every int, with no range to check.
//
// Time: O(n * m) -- two passes over the cells.
// Space: O(n + m) -- one flag per row and per column.
//
// Siblings: SetMatrixZeroes73 moves these flags into row 0 and column 0, O(1) space -- an easy step
// to explain once these two arrays are on the board, which is why this is the version to offer
// first. SetMatrixZeroes marks cells with Integer.MAX_VALUE instead, so it is correct only where
// that value cannot appear.

public final class SetMatrixZeroesBooleanArrays {

	public static void setZeros(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		boolean[] rows = new boolean[n]; // FLAGS: rows[r] -- row r holds a zero
		boolean[] cols = new boolean[m]; // FLAGS: cols[c] -- column c holds a zero
		mark(grid, rows, cols);
		apply(grid, rows, cols);
	}

	private static void mark(int[][] grid, boolean[] rows, boolean[] cols) {
		for (int r = 0; r < rows.length; r++) { // READ: every cell, no writes to grid
			for (int c = 0; c < cols.length; c++) {
				if (grid[r][c] == 0) {
					rows[r] = true;
					cols[c] = true;
				}
			}
		}
	}

	private static void apply(int[][] grid, boolean[] rows, boolean[] cols) {
		for (int r = 0; r < rows.length; r++) { // CLEAR: after every flag is set
			for (int c = 0; c < cols.length; c++) {
				if (rows[r] || cols[c]) {
					grid[r][c] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		setZeros(grid1);
		System.out.println(Arrays.deepToString(grid1)); // [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
		int[][] grid2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		setZeros(grid2);
		System.out.println(Arrays.deepToString(grid2)); // [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
		int[][] grid3 = { { Integer.MAX_VALUE, 1 }, { 1, 0 } };
		setZeros(grid3);
		System.out.println(Arrays.deepToString(grid3)); // [[2147483647, 0], [0, 0]] -- SetMatrixZeroes gives all zeros here
	}
}
