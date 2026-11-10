package com.svetanis.algorithms.matrix.rotate;

import java.util.Arrays;

// 1861. Rotating the Box
//
// Input: grid -- an n x m side view of a box; each cell is a stone '#', a fixed obstacle '*' or
// empty '.'. The box is turned a quarter-turn clockwise, and then every stone falls straight
// down until it lands on an obstacle, another stone or the bottom; obstacles do not move.
// Returns the m x n grid after the turn and the fall. On LeetCode: 1 <= n, m <= 500.
//
// The one idea: after a clockwise turn, "down" is what was "right" before it, so the stones can
// fall BEFORE the turn instead of after: slide them to the right along each row, then turn.
// Walk one row from the right end leftwards, keeping cell = the rightmost place the next stone
// can land. An obstacle sets cell to the place just left of it; a stone moves to cell, and cell
// moves left by one.
//
// This one lets the stones fall inside the caller's grid, so the input is changed -- the other
// two versions leave it untouched. It costs the same as RotatingTheBox: "Optimized" describes
// nothing it does.
//
// Time: O(n * m) -- the fall visits each cell once, and the turn copies each cell once.
// Space: O(n * m) -- the turned grid, which is the answer; O(1) besides it.
//
// Siblings, the same problem:
//   RotatingTheBox       turns first into a new grid, then lets the stones fall down each column
//   RotatingTheBoxQueue  turns first, then keeps a queue of the empty places in each column; the
//                        most work of the three

public final class RotatingTheBoxOptimized {

	public static char[][] rotate(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int row = 0; row < n; row++) {
			int cell = m - 1; // LAND: the rightmost free place, the right wall to start
			for (int col = m - 1; col >= 0; col--) { // WALK: right to left
				if (grid[row][col] == '*') {
					cell = col - 1; // OBSTACLE: stones left of it land against it
				} else if (grid[row][col] == '#') {
					grid[row][col] = '.'; // MOVE: clear first, so a stone already at cell stays
					grid[row][cell--] = '#'; // the caller's grid changes here
				}
			}
		}
		return rotated(grid); // TURN after the fall
	}

	private static char[][] rotated(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		char[][] rotated = new char[m][n];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				rotated[col][n - row - 1] = grid[row][col]; // TURN: (row, col) -> (col, n - 1 - row)
			}
		}
		return rotated;
	}

	public static void main(String[] args) {
		// Rotate clockwise
		char m1[][] = { { '#', '.', '#' } };
		System.out.println(Arrays.deepToString(rotate(m1))); // [[.], [#], [#]]

		char m2[][] = { { '#', '.', '*', '.' }, //
				{ '#', '#', '*', '.' } };//
		System.out.println(Arrays.deepToString(rotate(m2))); // [[#, .], [#, #], [*, *], [., .]]

		char m3[][] = { { '#', '#', '*', '.', '*', '.' }, //
				{ '#', '#', '#', '*', '.', '.' }, //
				{ '#', '#', '#', '.', '#', '.' } };//
		System.out.println(Arrays.deepToString(rotate(m3))); // [[., #, #], [., #, #], [#, #, *], [#, *, .], [#, ., *], [#, ., .]]
	}
}