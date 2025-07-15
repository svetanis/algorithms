package com.svetanis.algorithms.matrix.rotate;

import com.svetanis.java.base.utils.Print;

// 1861. Rotating the Box

public final class RotatingTheBoxOptimized {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static char[][] rotate(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int row = 0; row < n; row++) {
			int cell = m - 1;
			for (int col = m - 1; col >= 0; col--) {
				if (grid[row][col] == '*') {
					cell = col - 1;
				} else if (grid[row][col] == '#') {
					grid[row][col] = '.';
					grid[row][cell--] = '#';
				}
			}
		}
		return rotated(grid);
	}

	private static char[][] rotated(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		char[][] rotated = new char[m][n];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				rotated[col][n - row - 1] = grid[row][col];
			}
		}
		return rotated;
	}

	public static void main(String[] args) {
		// Rotate clockwise
		char m1[][] = { { '#', '.', '#' } };
		Print.print(rotate(m1));

		char m2[][] = { { '#', '.', '*', '.' }, //
				{ '#', '#', '*', '.' } };//
		Print.print(rotate(m2));

		char m3[][] = { { '#', '#', '*', '.', '*', '.' }, //
				{ '#', '#', '#', '*', '.', '.' }, //
				{ '#', '#', '#', '.', '#', '.' } };//
		Print.print(rotate(m3));
	}
}