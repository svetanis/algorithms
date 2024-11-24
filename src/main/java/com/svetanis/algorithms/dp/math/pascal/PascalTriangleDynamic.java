package com.svetanis.algorithms.dp.math.pascal;

import com.svetanis.java.base.utils.Print;

// 118. Pascal's Triangle

public final class PascalTriangleDynamic {
	// Time complexity: O(n^2)
	// Auxilary space: O(n^2)

	public static int[][] pascal(int n) {
		int[][] dp = new int[n][n];
		for (int row = 0; row < n; ++row) {
			// every row has number of
			// integers equal to row number
			for (int col = 0; col <= row; ++col) {
				// first and last values
				// in every row are 1
				if (col == 0 || col == row) {
					dp[row][col] = 1;
				} else {
					// other values are sum of values
					// just above and left of above
					dp[row][col] = dp[row - 1][col] + dp[row - 1][col - 1];
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		int n = 7;
		Print.print(pascal(n));
	}
}
