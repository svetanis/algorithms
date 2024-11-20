package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 1727. Largest Submatrix With Rearrangements

public final class LargestSubmatrixWithRearrangements {
	// Time Complexity: O(m * n * log n)
	// Space Complexity: O(1)

	public static int largestSubmatrix(int[][] matrix) {
		int m = matrix[0].length;
		histogram(matrix);
		int max = 0;
		for (int[] row : matrix) {
			Arrays.sort(row);
			int width = 1;
			for (int j = m - 1; j >= 0; j--) {
				int area = row[j] * width++;
				max = Math.max(max, area);
			}
		}
		return max;
	}

	private static void histogram(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] != 0) {
					matrix[i][j] += matrix[i - 1][j];
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] m1 = { { 0, 0, 1 }, { 1, 1, 1 }, { 1, 0, 1 } };
		int[][] m2 = { { 1, 0, 1, 0, 1 } };
		int[][] m3 = { { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(largestSubmatrix(m1)); // 4
		System.out.println(largestSubmatrix(m2)); // 3
		System.out.println(largestSubmatrix(m3)); // 2
	}
}