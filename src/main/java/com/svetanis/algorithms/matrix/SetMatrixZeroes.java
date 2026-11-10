package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 73. Set Matrix Zeroes
//
// Input: an n x m matrix of ints. Wherever a cell holds 0, set its whole row and its whole column
// to 0, in place; returns nothing. On LeetCode: 1 <= m, n <= 200,
// -2^31 <= matrix[i][j] <= 2^31 - 1.
//
// The one idea: one cell holds either its own value or, once a zero in its row or column has been
// found, the mark Integer.MAX_VALUE, meaning "this becomes 0 later". A mark is needed because a
// cell set to 0 straight away would look like an original zero and spread its own row and column.
// transform() paints a zero's row and column with the mark but leaves every 0 as 0, so the scan
// still finds each original zero exactly once; doZeros() then turns every mark into 0.
//
// Time: O(n * m + z * (n + m)), z = the number of zeros -- transform() runs once per zero and
//       repaints a whole row and column. An all-zero n x n matrix costs n^2 calls and 2n^3 writes.
// Space: O(1) -- the marks live in the matrix.
//
// Siblings: SetMatrixZeroes73 keeps the marks in row 0 and column 0, O(1) space;
// SetMatrixZeroesBooleanArrays keeps them in two boolean arrays, O(n + m). Both are correct for
// every int, because neither marks with a value.

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

	public static void setZeros(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int i = 0; i < n; i++) { // SCAN: every cell once
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0) { // ZERO: an original one -- transform() never writes a new 0
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
				if (grid[i][j] == Integer.MAX_VALUE) { // MARK -> 0: a real MAX_VALUE is caught here too
					grid[i][j] = 0;
				}
			}
		}
	}

	private static void transform(int[][] grid, int row, int col) {
		int n = grid.length;
		int m = grid[0].length;
		for (int c = 0; c < m; c++) { // PAINT: the zero's row
			boolean isZero = grid[row][c] == 0;
			grid[row][c] = isZero ? 0 : Integer.MAX_VALUE; // KEEP a 0 as 0, so the scan still finds it
		}
		for (int r = 0; r < n; r++) { // PAINT: the zero's column
			boolean isZero = grid[r][col] == 0;
			grid[r][col] = isZero ? 0 : Integer.MAX_VALUE;
		}
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		setZeros(grid1);
		System.out.println(Arrays.deepToString(grid1)); // [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
		int[][] grid2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		setZeros(grid2);
		System.out.println(Arrays.deepToString(grid2)); // [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
		// the input the header warns about: a real MAX_VALUE is taken for a mark
		int[][] grid3 = { { Integer.MAX_VALUE, 1 }, { 1, 0 } };
		setZeros(grid3);
		System.out.println(Arrays.deepToString(grid3)); // [[0, 0], [0, 0]] -- right is [[2147483647, 0], [0, 0]]
	}
}