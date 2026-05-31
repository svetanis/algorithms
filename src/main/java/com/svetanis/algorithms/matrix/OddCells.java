package com.svetanis.algorithms.matrix;

// 1252. Cells with Odd Values in a Matrix

public final class OddCells {
	// Time Complexity: O(n + m + indices.length)
	// Space Complexity: O(n + m)

	public static int oddCells(int m, int n, int[][] indices) {
		int[] row = new int[m];
		int[] col = new int[n];
		for (int[] index : indices) {
			row[index[0]] += 1;
			col[index[1]] += 1;
		}
		int odd = 0;
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if ((row[r] + col[c]) % 2 != 0) {
					odd += 1;
				}
			}
		}
		return odd;
	}

	public static void main(String[] agrs) {
		int[][] m1 = { { 0, 1 }, { 1, 1 } };
		System.out.println(oddCells(2, 3, m1)); // 6

		int[][] m2 = { { 1, 1 }, { 0, 0 } };
		System.out.println(oddCells(2, 2, m2)); // 0
	}
}
