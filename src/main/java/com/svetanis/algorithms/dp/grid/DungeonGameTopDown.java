package com.svetanis.algorithms.dp.grid;

// 174. Dungeon Game

public final class DungeonGameTopDown {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final int INF = Integer.MAX_VALUE;

	private int rows;
	private int cols;
	private int[][] dp;

	public int dungeonGame(int[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.dp = new int[rows][cols];
		return dfs(grid, 0, 0);
	}

	private int dfs(int[][] grid, int row, int col) {
		if (row >= rows || col >= cols) {
			return INF;
		}
		if (dp[row][col] != 0) {
			return dp[row][col];
		}
		if (row == rows - 1 && col == cols - 1) {
			return dp[row][col] = Math.max(1, 1 - grid[row][col]);
		}
		int right = dfs(grid, row, col + 1);
		int down = dfs(grid, row + 1, col);
		int minNext = Math.min(right, down);
		return dp[row][col] = Math.max(1, minNext - grid[row][col]);
	}

	public static void main(String[] args) {
		DungeonGameTopDown dgb = new DungeonGameTopDown();
		int[][] grid1 = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		System.out.println(dgb.dungeonGame(grid1)); // 7
		int[][] grid2 = { { 0 } };
		System.out.println(dgb.dungeonGame(grid2)); // 1
	}
}
