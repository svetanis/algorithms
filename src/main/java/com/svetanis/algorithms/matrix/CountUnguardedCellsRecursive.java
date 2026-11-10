package com.svetanis.algorithms.matrix;

// 2257. Count Unguarded Cells in the Grid

// KEPT AS A SPECIMEN of recursion depth. dfs() calls itself once per
// cell along a guard's line of sight, so the depth equals the length
// of that line. LC 2257 allows a single row or column of up to 1e5
// cells, and a guard at one end of it overflows the default JVM
// stack. CountUnguardedCells.java walks the same lines with a loop
// and handles that input -- use it.
//
// Input: m, n -- the grid's rows and columns; guards[i] = [row, col] and walls[j] = [row, col],
// no position listed twice. A guard sees every cell up, down, left and right of it until a wall,
// another guard or the edge of the grid blocks the view. Returns the number of cells that are not
// a guard, not a wall, and not seen by any guard. On LeetCode: 1 <= m, n <= 10^5,
// 2 <= m * n <= 10^5, 1 <= guards.length, walls.length <= 5 * 10^4,
// 2 <= guards.length + walls.length <= m * n, all positions distinct.
//
// The one idea: one cell of grid holds 0 (empty, not seen yet), 1 (seen by a guard) or 2 (a guard
// or a wall -- something that blocks a view). Every guard and wall is marked 2 first. Then each
// guard starts one look in each of its four directions; a look marks its cell 1 and moves one cell
// on in the same direction, the dir letter carrying which way, until it reaches a 2 or the edge.
// A cell already marked 1 does not stop it: a seen cell is still empty. Every cell still 0 at the
// end is unguarded.
//
// Time: O(n * m) -- a look stops at the next guard, so each cell is passed at most once per
// direction, four times in all, plus one pass to count.
// Space: O(n * m) for the grid, plus O(max(n, m)) call stack -- one frame per cell of the
// longest line a guard looks along.
//
// Siblings: CountUnguardedCells makes the same looks with a while loop and no call stack.

public final class CountUnguardedCellsRecursive {

	public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
		int[][] grid = new int[m][n]; // 0 empty, 1 seen, 2 guard or wall
		for (int[] guard : guards) {
			int row = guard[0], col = guard[1];
			grid[row][col] = 2; // BLOCK: a guard stops another guard's view
		}
		for (int[] wall : walls) {
			int row = wall[0], col = wall[1];
			grid[row][col] = 2; // BLOCK: a wall
		}
		for (int[] guard : guards) {
			int row = guard[0], col = guard[1];
			dfs(row - 1, col, grid, 'U'); // LOOK up
			dfs(row + 1, col, grid, 'D'); // LOOK down
			dfs(row, col - 1, grid, 'L'); // LOOK left
			dfs(row, col + 1, grid, 'R'); // LOOK right
		}
		int count = 0;
		for (int row = 0; row < m; row++) {
			for (int col = 0; col < n; col++) {
				if (grid[row][col] == 0) { // COUNT: empty and never seen
					count += 1;
				}
			}
		}
		return count;
	}

	private static void dfs(int row, int col, int[][] grid, char dir) {
		int n = grid.length, m = grid[0].length;
		if (row < 0 || row >= n || col < 0 || col >= m || grid[row][col] == 2) {
			return; // STOP: the edge, a guard or a wall
		}
		grid[row][col] = 1; // SEEN
		if (dir == 'U') {
			dfs(row - 1, col, grid, 'U'); // NEXT cell the same way: one stack frame per cell
		}
		if (dir == 'D') {
			dfs(row + 1, col, grid, 'D');
		}
		if (dir == 'L') {
			dfs(row, col - 1, grid, 'L');
		}
		if (dir == 'R') {
			dfs(row, col + 1, grid, 'R');
		}
	}

	public static void main(String[] agrs) {
		int[][] g1 = { { 0, 0 }, { 1, 1 }, { 2, 3 } };
		int[][] w1 = { { 0, 1 }, { 2, 2 }, { 1, 4 } };
		System.out.println(countUnguarded(4, 6, g1, w1)); // 7

		int[][] g2 = { { 1, 1 } };
		int[][] w2 = { { 0, 1 }, { 1, 0 }, { 2, 1 }, { 1, 2 } };
		System.out.println(countUnguarded(3, 3, g2, w2)); // 4
	}
}
