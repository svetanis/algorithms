package com.svetanis.algorithms.dp.grid;

import java.util.Arrays;

// 931. Minimum Falling Path Sum

public final class MinFallingPathSumSubmit {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int mps(int[][] grid) {
		int n = grid.length;
		int[] curr = new int[n];
		for (int c = 0; c < n; c++) {
			curr[c] = grid[0][c];
		}
		for (int r = 1; r < n; r++) {
			int[] next = curr.clone();
			for (int c = 0; c < n; c++) {
				int min = curr[c];
				if (c > 0) {
					min = Math.min(min, curr[c - 1]);
				}
				if (c + 1 < n) {
					min = Math.min(min, curr[c + 1]);
				}
				next[c] = min + grid[r][c];
			}
			curr = next;
		}
		return Arrays.stream(curr).min().getAsInt();
	}

	public static void main(String[] args) {
		int[][] g1 = { { 2, 1, 3 }, { 6, 5, 4 }, { 7, 8, 9 } };
		int[][] g2 = { { -19, 57 }, { -40, -5 } };
		System.out.println(mps(g1)); // 13
		System.out.println(mps(g2)); // -59
	}
}
