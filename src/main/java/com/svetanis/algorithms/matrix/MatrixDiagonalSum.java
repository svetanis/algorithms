package com.svetanis.algorithms.matrix;

// 1572. Matrix Diagonal Sum

public final class MatrixDiagonalSum {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public int diagonalSum(int[][] g) {
		int n = g.length;
		int sum = 0;
		for (int row = 0; row < n; row++) {
			int col = n - row - 1;
			sum += g[row][row];
			if (row != col) {
				sum += g[row][col];
			}
		}
		return sum;
	}

	public static void main(String[] agrs) {
		MatrixDiagonalSum cbb = new MatrixDiagonalSum();
		int[][] g1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(cbb.diagonalSum(g1)); // 25
		int[][] g2 = { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } };
		System.out.println(cbb.diagonalSum(g2)); // 8
		int[][] g3 = { { 5 } };
		System.out.println(cbb.diagonalSum(g3)); // 5
		int[][] g4 = { { 7, 3, 1, 9 }, { 3, 4, 6, 9 }, { 6, 9, 6, 6 }, { 9, 5, 8, 5 } };
		System.out.println(cbb.diagonalSum(g4)); // 55
	}
}
