package com.svetanis.algorithms.matrix;

// 2352. Equal Row and Column Pairs

public final class CountRowAndColPairs2352 {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static int countPairs(int[][] grid) {
		int n = grid.length;
		int[][] transposed = transposed(grid);
		int count = 0;
		for (int[] row : grid) {
			for (int[] col : transposed) {
				int pair = 1;
				for (int i = 0; i < n; i++) {
					if (row[i] != col[i]) {
						pair = 0;
						break;
					}
				}
				count += pair;
			}
		}
		return count;
	}

	private static int[][] transposed(int[][] grid) {
		int n = grid.length;
		int[][] matrix = new int[n][n];
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				matrix[i][j] = grid[j][i];
			}
		}
		return matrix;
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 3, 2, 1 }, { 1, 7, 6 }, { 2, 7, 7 } };
		System.out.println(countPairs(grid1)); // 1
		int[][] grid2 = { { 3, 1, 2, 2 }, { 1, 4, 4, 5 }, { 2, 4, 2, 2 }, { 2, 4, 2, 2 } };
		System.out.println(countPairs(grid2)); // 3
	}
}