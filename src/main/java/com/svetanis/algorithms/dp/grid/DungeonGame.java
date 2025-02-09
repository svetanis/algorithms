package com.svetanis.algorithms.dp.grid;

import java.util.Arrays;

// 174. Dungeon Game

public final class DungeonGame {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int dungeonGame(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n + 1][m + 1];
		for(int[] row : dp) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		dp[n][m - 1] = 1;
		dp[n - 1][m] = 1;
		for(int row = n - 1; row >= 0; row--) {
			for(int col = m - 1; col >= 0; col--) {
				int min = Math.min(dp[row + 1][col], dp[row][col+ 1]);
				dp[row][col] = Math.max(1, min - grid[row][col]);
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[][] grid1 = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		System.out.println(dungeonGame(grid1)); // 7
		int[][] grid2 = { { 0 } };
		System.out.println(dungeonGame(grid2)); // 1
	}
}
