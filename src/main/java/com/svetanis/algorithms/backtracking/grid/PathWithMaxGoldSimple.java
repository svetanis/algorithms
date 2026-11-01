package com.svetanis.algorithms.backtracking.grid;

// 1219. Path with Maximum Gold
//
// Input: a grid of m rows and n columns (1 <= m, n <= 15); a cell holds the amount of gold in it
// (0 <= gold <= 100), and 0 means empty. At most 25 cells hold gold. You may start and stop at
// any cell that holds gold, move one step up, down, left or right, never onto an empty cell, and
// take the gold of each cell you stand on, once. Returns the most gold one walk can collect.
//
// The one idea: one call answers "the most gold a walk can collect that starts at this cell?".
// It takes the cell's gold, empties the cell so the walk cannot come back to it, asks the same
// question of each of the four neighbours, and adds the best of those answers to the gold it
// took. It then puts the gold back, so another walk may use the cell. An empty cell or a step off
// the grid is worth 0.
//
// The same problem as PathWithMaxGold, written with dx/dy direction arrays
// instead of four separate `if` blocks. Same moves, same marker, same undo.
// VERIFIED: prints 24 and 28, which are LC 1219's two published examples.
//
// Time: O(k * 3^k) -- k is the number of gold cells (at most 25); each of the k starts walks
// at most 4 * 3^(k - 1) paths that never revisit a cell.
// Space: O(k) -- no visited array, the recursion is at most k deep.
//
// Siblings: PathWithMaxGold keeps a visited array and threads the best gold down as parameters;
// here each call returns the best from its own cell instead.

public final class PathWithMaxGoldSimple {

	private static final int[] dx = { -1, 0, 0, 1 };
	private static final int[] dy = { 0, -1, 1, 0 };

	public int maxGold(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] != 0) { // START: any cell that holds gold
					max = Math.max(max, dfs(grid, i, j));
				}
			}
		}
		return max;
	}

	private int dfs(int[][] grid, int x, int y) {
		if (invalid(grid, x, y)) { // BASE: off the grid, or empty (or already used)
			return 0;
		}
		int max = 0;
		int gold = grid[x][y]; // TAKE: the gold of this cell
		grid[x][y] = 0; // MARK: an empty cell cannot be entered again
		for (int k = 0; k < 4; k++) {
			int row = x + dx[k];
			int col = y + dy[k];
			max = Math.max(max, gold + dfs(grid, row, col)); // WALK: the best of the four neighbours
		}
		grid[x][y] = gold; // UNDO: put the gold back
		return max; // RETURN: the best walk that starts here
	}

	private boolean invalid(int[][] grid, int x, int y) {
		int n = grid.length;
		int m = grid[0].length;
		return x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0;
	}

	public static void main(String[] args) {
		PathWithMaxGoldSimple pmg = new PathWithMaxGoldSimple();
		int[][] g1 = { { 0, 6, 0 }, { 5, 8, 7 }, { 0, 9, 0 } };
		System.out.println(pmg.maxGold(g1)); // 24
		PathWithMaxGoldSimple pmg1 = new PathWithMaxGoldSimple();
		int[][] g2 = { { 1, 0, 7 }, { 2, 0, 6 }, { 3, 4, 5 }, { 0, 3, 0 }, { 9, 0, 20 } };
		System.out.println(pmg1.maxGold(g2)); // 28
	}
}
