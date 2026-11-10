package com.svetanis.algorithms.matrix.rotate;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 1861. Rotating the Box
//
// Input: grid -- an n x m side view of a box; each cell is a stone '#', a fixed obstacle '*' or
// empty '.'. The box is turned a quarter-turn clockwise, and then every stone falls straight
// down until it lands on an obstacle, another stone or the bottom; obstacles do not move.
// Returns the m x n grid after the turn and the fall. On LeetCode: 1 <= n, m <= 500.
//
// The one idea: copy the grid turned first, then let the stones fall down each column. Walk one
// column from the bottom up, and keep in a queue the rows of the empty places met so far, lowest
// first. A stone with an empty place below it moves to the lowest one, the front of the queue,
// and the place it left joins the back -- it is the highest empty place so far. A stone with
// nothing empty below it stays. An obstacle empties the queue: nothing above it can fall past it.
//
// Time: O(n * m) -- the turn copies each cell once, and the fall visits each cell once with one
// queue step per empty place and per moved stone; the most work of the three 1861 files, since
// every queue step stores a boxed Integer.
// Space: O(n * m) -- the turned grid, which is the answer; the queue holds at most one column.
// The caller's grid is not changed.
//
// Siblings, the same problem:
//   RotatingTheBox           the same order, with one int (the lowest free place) instead of the
//                            queue
//   RotatingTheBoxOptimized  lets the stones fall along each row BEFORE the turn, inside the
//                            caller's grid; the same cost as RotatingTheBox

public final class RotatingTheBoxQueue {

	public static char[][] rotate(char[][] grid) {
		char[][] rotated = rotated(grid); // TURN first: the stones then fall down the columns
		int n = rotated.length;
		int m = rotated[0].length;
		for (int col = 0; col < m; col++) {
			Deque<Integer> dq = new ArrayDeque<>(); // QUEUE: rows of the empty places, lowest first
			for (int row = n - 1; row >= 0; row--) { // WALK: bottom up
				if (rotated[row][col] == '*') {
					dq.clear(); // OBSTACLE: no stone above falls past it
				} else if (rotated[row][col] == '.') {
					dq.offer(row); // EMPTY: joins the back, the highest so far
				} else if (!dq.isEmpty()) { // STONE with an empty place below it
					int top = dq.pollFirst(); // TAKE the lowest empty place
					rotated[top][col] = '#';
					rotated[row][col] = '.';
					dq.offer(row); // the place it left is now the highest empty one
				}
			}
		}
		return rotated; // RETURN: the turned grid after the fall
	}

	public static char[][] rotated(char[][] grid) {
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