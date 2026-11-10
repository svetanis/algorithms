package com.svetanis.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1380. Lucky Numbers in a Matrix
//
// Input: matrix -- m x n distinct integers. A lucky number is the smallest number in its row and
// the largest number in its column. Returns every lucky number, in any order. On LeetCode:
// 1 <= m, n <= 50, 1 <= matrix[i][j] <= 10^5, all values distinct.
//
// The one idea: one cell holds one number, and whether it is lucky depends on just two other
// numbers -- the minimum of its row and the maximum of its column. So the first pass records the
// minimum of every row and the maximum of every column, and the second pass keeps each number
// that equals both.
//
// Time: O(m * n) -- two passes over the grid.
// Space: O(m + n) -- one minimum per row and one maximum per column, plus the answer.

public final class LuckyNumbers {

	private int rows;
	private int cols;

	public List<Integer> luckyNums(int[][] matrix) {
		this.rows = matrix.length;
		this.cols = matrix[0].length;
		int[] minInRows = new int[rows];
		int[] maxInCols = new int[cols]; // starts at 0: LC 1380 values are >= 1
		Arrays.fill(minInRows, Integer.MAX_VALUE);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				minInRows[i] = Math.min(minInRows[i], matrix[i][j]); // ROW minimum
				maxInCols[j] = Math.max(maxInCols[j], matrix[i][j]); // COLUMN maximum
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int curr = matrix[row][col];
				if (curr == minInRows[row] && curr == maxInCols[col]) { // LUCKY: both at once
					list.add(curr);
				}
			}
		}
		return list;
	}

	public static void main(String[] agrs) {
		LuckyNumbers ism = new LuckyNumbers();
		int[][] g1 = { { 3, 7, 8 }, { 9, 11, 13 }, { 15, 16, 17 } };
		System.out.println(ism.luckyNums(g1)); // [15]

		int[][] g2 = { { 1, 10, 4, 2 }, { 9, 3, 8, 7 }, { 15, 16, 17, 12 } };
		System.out.println(ism.luckyNums(g2)); // [12]

		int[][] g3 = { { 7, 8 }, { 1, 2 } };
		System.out.println(ism.luckyNums(g3)); // [7]
	}
}
