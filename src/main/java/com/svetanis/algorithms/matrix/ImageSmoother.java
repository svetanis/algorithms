package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 661. Image Smoother
//
// Input: grid -- an m x n grayscale image; a cell holds a value from 0 to 255. Returns a new
// image of the same size where each cell is the average of the same cell and its up to eight
// neighbours that lie on the image, rounded down. On LeetCode: 1 <= m, n <= 200,
// 0 <= img[i][j] <= 255.
//
// The one idea: one cell of the answer holds the rounded-down average of the 3 x 3 square
// centred on the same cell of the input, counting only the cells of that square that lie on the
// image -- 9 in the middle, 6 along an edge, 4 in a corner, fewer when the image is one row or one
// column wide. The answer goes into a new array, so every average reads the original values.
// Integer division rounds down here because every sum is >= 0.
//
// Time: O(m * n) -- nine cells read per cell.
// Space: O(m * n) -- the answer image.

public final class ImageSmoother {

	private int rows;
	private int cols;

	public int[][] smoother(int[][] grid) {
		this.rows = grid.length;
		this.cols = grid[0].length;
		int[][] image = new int[rows][cols]; // NEW image: the input is only read
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				image[row][col] = average(grid, row, col);
			}
		}
		return image;
	}

	private int average(int[][] grid, int row, int col) {
		int sum = 0;
		int count = 0; // CELLS of the 3 x 3 square that lie on the image
		for (int x = row - 1; x <= row + 1; x++) {
			for (int y = col - 1; y <= col + 1; y++) { // SQUARE: the cell and its eight neighbours
				if (isSafe(x, y)) {
					count++;
					sum += grid[x][y];
				}
			}
		}
		return sum / count; // ROUND down: sum >= 0
	}

	private boolean isSafe(int row, int col) {
		boolean one = row >= 0 && row < rows; // row number is in range
		boolean two = col >= 0 && col < cols; // col number is in range
		return one && two;
	}

	public static void main(String[] agrs) {
		ImageSmoother ism = new ImageSmoother();
		int[][] g1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		System.out.println(Arrays.deepToString(ism.smoother(g1))); // [[0, 0, 0], [0, 0, 0], [0, 0, 0]]

		int[][] g2 = { { 100, 200, 100 }, { 200, 50, 200 }, { 100, 200, 100 } };
		System.out.println(Arrays.deepToString(ism.smoother(g2))); // [[137, 141, 137], [141, 138, 141], [137, 141, 137]]
	}
}
