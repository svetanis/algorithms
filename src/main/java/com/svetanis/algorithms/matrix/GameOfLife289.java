package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 289. Game of Life
//
// Input: grid -- an m x n board; a cell holds 1 (live) or 0 (dead). A cell's neighbours are the up
// to eight cells around it, sideways and diagonal. Every cell changes at the same moment: a live
// cell with fewer than 2 live neighbours dies, with 2 or 3 it lives on, with more than 3 it dies;
// a dead cell with exactly 3 live neighbours becomes live. Changes grid in place to the next
// state; returns nothing. On LeetCode: 1 <= m, n <= 25, grid[i][j] is 0 or 1.
//
// The one idea: every cell changes at the same moment, so a cell changed early in the pass must
// still show its old state to the neighbours read after it. Each cell keeps both states in one
// int, using two values the board never holds: 2 means "was live, now dead" and -1 means "was
// dead, now live". A neighbour counts as live when it is > 0 -- true for 1 and 2, false for 0 and
// -1 -- which is exactly its old state, so every count reads the old board. A second pass turns
// 2 into 0 and -1 into 1.
//
// Time: O(m * n) -- eight neighbours read per cell, then one more pass.
// Space: O(1) -- the two states share the grid's own cells.

public final class GameOfLife289 {

	private static final int[] dx = { 0, 1, 0, -1, 1, 1, -1, -1 }; // NEIGHBOURS: (dx[k], dy[k]), the eight around a cell
	private static final int[] dy = { 1, 0, -1, 0, 1, -1, 1, -1 };

	public static void gameOfLife(int[][] grid) {
		applyRules(grid); // MARK: 2 and -1 keep the old state visible
		transform(grid); // SETTLE: markers to their new state
	}

	private static void transform(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (grid[row][col] == 2) {
					grid[row][col] = 0; // DIED
				} else if (grid[row][col] == -1) {
					grid[row][col] = 1; // BORN
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
				if (grid[row][col] == 1 && (live < 2 || live > 3)) { // DIES: too few or too many
					grid[row][col] = 2; // was live, now dead
				}
				if (grid[row][col] == 0 && live == 3) { // BORN: exactly three
					grid[row][col] = -1; // was dead, now live
				}
			}
		}
	}

	private static int liveNeighbors(int[][] grid, int row, int col) {
		int count = 0;
		for (int k = 0; k < dx.length; k++) {
			int x = row + dx[k];
			int y = col + dy[k];
			if (valid(grid, x, y) && grid[x][y] > 0) { // OLD state live: 1 or 2
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
		System.out.println(Arrays.deepToString(grid1)); // [[0, 0, 0], [1, 0, 1], [0, 1, 1], [0, 1, 0]]
		int[][] grid2 = { { 1, 1 }, { 1, 0 } };
		gameOfLife(grid2);
		System.out.println(Arrays.deepToString(grid2)); // [[1, 1], [1, 1]]
	}
}