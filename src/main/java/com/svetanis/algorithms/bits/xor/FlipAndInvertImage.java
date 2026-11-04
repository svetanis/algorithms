package com.svetanis.algorithms.bits.xor;

import java.util.Arrays;

// 832. Flipping an Image
//
// Input: an n x n matrix of 0s and 1s. Returns the same matrix with every row reversed and then
// every cell inverted (0 becomes 1, 1 becomes 0); the input matrix is changed in place. On
// LeetCode: 1 <= n <= 20.
//
// The one idea: after the two steps, cell j of a row holds the old cell c - 1 - j, inverted. So
// walk j over the first half of the row and do both steps at once: swap cell j with cell c - 1 - j
// and invert both. ^ 1 inverts, because 0 ^ 1 = 1 and 1 ^ 1 = 0. A row of odd length has a middle
// cell that pairs with itself; the loop runs to (c + 1) / 2 to include it, and swapping it with
// itself and inverting it once is exactly what it needs.
//
// Time: O(r * c) -- each cell is touched once.
// Space: O(1) -- the matrix is changed in place.

public final class FlipAndInvertImage {

	public static int[][] flipAndInvert(int[][] matrix) {
		// Time Complexity: O(r * c)
		// Space Complexity: O(1)
		int r = matrix.length;
		int c = matrix[0].length;
		for (int i = 0; i < r; i++) { // WALK the rows
			for (int j = 0; j < (c + 1) / 2; j++) { // HALF: (c + 1) / 2, an odd row's middle cell is inverted too
				swapAndInvert(matrix[i], j, c - 1 - j); // PAIR: cell j with its mirror
			}
		}
		return matrix;
	}

	public static void swapAndInvert(int[] a, int i, int j) {
		int temp = a[i] ^ 1; // INVERT: ^ 1 turns 0 into 1 and 1 into 0
		a[i] = a[j] ^ 1; // SWAP, inverted
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 1, 0, 1 }, { 1, 1, 1 }, { 0, 1, 1 } };
		System.out.println(Arrays.deepToString(flipAndInvert(m1))); // [[0, 1, 0], [0, 0, 0], [0, 0, 1]]

		int[][] m2 = { { 1, 1, 0, 0 }, { 1, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 0, 1, 0 } };
		System.out.println(Arrays.deepToString(flipAndInvert(m2))); // [[1, 1, 0, 0], [0, 1, 1, 0], [0, 0, 0, 1], [1, 0, 1, 0]]

		int[][] m3 = { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 0, 0 } };
		System.out.println(Arrays.deepToString(flipAndInvert(m3))); // [[1, 0, 0], [0, 1, 0], [1, 1, 1]]
	}
}
