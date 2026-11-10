package com.svetanis.algorithms.matrix.rotate;

import java.util.ArrayList;
import java.util.List;

// 1260. Shift 2D Grid
//
// Input: grid -- a rows x cols grid of numbers; k -- how many shifts. One shift moves every
// number one place to the right; the last number of a row moves to the start of the next row,
// and the last number of the grid moves to the top-left corner. Returns the grid after k shifts,
// as a list of rows. On LeetCode: 1 <= rows, cols <= 50, -1000 <= grid[i][j] <= 1000,
// 0 <= k <= 100.
//
// The one idea: read the grid row by row as one long line of rows * cols numbers. The number at
// (row, col) sits at place row * cols + col of that line, and one shift moves every number one
// place along the line, the last one wrapping to place 0. So k shifts move it k places:
// (place + k) % (rows * cols). Division by cols turns a place back into a row, and the
// remainder into a column. No shift is simulated.
//
// Time: O(rows * cols) -- each number is placed once.
// Space: O(rows * cols) -- the returned lists; the grid itself is not changed.

public final class Shift2DGrid {

	public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
		int rows = grid.length;
		int cols = grid[0].length;
		k %= (rows * cols); // WRAP: rows * cols shifts bring every number back
		List<List<Integer>> lists = init(rows, cols);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int index = row * cols + col; // PLACE on the long line
				int shift = (index + k) % (rows * cols); // MOVE k places, wrapping to the start
				int newRow = shift / cols; // BACK to a row and a column
				int newCol = shift % cols;
				lists.get(newRow).set(newCol, grid[row][col]);
			}
		}
		return lists;
	}

	private static List<List<Integer>> init(int rows, int cols) {
		List<List<Integer>> lists = new ArrayList<>();
		for (int row = 0; row < rows; row++) {
			List<Integer> list = new ArrayList<>();
			for (int col = 0; col < cols; col++) {
				list.add(0); // FILL: set() needs the place to exist
			}
			lists.add(list);
		}
		return lists;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(shiftGrid(g1, 1)); // [[9, 1, 2], [3, 4, 5], [6, 7, 8]]
		int[][] g2 = { { 3, 8, 1, 9 }, { 19, 7, 2, 5 }, { 4, 6, 11, 10 }, { 12, 0, 21, 13 } };
		System.out.println(shiftGrid(g2, 4)); // [[12, 0, 21, 13], [3, 8, 1, 9], [19, 7, 2, 5], [4, 6, 11, 10]]
		int[][] g3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(shiftGrid(g3, 9)); // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
	}
}