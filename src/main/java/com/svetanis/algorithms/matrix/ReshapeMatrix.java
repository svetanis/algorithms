package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 566. Reshape the Matrix
//
// Input: grid -- a matrix of n rows and m columns; r, c -- the shape wanted. Returns an r x c
// matrix holding grid's values in the same row-by-row reading order, or grid itself when r * c is
// not the number of cells. On LeetCode: 1 <= n, m <= 100, -1000 <= grid[i][j] <= 1000,
// 1 <= r, c <= 300.
//
// The one idea: read both matrices row by row and number the cells 0, 1, 2, ... in that order.
// One number i names one value, the same value in both matrices. In grid, which has m columns,
// cell i sits at row i / m, column i % m; in the answer, which has c columns, at row i / c,
// column i % c. One loop over i copies each value from one place to the other.
//
// Time: O(n * m) -- one copy per cell.
// Space: O(r * c) -- the answer; nothing is allocated when the shapes do not match.

public final class ReshapeMatrix {

	public static int[][] reshape(int[][] grid, int r, int c) {
		int n = grid.length;
		int m = grid[0].length;
		if (n * m != r * c) { // SKIP: different cell counts -- the original comes back
			return grid;
		}
		int[][] matrix = new int[r][c];
		for (int i = 0; i < n * m; i++) { // WALK: cell number i, row-by-row order
			int row = i / c; // TARGET: the answer has c columns
			int col = i % c;
			matrix[row][col] = grid[i / m][i % m]; // COPY: grid has m columns
		}
		return matrix;
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 2 }, { 3, 4 } };
		System.out.println(Arrays.deepToString(reshape(grid1, 1, 4))); // [[1, 2, 3, 4]]
		int[][] grid2 = { { 1, 2 }, { 3, 4 } };
		System.out.println(Arrays.deepToString(reshape(grid2, 2, 4))); // [[1, 2], [3, 4]]
	}
}