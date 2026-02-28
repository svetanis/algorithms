package com.svetanis.algorithms.matrix.rotate;

import java.util.ArrayList;
import java.util.List;

// 1260. Shift 2D Grid

public final class Shift2DGrid {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
		int rows = grid.length;
		int cols = grid[0].length;
		k %= (rows * cols);
		List<List<Integer>> lists = init(rows, cols);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int index = row * cols + col;
				int shift = (index + k) % (rows * cols);
				int newRow = shift / cols;
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
				list.add(0);
			}
			lists.add(list);
		}
		return lists;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(shiftGrid(g1, 1));
		int[][] g2 = { { 3, 8, 1, 9 }, { 19, 7, 2, 5 }, { 4, 6, 11, 10 }, { 12, 0, 21, 13 } };
		System.out.println(shiftGrid(g2, 4));
		int[][] g3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(shiftGrid(g3, 9));
	}
}