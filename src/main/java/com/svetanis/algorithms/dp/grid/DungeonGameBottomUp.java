package com.svetanis.algorithms.dp.grid;

import java.util.Arrays;

// 174. Dungeon Game

public final class DungeonGameBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final int INF = Integer.MAX_VALUE;

	private int rows;
	private int cols;
	private int[][] dp;

	public int dungeonGame(int[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.dp = new int[rows + 1][cols + 1];
		for (int[] row : dp) {
			Arrays.fill(row, INF);
		}
		for (int row = rows - 1; row >= 0; row--) {
			for (int col = cols - 1; col >= 0; col--) {
				int curr = grid[row][col];
				int right = minHealth(curr, row, col + 1);
				int down = minHealth(curr, row + 1, col);
				int next = Math.min(right, down);
				int minHealth;
				if (next != INF) {
					minHealth = next;
				} else {
					minHealth = curr >= 0 ? 1 : 1 - curr;
				}
				dp[row][col] = minHealth;
			}
		}
		return dp[0][0];
	}

	private int minHealth(int curr, int row, int col) {
		if (row >= rows || col >= cols) {
			return INF;
		}
		int next = dp[row][col];
		return Math.max(1, next - curr);
	}

	public static void main(String[] args) {
		DungeonGameBottomUp dgb = new DungeonGameBottomUp();
		int[][] grid1 = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		System.out.println(dgb.dungeonGame(grid1)); // 7
		int[][] grid2 = { { 0 } };
		System.out.println(dgb.dungeonGame(grid2)); // 1
	}
}
