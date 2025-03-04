package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 566. Reshape the Matrix

public final class ReshapeMatrix {
	// Time Complexity: O(m * n)
	// Space Complexity: O(r * c)

	public static int[][] reshape(int[][] grid, int r, int c) {
		int n = grid.length;
		int m = grid[0].length;
		if (n * m != r * c) {
			return grid;
		}
		int[][] matrix = new int[r][c];
		for (int i = 0; i < n * m; i++) {
			int row = i / c;
			int col = i % c;
			matrix[row][col] = grid[i / m][i % m];
		}
		return matrix;
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 2 }, { 3, 4 } };
		Print.print(reshape(grid1, 1, 4)); // [[1,2,3,4]]
		int[][] grid2 = { { 1, 2 }, { 3, 4 } };
		Print.print(reshape(grid2, 2, 4)); // [[1,2],[3,4]]
	}
}