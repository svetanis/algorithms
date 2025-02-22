package com.svetanis.algorithms.dp.grid;

// 329. Longest Increasing Path in a Matrix

public final class LongestIncreasingPath {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final int[] dx = { 1, -1, 0, 0 };
	private static final int[] dy = { 0, 0, 1, -1 };

	public static int lip(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = Integer.MIN_VALUE;
		Integer[][] dp = new Integer[n + 1][m + 1];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				max = Math.max(max, dfs(grid, row, col, dp));
			}
		}
		return max;
	}

	private static int dfs(int[][] grid, int row, int col, Integer[][] dp) {
		if (dp[row][col] != null) {
			return dp[row][col];
		}
		int max = 1;
		for (int k = 0; k < dx.length; k++) {
			int x = row + dx[k];
			int y = col + dy[k];
			if (valid(grid, x, y) && grid[x][y] > grid[row][col]) {
				max = Math.max(max, 1 + dfs(grid, x, y, dp));
			}
		}
		dp[row][col] = max;
		return max;
	}

	private static boolean valid(int[][] grid, int row, int col) {
		boolean one = row >= 0 && row < grid.length;
		boolean two = col >= 0 && col < grid[0].length;
		return one && two;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
		System.out.println(lip(g1)); // 4
		int[][] g2 = { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 } };
		System.out.println(lip(g2)); // 4
		int[][] g3 = { { 1 } };
		System.out.println(lip(g3)); // 1
	}
}
