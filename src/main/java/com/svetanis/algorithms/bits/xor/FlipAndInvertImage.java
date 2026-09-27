package com.svetanis.algorithms.bits.xor;

import static com.svetanis.java.base.utils.Print.print;

// 832. Flipping an Image

// reverse each row, then invert every 0 and 1;
// the two cells that swap are inverted in the same step

public final class FlipAndInvertImage {

	public static int[][] flipAndInvert(int[][] matrix) {
		// Time Complexity: O(r * c)
		// Space Complexity: O(1)
		int r = matrix.length;
		int c = matrix[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < (c + 1) / 2; j++) { // (c + 1) / 2: an odd row's middle cell is inverted too
				swapAndInvert(matrix[i], j, c - 1 - j);
			}
		}
		return matrix;
	}

	public static void swapAndInvert(int[] a, int i, int j) {
		int temp = a[i] ^ 1; // ^ 1 turns 0 into 1 and 1 into 0
		a[i] = a[j] ^ 1;
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 0, 1 }, { 1, 1, 1 }, { 0, 1, 1 } };
		print(flipAndInvert(m1));

		int[][] m2 = { { 1, 1, 0, 0 }, { 1, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 0, 1, 0 } };
		print(flipAndInvert(m2));
	}
}
