package com.svetanis.algorithms.matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 2768. Number of Black Blocks
//
// Input: rows, cols -- the size of a grid whose cells are all white except the ones in
// coordinates; coordinates[i] = [x, y] is a black cell, and no cell is listed twice. A block is a
// 2 x 2 square of cells inside the grid. Returns arr of length 5: arr[i] is the number of blocks
// holding exactly i black cells. On LeetCode: 2 <= rows, cols <= 10^5,
// 0 <= coordinates.length <= 10^4, 0 <= x < rows, 0 <= y < cols, the coordinates are distinct.
//
// The one idea: one block is named by its top-left cell (x, y) and covers (x, y), (x, y + 1),
// (x + 1, y) and (x + 1, y + 1); its top-left cell can be any cell except one in the last row or
// the last column. So one black cell (r, c) lies in at most four blocks -- the ones named (r, c),
// (r, c - 1), (r - 1, c - 1) and (r - 1, c) -- fewer when one of those names falls off the grid.
// There are (rows - 1) * (cols - 1) blocks, up to about 10^10, far too many to list; but at most
// 4 * 10^4 of them hold any black cell. So a map from a block's name to its black count holds
// only those blocks. Every block not in the map holds 0 black cells, so arr[0] starts at the total
// and loses one for each block in the map.
//
// Time: O(k), k = coordinates.length -- four map updates per black cell, then one pass over at
// most 4k map entries.
// Space: O(k) -- the map holds at most 4k blocks.

public final class CountBlackBlocks {

	private int rows;
	private int cols;

	private final int[] dir = { 0, 0, -1, -1, 0 }; // OFFSETS: (dir[k], dir[k + 1]) = the cell is the block's top-left, top-right, bottom-right, bottom-left

	public long[] count(int rows, int cols, int[][] coordinates) {
		this.rows = rows;
		this.cols = cols;
		Map<Long, Integer> map = new HashMap<>(); // MAP: block name -> black cells in it
		for (int[] coordinate : coordinates) {
			int row = coordinate[0];
			int col = coordinate[1];
			for (int k = 0; k < 4; k++) { // EACH of the four blocks that can hold this cell
				int x = row + dir[k];
				int y = col + dir[k + 1];
				if (valid(x, y)) {
					long index = 1L * x * cols + y; // NAME the block: a long, since x * cols can pass 2^31
					map.merge(index, 1, Integer::sum); // ADD one black cell to it
				}
			}
		}
		long[] a = new long[5];
		a[0] = (long) (rows - 1) * (cols - 1); // ALL blocks, before any black cell is counted
		for (int count : map.values()) {
			a[count]++; // RECORD: this block holds count black cells
			a[0]--; // so it is not an all-white block
		}
		return a;
	}

	private boolean valid(int x, int y) {
		return x >= 0 && x < rows - 1 && y >= 0 && y < cols - 1; // NAME exists: not in the last row or column
	}

	public static void main(String[] agrs) {
		CountBlackBlocks cbb = new CountBlackBlocks();
		int[][] g1 = { { 0, 0 } };
		System.out.println(Arrays.toString(cbb.count(3, 3, g1))); // [3, 1, 0, 0, 0]
		int[][] g2 = { { 0, 0 }, { 1, 1 }, { 0, 2 } };
		System.out.println(Arrays.toString(cbb.count(3, 3, g2))); // [0, 2, 2, 0, 0]
	}
}
