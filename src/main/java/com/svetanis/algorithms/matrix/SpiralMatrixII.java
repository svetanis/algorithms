package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 59. Spiral Matrix II

public final class SpiralMatrixII {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int[][] spiral(int n) {
		int top = 0;
		int left = 0;
		int bottom = n - 1;
		int right = n - 1;

		int val = 0;
		int[][] grid = new int[n][n];
		while (top <= bottom && left <= right) {
			// move from left to right along the top boundary
			for (int i = left; i <= right; i++) {
				grid[top][i] = ++val;
			}
			top += 1;
			// move from top to bottom along the right boundary
			for (int i = top; i <= bottom; i++) {
				grid[i][right] = ++val;
			}
			right -= 1;
			// move from right to left along the bottom boundary
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					grid[bottom][i] = ++val;
				}
				bottom -= 1;
			}
			// move from bottom to top along the left boundary
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					grid[i][left] = ++val;
				}
				left += 1;
			}
		}
		return grid;
	}

	public static void main(String[] args) {
		Print.print(spiral(3));
		Print.print(spiral(1));
	}
}