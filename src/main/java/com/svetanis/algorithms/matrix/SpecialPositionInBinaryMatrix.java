package com.svetanis.algorithms.matrix;

// 1582. Special Positions in a Binary Matrix
//
// Input: a matrix of rows x cols cells, each 0 or 1. Position (i, j) is special if it holds 1 and
// every other cell in row i and in column j holds 0. Returns the number of special positions. On
// LeetCode: 1 <= rows, cols <= 100, matrix[i][j] is 0 or 1.
//
// The one idea: a cell holding 1 is special exactly when its row holds one 1 and its column holds
// one 1 -- both the cell itself. So a first pass counts the 1s of every row and every column, and a
// second pass counts the 1s whose row count and column count are both 1.
//
// Time: O(rows * cols) -- two passes over the cells.
// Space: O(rows + cols) -- one count per row and per column.

public final class SpecialPositionInBinaryMatrix {

	public static int countSpecial(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[] rowCount = new int[rows]; // COUNT: the 1s in each row
		int[] colCount = new int[cols]; // COUNT: the 1s in each column
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 1) {
					rowCount[i]++;
					colCount[j]++;
				}
			}
		}
		int count = 0;
		for (int row = 0; row < rows; row++) { // WALK: every cell again
			for (int col = 0; col < cols; col++) {
				int curr = matrix[row][col];
				if (curr == 1 && rowCount[row] == 1 && 1 == colCount[col]) { // SPECIAL: the only 1 in both
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] agrs) {
		int[][] g1 = { { 1, 0, 0 }, { 0, 0, 1 }, { 1, 0, 0 } };
		System.out.println(countSpecial(g1)); // 1

		int[][] g2 = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		System.out.println(countSpecial(g2)); // 3
	}
}
