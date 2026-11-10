package com.svetanis.algorithms.matrix;

// 1572. Matrix Diagonal Sum
//
// Input: g -- a square n x n matrix. Returns the sum of the primary diagonal (top-left to
// bottom-right) and the secondary diagonal (top-right to bottom-left), a cell on both counted
// once. On LeetCode: 1 <= n <= 100, 1 <= mat[i][j] <= 100.
//
// The one idea: each row holds exactly one cell of each diagonal -- row r holds (r, r) on the
// primary and (r, n - 1 - r) on the secondary. Add both. They are the same cell only when
// r == n - 1 - r, the middle row of an odd n, and then it is added once.
//
// Time: O(n) -- two cells per row.
// Space: O(1).

public final class MatrixDiagonalSum {

	public int diagonalSum(int[][] g) {
		int n = g.length;
		int sum = 0;
		for (int row = 0; row < n; row++) {
			int col = n - row - 1; // SECONDARY diagonal's cell in this row
			sum += g[row][row]; // PRIMARY
			if (row != col) { // SKIP the centre the second time
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
