package com.svetanis.algorithms.prefixsum;

// 2428. Maximum Sum of an Hourglass
//
// Input: a grid of m rows and n columns. An hourglass is seven cells of a 3 x 3 square: its whole
// top row, its centre, and its whole bottom row -- the middle row's left and right cells are left
// out. Returns the largest total of any hourglass that fits inside the grid. On LeetCode:
// 3 <= m, n <= 150, 0 <= grid[i][j] <= 10^6, so a total is at most 7 * 10^6 and fits in an int.
// The best total starts at 0, which relies on no value being negative: a grid of -1s returns 0
// where the answer is -7.
//
// The one idea: an hourglass is named by its centre cell [crow][ccol], which can be any cell not on
// the grid's border. Its total is the 3 x 3 square around the centre minus the two cells beside the
// centre. No prefix sum is needed: every hourglass costs nine reads, whatever the grid's size.
//
// Time: O(m * n) -- (m - 2) * (n - 2) centres, nine reads each.
// Space: O(1) -- a running total and the best.
//
// Siblings: none.

public final class MaxSumHourglass {

	public static int maxSum(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int maxSum = 0; // BEST so far; 0 is safe only because values are never negative

		for (int crow = 1; crow < rows - 1; crow++) { // CENTRE: every cell off the border
			for (int ccol = 1; ccol < cols - 1; ccol++) {
				int sum = 0;
				for (int row = crow - 1; row <= crow + 1; row++) { // SQUARE: the 3 x 3 around the centre
					for (int col = ccol - 1; col <= ccol + 1; col++) {
						sum += grid[row][col];
					}
				}
				sum -= (grid[crow][ccol - 1] + grid[crow][ccol + 1]); // CUT the two cells beside the centre
				maxSum = Math.max(maxSum, sum); // KEEP the best hourglass
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 6, 2, 1, 3 }, { 4, 2, 1, 5 }, //
				{ 9, 2, 8, 7 }, { 4, 1, 2, 9 } };
		System.out.println(maxSum(g1)); // 30

		int[][] g2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(maxSum(g2)); // 35
	}
}
