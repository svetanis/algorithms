package com.svetanis.algorithms.matrix;

// 1582. Special Positions in a Binary Matrix

public final class SpecialPositionInBinaryMatrix {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n + m)

	public static int countSpecial(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[] rowCount = new int[rows];
		int[] colCount = new int[cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 1) {
					rowCount[i]++;
					colCount[j]++;
				}
			}
		}
		int count = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int curr = matrix[row][col];
				if (curr == 1 && rowCount[row] == 1 && 1 == colCount[col]) {
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
