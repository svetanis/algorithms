package com.svetanis.algorithms.backtracking;

// 1219. Path with Maximum Gold

public final class PathWithMaxGoldSimple {
	// Time Complexity: O(4^(n*m))
	// Space Complexity: O(n * m)

	private static final int[] dx = { -1, 0, 0, 1 };
	private static final int[] dy = { 0, -1, 1, 0 };

	public int maxGold(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] != 0) {
					max = Math.max(max, dfs(grid, i, j));
				}
			}
		}
		return max;
	}

	private int dfs(int[][] grid, int x, int y) {
		if (invalid(grid, x, y)) {
			return 0;
		}
		int max = 0;
		int gold = grid[x][y];
		grid[x][y] = 0;
		for (int k = 0; k < 4; k++) {
			int row = x + dx[k];
			int col = y + dy[k];
			max = Math.max(max, gold + dfs(grid, row, col));
		}
		grid[x][y] = gold;
		return max;
	}

	private boolean invalid(int[][] grid, int x, int y) {
		int n = grid.length;
		int m = grid[0].length;
		return x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0;
	}

	public static void main(String[] args) {
		PathWithMaxGoldSimple pmg = new PathWithMaxGoldSimple();
		int[][] g1 = { { 0, 6, 0 }, { 5, 8, 7 }, { 0, 9, 0 } }; // 24
		System.out.println(pmg.maxGold(g1));
		PathWithMaxGoldSimple pmg1 = new PathWithMaxGoldSimple();
		int[][] g2 = { { 1, 0, 7 }, { 2, 0, 6 }, { 3, 4, 5 }, { 0, 3, 0 }, { 9, 0, 20 } }; // 28
		System.out.println(pmg1.maxGold(g2));
	}
}
