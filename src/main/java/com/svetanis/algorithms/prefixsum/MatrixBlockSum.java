package com.svetanis.algorithms.prefixsum;

// 1314. Matrix Block Sum
//
// Input: a grid (mat on LeetCode) of m rows and n columns, and k. Returns a grid of the same shape
// whose cell [i][j] is the total of every cell [r][c] with i - k <= r <= i + k and
// j - k <= c <= j + k that lies inside the grid -- the square of side 2k + 1 centred on [i][j],
// cut off at the grid's edges. On LeetCode: 1 <= m, n, k <= 100, 1 <= mat[i][j] <= 100.
//
// The one idea: prefix[i][j] holds the total of the rectangle made of the top i rows and the left
// j columns; row 0 and column 0 are the empty rectangle, 0. It is built from three earlier
// entries: the one above plus the one to the left counts their shared corner rectangle twice, so
// subtract that once, then add the cell itself. Any rectangle's total then takes four lookups: the
// rectangle down to its bottom-right corner, minus the strip above it, minus the strip to its
// left, plus the top-left rectangle that both strips removed. Each block is such a rectangle once
// its edges are clamped into the grid, and sum() does the clamping.
//
// The table and the grid's size live in instance fields that every call to blockSum sets again,
// so one instance answers grids of any shape in turn; until the next call it still holds the last
// grid's table.
//
// Time: O(m * n) -- the build visits each cell once, and each block is four lookups whatever k is.
// Space: O(m * n) -- the prefix table and the answer.
//
// Siblings: none for this problem. RangeSumQuery2DSubmit (304) builds the same table and is asked
// for rectangles given by their corners.

public final class MatrixBlockSum {

	private int rows;
	private int cols;
	private int[][] prefix;

	public int[][] blockSum(int[][] grid, int k) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.prefix = prefix(grid); // BUILD once per grid
		int[][] sum = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int bottomRight = sum(i + k + 1, j + k + 1); // WHOLE: everything up to the block's bottom-right
				int topLeft = sum(i - k, j - k); // CORNER: removed twice by the two strips
				int overlap = sum(i + k + 1, j - k) + sum(i - k, j + k + 1); // STRIPS: left of and above the block
				sum[i][j] = topLeft + bottomRight - overlap; // BLOCK: whole - strips + corner
			}
		}
		return sum;
	}

	private int[][] prefix(int[][] grid) {
		this.prefix = new int[rows + 1][cols + 1]; // row 0 and column 0 stay 0: the empty rectangle
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				int top = prefix[i - 1][j];
				int left = prefix[i][j - 1];
				int diag = prefix[i - 1][j - 1]; // SHARED corner, counted in both top and left
				int cell = grid[i - 1][j - 1];
				prefix[i][j] = top + left - diag + cell; // TOTAL of the top i rows, left j columns
			}
		}
		return prefix;
	}

	// prefix entry for the top i rows and left j columns, with i and j clamped into 0..rows, 0..cols
	private int sum(int i, int j) {
		i = Math.max(Math.min(i, rows), 0); // CLAMP: a block past the edge stops at the edge
		j = Math.max(Math.min(cols, j), 0);
		return prefix[i][j];
	}

	// PRINT: each row's values followed by a space, a line per row, then an empty line
	private static void print(int[][] grid) {
		for (int[] row : grid) {
			for (int value : row) {
				System.out.printf("%d ", value);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MatrixBlockSum mbs = new MatrixBlockSum();
		int[][] g1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		print(mbs.blockSum(g1, 1)); // 12 21 16 / 27 45 33 / 24 39 28, a row per line
		int[][] g2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		print(mbs.blockSum(g2, 2)); // 45 45 45 / 45 45 45 / 45 45 45, a row per line
	}
}
