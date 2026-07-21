package com.svetanis.algorithms.matrix;

// 2257. Count Unguarded Cells in the Grid

public final class CountUnguardedCells {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final int[] DIR = { -1, 0, 1, 0, -1 };

	public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
		int[][] grid = new int[m][n];
		for (int[] guard : guards) {
			int row = guard[0], col = guard[1];
			grid[row][col] = 2;
		}
		for (int[] wall : walls) {
			int row = wall[0], col = wall[1];
			grid[row][col] = 2;
		}
		for (int[] guard : guards) {
			for (int k = 0; k < 4; k++) {
				int row = guard[0], col = guard[1];
				int dx = DIR[k];
				int dy = DIR[k + 1];
				while (row + dx >= 0 && row + dx < m //
						&& col + dy >= 0 && col + dy < n //
						&& grid[row + dx][col + dy] < 2) {//
					row += dx;
					col += dy;
					grid[row][col] = 1;
				}
			}
		}
		int count = 0;
		for (int row = 0; row < m; row++) {
			for (int col = 0; col < n; col++) {
				if (grid[row][col] == 0) {
					count += 1;
				}
			}
		}
		return count;
	}

	public static void main(String[] agrs) {
		int[][] g1 = { { 0, 0 }, { 1, 1 }, { 2, 3 } };
		int[][] w1 = { { 0, 1 }, { 2, 2 }, { 1, 4 } };
		System.out.println(countUnguarded(4, 6, g1, w1)); // 7

		int[][] g2 = { { 1, 1 } };
		int[][] w2 = { { 0, 1 }, { 1, 0 }, { 2, 1 }, { 1, 2 } };
		System.out.println(countUnguarded(3, 3, g2, w2)); // 4
	}
}
