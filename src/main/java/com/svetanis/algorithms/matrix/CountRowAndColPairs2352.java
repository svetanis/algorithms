package com.svetanis.algorithms.matrix;

// 2352. Equal Row and Column Pairs
//
// Input: grid -- an n x n grid of integers. Returns the number of pairs (r, c) such that row r and
// column c, the column read top to bottom, hold the same values in the same order. On LeetCode:
// 1 <= n <= 200, 1 <= grid[i][j] <= 10^5.
//
// The one idea: one pair is one row set against one column, and it counts when every position
// agrees. A copy of the grid with rows and columns swapped (the transpose: its row j is the
// grid's column j) turns each column into a plain array, so each pair is two arrays compared
// position by position. The comparison stops at the first position that differs; two unrelated
// rows usually differ at position 0, so on random input the n * n pairs cost about n * n steps.
//
// Time: O(n^3) -- n * n pairs, each up to n comparisons. It reaches n^3 whenever rows and columns
// share a long common start: a grid of one repeated value, or distinct rows such as
// [1, 1, ..., 1, i], where every row agrees with n - 1 columns until the last position.
// Space: O(n^2) -- the transposed copy.
//
// Siblings: CountRowAndColPairs2352Optimized counts the rows in a map keyed on Arrays.hashCode
// and looks each column up -- O(n^2) on average, O(n^3) when many rows share one hash, O(n) space
// and no transposed copy.

public final class CountRowAndColPairs2352 {

	public static int countPairs(int[][] grid) {
		int n = grid.length;
		int[][] transposed = transposed(grid); // COLUMNS as rows
		int count = 0;
		for (int[] row : grid) {
			for (int[] col : transposed) { // EVERY row against every column
				int pair = 1;
				for (int i = 0; i < n; i++) {
					if (row[i] != col[i]) {
						pair = 0;
						break; // STOP at the first position that differs
					}
				}
				count += pair; // COUNT the pair if every position agreed
			}
		}
		return count;
	}

	private static int[][] transposed(int[][] grid) {
		int n = grid.length;
		int[][] matrix = new int[n][n];
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				matrix[i][j] = grid[j][i]; // SWAP: row i of the copy is column i of the grid
			}
		}
		return matrix;
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 3, 2, 1 }, { 1, 7, 6 }, { 2, 7, 7 } };
		System.out.println(countPairs(grid1)); // 1
		int[][] grid2 = { { 3, 1, 2, 2 }, { 1, 4, 4, 5 }, { 2, 4, 2, 2 }, { 2, 4, 2, 2 } };
		System.out.println(countPairs(grid2)); // 3
	}
}