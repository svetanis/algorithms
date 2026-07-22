package com.svetanis.algorithms.matrix;

// 2257. Count Unguarded Cells in the Grid

public final class CountUnguardedCellsRecursive {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

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
			int row = guard[0], col = guard[1];
			dfs(row - 1, col, grid, 'U');
			dfs(row + 1, col, grid, 'D');
			dfs(row, col - 1, grid, 'L');
			dfs(row, col + 1, grid, 'R');
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

	private static void dfs(int row, int col, int[][] grid, char dir) {
		int n = grid.length, m = grid[0].length;
		if (row < 0 || row >= n || col < 0 || col >= m || grid[row][col] == 2) {
			return;
		}
		grid[row][col] = 1;
		if (dir == 'U') {
			dfs(row - 1, col, grid, 'U');
		}
		if (dir == 'D') {
			dfs(row + 1, col, grid, 'D');
		}
		if (dir == 'L') {
			dfs(row, col - 1, grid, 'L');
		}
		if (dir == 'R') {
			dfs(row, col + 1, grid, 'R');
		}
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
