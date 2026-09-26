package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 73. Set Matrix Zeroes

// THE O(n + m) TIER, AND THE ONE TO OFFER FIRST.
// two boolean arrays remember which rows and columns must be
// cleared. no marker value is involved, so nothing in the
// matrix can be mistaken for bookkeeping -- which means this
// works for ANY value range, with no precondition to check.
// SetMatrixZeroes.java marks inside the matrix instead and
// is therefore correct only where its marker cannot occur.

// say this, then improve it: the flags are the only extra
// state, and row 0 / column 0 can hold them instead. that is
// SetMatrixZeroes73.java, O(1) space, and it is a much
// easier step to explain once these arrays are on the board.

public final class SetMatrixZeroesBooleanArrays {
	// Time Complexity: O(n * m) -- two passes
	// Space Complexity: O(n + m)

	public static void setZeros(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		boolean[] rows = new boolean[n];
		boolean[] cols = new boolean[m];
		mark(grid, rows, cols);
		apply(grid, rows, cols);
	}

	private static void mark(int[][] grid, boolean[] rows, boolean[] cols) {
		for (int r = 0; r < rows.length; r++) {
			for (int c = 0; c < cols.length; c++) {
				if (grid[r][c] == 0) {
					rows[r] = true;
					cols[c] = true;
				}
			}
		}
	}

	private static void apply(int[][] grid, boolean[] rows, boolean[] cols) {
		for (int r = 0; r < rows.length; r++) {
			for (int c = 0; c < cols.length; c++) {
				if (rows[r] || cols[c]) {
					grid[r][c] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		setZeros(grid1); // [1,0,1],[0,0,0],[1,0,1]
		Print.print(grid1);
		int[][] grid2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		setZeros(grid2); // [0,0,0,0],[0,4,5,0],[0,3,1,0]
		Print.print(grid2);
		int[][] grid3 = { { Integer.MAX_VALUE, 1 }, { 1, 0 } };
		setZeros(grid3); // [2147483647,0],[0,0] -- the case SetMatrixZeroes fails
		Print.print(grid3);
	}
}
