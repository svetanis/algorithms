package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

public final class SetMatrixZeroes {
	// Time Complexity: O(n * m)
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