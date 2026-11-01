package com.svetanis.algorithms.backtracking.grid;

// 980. Unique Paths III
//
// Input: g -- a grid in which 1 is the start square, 2 is the end square, 0 is an empty square
// that may be walked on, and -1 is an obstacle. A walk moves up, down, left or right. Returns the
// number of walks from the start to the end that step on every square that is not an obstacle
// exactly once. On LeetCode: 1 <= rows, columns <= 20, 1 <= rows * columns <= 20, -1 <= g[i][j]
// <= 2, and there is exactly one start and exactly one end.
//
// The one idea: try every walk, backing out of the ones that fail. One call of dfs stands on one
// square, having taken `count` steps to get there; visited says which squares the walk has used.
// From a square, try each of the four neighbours that is inside the grid, is not an obstacle and
// is not on the walk yet: mark it, go on from it, and un-mark it when the call comes back, so the
// next walk may use it. The walk covers every square exactly once when, on arriving at the end,
// it has used start + all the empty squares + end, which is empty + 1 steps. Arriving at the end
// earlier counts nothing, and the walk stops there.
//
// Time: O(3^(rows * cols)) -- a walk is at most rows * cols squares long and, after the first
// step, it has at most 3 squares to choose from (not the one it came from).
// Space: O(rows * cols) -- the marks; the walk is at most rows * cols calls deep.

public final class UniquePaths {

	// horizontal + vertical moves
	private static int[] dx = { -1, 0, 0, 1 };
	private static int[] dy = { 0, -1, 1, 0 };

	private int rows;
	private int cols;
	private int empty;
	private int[][] g;
	private boolean[][] visited;

	public int uniquePaths(int[][] g) {
		this.rows = g.length;
		this.cols = g[0].length;
		this.g = g;
		this.empty = 0; // BASE: init() counts the empty squares of this grid only

		int[] start = init();
		int startX = start[0];
		int startY = start[1];
		this.visited = new boolean[rows][cols];
		visited[startX][startY] = true; // MARK: the walk begins on the start square
		return dfs(startX, startY, 0);
	}

	private int[] init() {
		int x = -1;
		int y = -1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (g[i][j] == 0) {
					this.empty++; // COUNT: squares that must all be used
				} else if (g[i][j] == 1) {
					x = i; // RECORD: where the start is
					y = j;
				}
			}
		}
		return new int[] { x, y };
	}

	private int dfs(int row, int col, int count) {
		if (g[row][col] == 2) {
			return count == empty + 1 ? 1 : 0; // RETURN: a walk that used every empty square counts
		}
		int pathCount = 0;
		for (int k = 0; k < dx.length; ++k) { // WALK: the four neighbours
			int x = row + dx[k];
			int y = col + dy[k];
			if (isSafe(x, y)) { // SKIP: outside, an obstacle, or already on the walk
				visited[x][y] = true; // MARK
				pathCount += dfs(x, y, count + 1);
				// backtrack
				visited[x][y] = false; // UNMARK: other walks may use this square
			}
		}
		return pathCount;
	}

	private boolean isSafe(int row, int col) {
		boolean one = row >= 0 && row < g.length; // row number is in range
		boolean two = col >= 0 && col < g[0].length; // col number is in range
		return one && two && g[row][col] != -1 && !visited[row][col];
	}

	public static void main(String[] args) {
		UniquePaths ups = new UniquePaths();
		int[][] g = { //
				{ 1, 0, 0, 0 }, //
				{ 0, 0, 0, 0 }, //
				{ 0, 0, 2, -1 } };//
		System.out.println(ups.uniquePaths(g)); // 2

		UniquePaths ups2 = new UniquePaths();
		int[][] g1 = { //
				{ 1, 0, 0, 0 }, //
				{ 0, 0, 0, 0 }, //
				{ 0, 0, 0, 2 } };//
		System.out.println(ups2.uniquePaths(g1)); // 4

		int[][] g2 = { //
				{ 0, 1 }, //
				{ 2, 0 } };//
		System.out.println(ups.uniquePaths(g2)); // 0
		System.out.println(ups.uniquePaths(g)); // 2
	}
}
