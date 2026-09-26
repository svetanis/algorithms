package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 73. Set Matrix Zeroes

// CORRECT ONLY WHERE Integer.MAX_VALUE CANNOT APPEAR IN THE
// INPUT. the algorithm is sound -- transform() paints a
// zero's row and column with MAX_VALUE meaning "this becomes
// 0 later", and doZeros() rewrites every MAX_VALUE to 0.
// that works for as long as no cell legitimately holds it.

// LC 73's range is -2^31 .. 2^31 - 1, so there it CAN, and
// doZeros() then cannot tell a mark from real data:
// [[MAX, 1], [1, 0]] -> [[0, 0], [0, 0]], where the correct
// answer is [[MAX, 0], [0, 0]] -- (0, 0) has no zero in its
// row or column and must survive.
// random matrices of ordinary values never expose this --
// the bug appears only once MAX_VALUE is in the pool. same
// code, same behaviour; only the input domain changed.

// for LC 73 use SetMatrixZeroes73.java (O(1) space) or
// SetMatrixZeroesBooleanArrays.java (O(n + m)). no other
// constant would rescue this one: every int is a legal cell
// value there, so NO in-band marker exists for that problem
// at all. the mark has to leave the band -- into two boolean
// arrays, or into row 0 and column 0, whose real contents
// are recovered from two flags.

// KEPT ON PURPOSE, and the reason is the lesson: a marker
// value is a CONTRACT WITH THE INPUT RANGE, and it does not
// travel. this code was correct for whatever domain it was
// written against and stopped being correct when the domain
// widened. that will happen to your own code; the defence is
// to state the range the marker depends on, in the file.

public final class SetMatrixZeroes {
	// Time Complexity: O(n * m) claimed, but transform() is called
	// once per zero and PRESERVES zeros, so the outer scan keeps
	// finding them: calls grow as n^2 and cell writes as 2n^3.
	// Space Complexity: O(1)

	public static void setZeros(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0) {
					transform(grid, i, j);
				}
			}
		}
		doZeros(grid);
	}

	private static void doZeros(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == Integer.MAX_VALUE) {
					grid[i][j] = 0;
				}
			}
		}
	}

	private static void transform(int[][] grid, int row, int col) {
		int n = grid.length;
		int m = grid[0].length;
		for (int c = 0; c < m; c++) {
			boolean isZero = grid[row][c] == 0;
			grid[row][c] = isZero ? 0 : Integer.MAX_VALUE;
		}
		for (int r = 0; r < n; r++) {
			boolean isZero = grid[r][col] == 0;
			grid[r][col] = isZero ? 0 : Integer.MAX_VALUE;
		}
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		setZeros(grid1); // [1,0,1],[0,0,0][1,0,1]
		Print.print(grid1);
		int[][] grid2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		setZeros(grid2); // [0,0,0,0],[0,4,5,0],[0,3,1,0]
		Print.print(grid2);
	}
}