package com.svetanis.algorithms.bits.xor;

import static com.svetanis.java.base.utils.Print.print;

// for a given positive number N
// in base 10, find the complement
// of its binary representation 
// as a base 10 integer

public final class FlipAndInvertImage {

	public static int[][] flipAndInvert(int[][] matrix) {
		// Time Complexity: O(n)
		int r = matrix.length;
		int c = matrix[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < (c + 1) / 2; j++) {
				swapAndInvert(matrix[i], j, c - 1 - j);
			}
		}
		return matrix;
	}

	public static void swapAndInvert(int[] a, int i, int j) {
		int temp = a[i] ^ 1;
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
