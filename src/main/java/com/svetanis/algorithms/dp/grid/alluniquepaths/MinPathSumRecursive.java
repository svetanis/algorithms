package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 64. Minimum Path Sum

public final class MinPathSumRecursive {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int mps(int[][] grid) {
		return dfs(grid, 0, 0);
	}

	private static int dfs(int[][] grid, int row, int col) {
		int n = grid.length;
		int m = grid[0].length;
		if (row == n - 1 && col == m - 1) {
			return grid[row][col];
		}
		if (row == n - 1) {
			return grid[row][col] + dfs(grid, row, col + 1);
		}
		if (col == m - 1) {
			return grid[row][col] + dfs(grid, row + 1, col);
		}
		int right = dfs(grid, row, col + 1);
		int down = dfs(grid, row + 1, col);
		return grid[row][col] + Math.min(right, down);
	}

	public static void main(String[] args) {
		// m rows and n columns
		int[][] g1 = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int[][] g2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(mps(g1)); // 7: 1->3->1->1->1
		System.out.println(mps(g2)); // 12
	}
}
