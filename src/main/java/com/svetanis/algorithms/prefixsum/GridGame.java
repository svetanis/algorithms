package com.svetanis.algorithms.prefixsum;

// 2017. Grid Game

public final class GridGame {

	public static long gridGame(int[][] grid) {
		long max = Long.MAX_VALUE;
		long tsum = 0, bsum = 0;
		for (int val : grid[0]) {
			tsum += val;
		}
		int n = grid[0].length;
		for (int col = 0; col < n; col++) {
			tsum -= grid[0][col];
			max = Math.min(max, Math.max(tsum, bsum));
			bsum += grid[1][col];
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 2, 5, 4 }, { 1, 5, 1 } };
		System.out.println(gridGame(g1)); // 4
		int[][] g2 = { { 3, 3, 1 }, { 8, 5, 2 } };
		System.out.println(gridGame(g2)); // 4
		int[][] g3 = { { 1, 3, 1, 15 }, { 1, 3, 3, 1 } };
		System.out.println(gridGame(g3)); // 7
	}
}
