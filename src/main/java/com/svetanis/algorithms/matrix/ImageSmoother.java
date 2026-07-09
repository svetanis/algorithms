package com.svetanis.algorithms.matrix;

import com.svetanis.java.base.utils.Print;

// 661. Image Smoother

public final class ImageSmoother {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private int rows;
	private int cols;

	public int[][] smoother(int[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		int[][] image = new int[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				image[row][col] = average(grid, row, col);
			}
		}
		return image;
	}

	private int average(int[][] grid, int row, int col) {
		int sum = 0;
		int count = 0;
		for (int x = row - 1; x <= row + 1; x++) {
			for (int y = col - 1; y <= col + 1; y++) {
				if (isSafe(x, y)) {
					count++;
					sum += grid[x][y];
				}
			}
		}
		return sum / count;
	}

	private boolean isSafe(int row, int col) {
		boolean one = row >= 0 && row < rows; // row number is in range
		boolean two = col >= 0 && col < cols; // col number is in range
		return one && two;
	}

	public static void main(String[] agrs) {
		ImageSmoother ism = new ImageSmoother();
		int[][] g1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		Print.print(ism.smoother(g1)); // [0,0,0], [0,0,0], [0,0,0]

		int[][] g2 = { { 100, 200, 100 }, { 200, 50, 200 }, { 100, 200, 100 } };
		Print.print(ism.smoother(g2)); // [[137,141,137],[141,138,141],[137,141,137]]
	}
}
