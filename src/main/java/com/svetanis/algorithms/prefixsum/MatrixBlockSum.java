package com.svetanis.algorithms.prefixsum;

import com.svetanis.java.base.utils.Print;

// 1314. Matrix Block Sum

public final class MatrixBlockSum {
	// Time complexity: O(n * m)
	// Space complexity: O(n * m)

	private int rows;
	private int cols;
	private int[][] prefix;

	public int[][] blockSum(int[][] grid, int k) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.prefix = prefix(grid);
		int[][] sum = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int bottomRight = sum(i + k + 1, j + k + 1);
				int topLeft = sum(i - k, j - k);
				int overlap = sum(i + k + 1, j - k) + sum(i - k, j + k + 1);
				sum[i][j] = topLeft + bottomRight - overlap;
			}
		}
		return sum;
	}

	private int[][] prefix(int[][] grid) {
		this.prefix = new int[rows + 1][cols + 1];
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				int top = prefix[i - 1][j];
				int left = prefix[i][j - 1];
				int diag = prefix[i - 1][j - 1];
				int cell = grid[i - 1][j - 1];
				prefix[i][j] = top + left - diag + cell;
			}
		}
		return prefix;
	}

	private int sum(int i, int j) {
		i = Math.max(Math.min(i, rows), 0);
		j = Math.max(Math.min(cols, j), 0);
		return prefix[i][j];
	}

	public static void main(String[] args) {
		MatrixBlockSum mbs = new MatrixBlockSum();
		int[][] g1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Print.print(mbs.blockSum(g1, 1));
		int[][] g2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Print.print(mbs.blockSum(g2, 2));
	}
}
