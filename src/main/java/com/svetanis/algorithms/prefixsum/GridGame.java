package com.svetanis.algorithms.prefixsum;

// 2017. Grid Game
//
// Input: a grid of 2 rows and n columns of points. Two robots each walk from the top-left cell to
// the bottom-right cell, moving only right or down. The first robot walks, collects the points on
// its path, and leaves those cells at 0; then the second robot walks and collects. The first robot
// wants the second to collect as little as possible; the second wants as much as possible. Returns
// what the second robot collects. On LeetCode: 1 <= n <= 5 * 10^4, 1 <= grid[r][c] <= 10^5.
//
// The one idea: with two rows, a path is set by the one column where it goes down. If the first
// robot goes down at col, it clears the top row up to col and the bottom row from col on. What is
// left is the top row right of col and the bottom row left of col, and the second robot can reach
// only one of the two: staying on top to the end, or going down at once. So it collects the larger
// of the two. tsum holds the top row right of col and bsum the bottom row left of col; the first
// robot tries every col and keeps the smallest of those larger values.
//
// Time: O(n) -- one pass for the top row's total, one pass over the columns
// Space: O(1) -- two running sums and the best value
//
// The sums reach 5 * 10^4 * 10^5 = 5 * 10^9, past the int range, so they are long. The variable
// max holds the smallest value seen: the first robot's best choice.

public final class GridGame {

	public static long gridGame(int[][] grid) {
		long max = Long.MAX_VALUE; // BEST for the first robot: the smallest second-robot total
		long tsum = 0, bsum = 0;
		for (int val : grid[0]) {
			tsum += val; // TOTAL of the top row
		}
		int n = grid[0].length;
		for (int col = 0; col < n; col++) { // DOWN: the first robot goes down at col
			tsum -= grid[0][col]; // TOP: now the top row right of col
			max = Math.min(max, Math.max(tsum, bsum)); // SECOND robot takes the larger side
			bsum += grid[1][col]; // BOTTOM: after the check, the bottom row up to col
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
