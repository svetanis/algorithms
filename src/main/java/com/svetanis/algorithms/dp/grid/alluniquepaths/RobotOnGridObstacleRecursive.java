package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 63. Unique Paths II

public final class RobotOnGridObstacleRecursive {
	// Time Complexity: O(2^(n + m))
	// Space Complexity: O(n + m)

	public static int countUniquePaths(int[][] grid) {
		return dfs(grid, 0, 0);
	}

	private static int dfs(int[][] grid, int row, int col) {
		int n = grid.length;
		int m = grid[0].length;
		if (row >= n || col >= m || grid[row][col] == 1) {
			return 0;
		}
		if (row == n - 1 && col == m - 1) {
			return 1;
		}
		int right = dfs(grid, row, col + 1);
		int down = dfs(grid, row + 1, col);
		return right + down;
	}

	public static void main(String[] args) {
		// n rows and m columns
		int[][] g1 = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		int[][] g2 = { { 0, 1 }, { 0, 0 } };
		int[][] g3 = { { 1 } };
		int[][] g4 = { { 1, 0 } };
		System.out.println(countUniquePaths(g1)); // 2
		System.out.println(countUniquePaths(g2)); // 1
		System.out.println(countUniquePaths(g3)); // 0
		System.out.println(countUniquePaths(g4)); // 0
	}
}
