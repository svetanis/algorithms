package com.svetanis.algorithms.matrix.rotate;

import java.util.Arrays;

// 1861. Rotating the Box
//
// Input: grid -- an n x m side view of a box; each cell is a stone '#', a fixed obstacle '*' or
// empty '.'. The box is turned a quarter-turn clockwise, and then every stone falls straight
// down until it lands on an obstacle, another stone or the bottom; obstacles do not move.
// Returns the m x n grid after the turn and the fall. On LeetCode: 1 <= n, m <= 500.
//
// The one idea: after a clockwise turn, the box's right wall is its floor, so first copy the
// grid turned, then let the stones fall down each column. Walk one column from the bottom up,
// keeping cell = the lowest place the next stone can land. An obstacle sets cell to the place
// just above it; a stone moves to cell, and cell goes up by one. Every stone between two
// obstacles ends up stacked on the lower one, in the order they were met, which is what gravity
// gives.
//
// Time: O(n * m) -- the turn copies each cell once, and the fall visits each cell once.
// Space: O(n * m) -- the turned grid, which is the answer; O(1) besides it. The caller's grid is
// not changed.
//
// Siblings, the same problem:
//   RotatingTheBoxOptimized  lets the stones fall along each row BEFORE the turn, inside the
//                            caller's grid; the same cost, the name describes nothing it does
//   RotatingTheBoxQueue      the same order as here, with a queue of the empty places in the
//                            column instead of one int; the most work of the three

public final class RotatingTheBox {

	public static char[][] rotate(char[][] grid) {
		char[][] rotated = rotated(grid); // TURN first: the stones then fall down the columns
		int n = rotated.length;
		int m = rotated[0].length;
		for (int col = 0; col < m; col++) {
			int cell = n - 1; // LAND: the lowest free place, the bottom to start
			for (int row = n - 1; row >= 0; row--) { // WALK: bottom up
				if (rotated[row][col] == '*') {
					cell = row - 1; // OBSTACLE: stones above it land on it
				} else if (rotated[row][col] == '#') {
					rotated[row][col] = '.'; // MOVE: clear first, so a stone already at cell stays
					rotated[cell--][col] = '#';
				}
			}
		}
		return rotated; // RETURN: the turned grid after the fall
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