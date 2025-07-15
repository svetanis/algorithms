package com.svetanis.algorithms.matrix.rotate;

import com.svetanis.java.base.utils.Print;

// 1861. Rotating the Box

public final class RotatingTheBox {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static char[][] rotate(char[][] grid) {
		char[][] rotated = rotated(grid);
		int n = rotated.length;
		int m = rotated[0].length;
		for (int col = 0; col < m; col++) {
			int cell = n - 1;
			for (int row = n - 1; row >= 0; row--) {
				if (rotated[row][col] == '*') {
					cell = row - 1;
				} else if (rotated[row][col] == '#') {
					rotated[row][col] = '.';
					rotated[cell--][col] = '#';
				}
			}
		}
		return rotated;
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