package com.svetanis.algorithms.backtracking.grid;

// 1219. Path with Maximum Gold
//
// Input: a grid of m rows and n columns (1 <= m, n <= 15); a cell holds the amount of gold in it
// (0 <= gold <= 100), and 0 means empty. At most 25 cells hold gold. You may start and stop at
// any cell that holds gold, move one step up, down, left or right, never onto an empty cell, and
// take the gold of each cell you stand on, once. Returns the most gold one walk can collect.
//
// The one idea: a walk is a list of gold cells that never repeats one, and the same cell can be on
// many different walks. For every starting cell the search extends the walk one step at a time,
// marks the cell it stands on, and unmarks it on the way out so another walk may use it. The gold
// collected so far is carried down as a parameter, and every cell reached makes it a candidate
// for the best.
//
// MOVES: all four neighbours, with the marker cleared on the way out so a cell
// is free again for a different route.
// REPORTS: the most gold, combined with `max`, and `max` is threaded down as a
// parameter -- so the return value is "the best seen anywhere so far" and this
// cannot be cached on (x, y).
// VERIFIED: prints 24 and 28, which are LC 1219's two published examples.
//
// Time: O(k * 3^k) -- k is the number of gold cells (at most 25); each of the k starts walks
// at most 4 * 3^(k - 1) paths that never revisit a cell.
// Space: O(m * n) -- a fresh visited array per start, and a recursion at most k deep.
//
// Siblings: PathWithMaxGoldSimple is the same search with a loop over dx/dy direction arrays, and
// it marks a cell by setting it to 0 in the grid instead of keeping a visited array.

public final class PathWithMaxGold {

	public int maxGold(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] != 0) { // START: any cell that holds gold
					boolean[][] visited = new boolean[n][m];
					int gold = dfs(grid, visited, i, j, 0, 0);
					max = Math.max(max, gold);
				}
			}
		}
		return max;
	}

	private int dfs(int[][] grid, boolean[][] visited, int x, int y, int gold, int max) {
		gold += grid[x][y]; // TAKE: the gold of this cell
		visited[x][y] = true; // MARK: on the current walk
		max = Math.max(max, gold); // RECORD: a walk may stop here
		// go down (x, y) --> (x + 1, y)
		if (valid(grid, visited, x + 1, y)) {
			max = dfs(grid, visited, x + 1, y, gold, max); // WALK: down
		}
		// go up (x, y) --> (x - 1, y)
		if (valid(grid, visited, x - 1, y)) {
			max = dfs(grid, visited, x - 1, y, gold, max); // WALK: up
		}
		// go right (x, y) --> (x, y + 1)
		if (valid(grid, visited, x, y + 1)) {
			max = dfs(grid, visited, x, y + 1, gold, max); // WALK: right
		}
		// go left (x, y) --> (x, y - 1)
		if (valid(grid, visited, x, y - 1)) {
			max = dfs(grid, visited, x, y - 1, gold, max); // WALK: left
		}
		visited[x][y] = false; // UNDO: backtrack
		gold -= grid[x][y];
		return max; // RETURN: the best seen so far
	}

	private static boolean valid(int[][] grid, boolean[][] visited, int x, int y) {
		int n = grid.length;
		int m = grid[0].length;
		return x >= 0 && x < n && y >= 0 && y < m && grid[x][y] != 0 && !visited[x][y];
	}

	public static void main(String[] args) {
		PathWithMaxGold pmg = new PathWithMaxGold();
		int[][] g1 = { { 0, 6, 0 }, { 5, 8, 7 }, { 0, 9, 0 } };
		System.out.println(pmg.maxGold(g1)); // 24
		PathWithMaxGold pmg1 = new PathWithMaxGold();
		int[][] g2 = { { 1, 0, 7 }, { 2, 0, 6 }, { 3, 4, 5 }, { 0, 3, 0 }, { 9, 0, 20 } };
		System.out.println(pmg1.maxGold(g2)); // 28
	}
}
