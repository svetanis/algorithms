package com.svetanis.algorithms.search.binary.matrix;

// 1351. Count Negative Numbers in a Sorted Matrix

// count negative numbers in
// colomn-wise/row-wise sorted matrix

public final class CountNegatives {
	// Time Complexity: O(n + m)

	public static int countDescending(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int count = 0;
		int row = 0;
		int col = 0;
		// start with top right corner
		while (valid(row, col, n, m)) {
			if (matrix[row][col] < 0) {
				count += m - col;
				row++;
				col = 0;
			} else {
				if (col == m - 1) {
					row++;
					col = 0;
				} else {
					col++;
				}
			}
		}
		return count;
	}

	public static int countAccending(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int count = 0;
		int row = 0;
		int col = m - 1;
		// start with top right corner
		while (valid(row, col, n, m)) {
			if (matrix[row][col] < 0) {
				count += col + 1;
				row++;
			} else {
				col--;
			}
		}
		return count;
	}

	private static boolean valid(int row, int col, int n, int m) {
		return row >= 0 && row < n && col >= 0 && col < m;
	}

	public static void main(String[] args) {
		int[][] m1 = { { -3, -2, -1, 1 }, { -2, 2, 3, 4 }, { 4, 5, 7, 8 } };
		System.out.println(countAccending(m1));

		int[][] m2 = { { -10, -25, 30, 40 }, { -15, -29, 35, 45 }, { -27, 20, 37, 48 }, { -32, 33, 39, 50 }, };
		System.out.println(countAccending(m2));

		int[][] m3 = { { 5, 4, 2, 1 }, { 3, 2, 1, -1 }, { 2, -1, -2, -3 }, { 1, -2, -3, -5 }, };
		System.out.println(countDescending(m3));

		int[][] m4 = { { 4, 3, 2, -1 }, { 3, 2, 1, -1 }, { 1, 1, -1, -2 }, { -1, -1, -2, -3 } };
		System.out.println(countDescending(m4)); // 8

		int[][] m5 = { { 3, 2 }, { 1, 0 } };
		System.out.println(countDescending(m5)); // 0
	}
}