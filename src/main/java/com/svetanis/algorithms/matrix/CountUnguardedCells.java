package com.svetanis.algorithms.matrix;

// 2257. Count Unguarded Cells in the Grid
//
// Input: m, n -- the grid's rows and columns; guards[i] = [row, col] and walls[j] = [row, col],
// no position listed twice. A guard sees every cell up, down, left and right of it until a wall,
// another guard or the edge of the grid blocks the view. Returns the number of cells that are not
// a guard, not a wall, and not seen by any guard. On LeetCode: 1 <= m, n <= 10^5,
// 2 <= m * n <= 10^5, 1 <= guards.length, walls.length <= 5 * 10^4,
// 2 <= guards.length + walls.length <= m * n, all positions distinct.
//
// The one idea: one cell of grid holds 0 (empty, not seen yet), 1 (seen by a guard) or 2 (a guard
// or a wall -- something that blocks a view). Every guard and wall is marked 2 first, so a view
// stops at the right place whichever guard looks first. Then each guard looks along its four
// lines, marking each cell it passes 1 and stopping at the first 2 or the edge. A cell already
// marked 1 does not stop the look: a seen cell is still empty, and the view goes through it.
// Every cell still 0 at the end is unguarded.
//
// Time: O(m * n) -- a guard's line in one direction stops at the next guard, so the lines of
// different guards in one direction never overlap: each cell is passed at most once per
// direction, four times in all, plus one pass to count.
// Space: O(m * n) -- the grid of marks.
//
// Siblings: CountUnguardedCellsRecursive marks the same lines with one recursive call per cell,
// and overflows the stack on a single row of 10^5 cells; this loop version handles that input.

public final class CountUnguardedCells {

	private static final int[] DIR = { -1, 0, 1, 0, -1 }; // DIRECTIONS: (DIR[k], DIR[k + 1]) = up, right, down, left

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
			for (int k = 0; k < 4; k++) {
				int row = guard[0], col = guard[1];
				int dx = DIR[k];
				int dy = DIR[k + 1];
				while (row + dx >= 0 && row + dx < m //
						&& col + dy >= 0 && col + dy < n //
						&& grid[row + dx][col + dy] < 2) { // LOOK: through empty and seen cells, stop at a 2 or the edge
					row += dx;
					col += dy;
					grid[row][col] = 1; // SEEN
				}
			}
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

	public static void main(String[] agrs) {
		int[][] g1 = { { 0, 0 }, { 1, 1 }, { 2, 3 } };
		int[][] w1 = { { 0, 1 }, { 2, 2 }, { 1, 4 } };
		System.out.println(countUnguarded(4, 6, g1, w1)); // 7

		int[][] g2 = { { 1, 1 } };
		int[][] w2 = { { 0, 1 }, { 1, 0 }, { 2, 1 }, { 1, 2 } };
		System.out.println(countUnguarded(3, 3, g2, w2)); // 4
	}
}
