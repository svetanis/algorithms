package com.svetanis.algorithms.matrix;

import java.util.HashMap;
import java.util.Map;

import com.svetanis.java.base.utils.Print;

// 2768. Number of Black Blocks

public final class CountBlackBlocks {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	private int rows;
	private int cols;

	private final int[] dir = { 0, 0, -1, -1, 0 };

	public long[] count(int rows, int cols, int[][] coordinates) {
		this.rows = rows;
		this.cols = cols;
		Map<Long, Integer> map = new HashMap<>();
		for (int[] coordinate : coordinates) {
			int row = coordinate[0];
			int col = coordinate[1];
			for (int k = 0; k < 4; k++) {
				int x = row + dir[k];
				int y = col + dir[k + 1];
				if (valid(x, y)) {
					long index = 1L * x * cols + y;
					map.merge(index, 1, Integer::sum);
				}
			}
		}
		long[] a = new long[5];
		a[0] = (long) (rows - 1) * (cols - 1);
		for (int count : map.values()) {
			a[count]++;
			a[0]--;
		}
		return a;
	}

	private boolean valid(int x, int y) {
		return x >= 0 && x < rows - 1 && y >= 0 && y < cols - 1;
	}

	public static void main(String[] agrs) {
		CountBlackBlocks cbb = new CountBlackBlocks();
		int[][] g1 = { { 0, 0 } };
		Print.print(cbb.count(3, 3, g1)); // 3 1 0 0 0
		int[][] g2 = { { 0, 0 }, { 1, 1 }, { 0, 2 } };
		Print.print(cbb.count(3, 3, g2)); // 0 2 2 0 0
	}
}
