package com.svetanis.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1380. Lucky Numbers in a Matrix

public final class LuckyNumbers {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n + m)

	private int rows;
	private int cols;

	public List<Integer> luckyNums(int[][] matrix) {
		this.rows = matrix.length;
		this.cols = matrix[0].length;
		int[] minInRows = new int[rows];
		int[] maxInCols = new int[cols];
		Arrays.fill(minInRows, Integer.MAX_VALUE);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				minInRows[i] = Math.min(minInRows[i], matrix[i][j]);
				maxInCols[j] = Math.max(maxInCols[j], matrix[i][j]);
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int curr = matrix[row][col];
				if (curr == minInRows[row] && curr == maxInCols[col]) {
					list.add(curr);
				}
			}
		}
		return list;
	}

	public static void main(String[] agrs) {
		LuckyNumbers ism = new LuckyNumbers();
		int[][] g1 = { { 3, 7, 8 }, { 9, 11, 13 }, { 15, 16, 17 } };
		System.out.println(ism.luckyNums(g1)); // 15

		int[][] g2 = { { 1, 10, 4, 2 }, { 9, 3, 8, 7 }, { 15, 16, 17, 12 } };
		System.out.println(ism.luckyNums(g2)); // 12

		int[][] g3 = { { 7, 8 }, { 1, 2 } };
		System.out.println(ism.luckyNums(g3)); // 7
	}
}
