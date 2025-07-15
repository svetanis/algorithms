package com.svetanis.algorithms.matrix.rotate;

import java.util.ArrayDeque;
import java.util.Deque;

import com.svetanis.java.base.utils.Print;

// 1861. Rotating the Box

public final class RotatingTheBoxQueue {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static char[][] rotate(char[][] grid) {
		char[][] rotated = rotated(grid);
		int n = rotated.length;
		int m = rotated[0].length;
		for (int col = 0; col < m; col++) {
			Deque<Integer> dq = new ArrayDeque<>();
			for (int row = n - 1; row >= 0; row--) {
				if (rotated[row][col] == '*') {
					dq.clear();
				} else if (rotated[row][col] == '.') {
					dq.offer(row);
				} else if (!dq.isEmpty()) {
					int top = dq.pollFirst();
					rotated[top][col] = '#';
					rotated[row][col] = '.';
					dq.offer(row);
				}
			}
		}
		return rotated;
	}

	public static char[][] rotated(char[][] grid) {
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