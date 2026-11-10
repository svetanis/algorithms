package com.svetanis.algorithms.matrix;

// 1252. Cells with Odd Values in a Matrix
//
// Input: m, n -- a matrix of m rows and n columns, all zeros at the start; indices[i] = [r, c] --
// add 1 to every cell of row r, then add 1 to every cell of column c. Returns how many cells hold an
// odd value after all the indices are applied. On LeetCode: 1 <= m, n <= 50,
// 1 <= indices.length <= 100, 0 <= r < m, 0 <= c < n.
//
// The one idea: one cell (r, c) ends up holding the number of times its row was named plus the
// number of times its column was named -- nothing else ever touches it. So the matrix is never
// built: row[r] counts the times row r was named, col[c] the times column c was named, and cell
// (r, c) holds row[r] + col[c].
//
// Time: O(m * n + indices.length) -- one pass over indices, then every cell's sum is checked.
// Space: O(m + n) -- one counter per row and per column.

public final class OddCells {

	public static int oddCells(int m, int n, int[][] indices) {
		int[] row = new int[m]; // COUNT: times each row was named
		int[] col = new int[n]; // COUNT: times each column was named
		for (int[] index : indices) {
			row[index[0]] += 1;
			col[index[1]] += 1;
		}
		int odd = 0;
		for (int r = 0; r < m; r++) { // WALK: every cell
			for (int c = 0; c < n; c++) {
				if ((row[r] + col[c]) % 2 != 0) { // CELL: its value is row[r] + col[c]
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
