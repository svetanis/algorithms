package com.svetanis.algorithms.prefixsum;

// 2428. Maximum Sum of an Hourglass

public final class MaxSumHourglass {
	// Time complexity: O(n * m)
	// Space complexity: O(1)

	public static int maxSum(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int maxSum = 0;
		
		for (int crow = 1; crow < rows - 1; crow++) {
			for (int ccol = 1; ccol < cols - 1; ccol++) {
				int sum = 0;
				for (int row = crow - 1; row <= crow + 1; row++) {
					for (int col = ccol - 1; col <= ccol + 1; col++) {
						sum += grid[row][col];
					}
				}
				sum -= (grid[crow][ccol - 1] + grid[crow][ccol + 1]);
				maxSum = Math.max(maxSum, sum);
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
