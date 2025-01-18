package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 289. Game of Life

public final class GameOfLife289 {
	// Time Complexity: O(n * m)
	// Space Complexity: O(1)

	private static final int[] dx = { 0, 1, 0, -1, 1, 1, -1, -1 };
	private static final int[] dy = { 1, 0, -1, 0, 1, -1, 1, -1 };

	public static void gameOfLife(int[][] grid) {
		applyRules(grid);
		transform(grid);
	}

	private static void transform(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (grid[row][col] == 2) {
					grid[row][col] = 0;
				} else if (grid[row][col] == -1) {
					grid[row][col] = 1;
				}
			}
		}
	}

	private static void applyRules(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				int live = liveNeighbors(grid, row, col);
				// rule 1 or rule 3
				if (grid[row][col] == 1 && (live < 2 || live > 3)) {
					grid[row][col] = 2;
				}
				// rule 4
				if (grid[row][col] == 0 && live == 3) {
					grid[row][col] = -1;
				}
			}
		}
	}

	private static int liveNeighbors(int[][] grid, int row, int col) {
		int count = 0;
		for (int k = 0; k < dx.length; k++) {
			int x = row + dx[k];
			int y = col + dy[k];
			if (valid(grid, x, y) && grid[x][y] > 0) {
				count++;
			}
		}
		return count;
	}

	private static boolean valid(int[][] grid, int x, int y) {
		int n = grid.length;
		int m = grid[0].length;
		boolean row = x >= 0 && x < n;
		boolean col = y >= 0 && y < m;
		return row && col;
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
		gameOfLife(grid1);
		Print.print(grid1); // [0,0,0],[1,0,1],[0,1,1],[0,1,0]
		int[][] grid2 = { { 1, 1 }, { 1, 0 } };
		gameOfLife(grid2);
		Print.print(grid2); // [1,1],[1,1]
	}
}