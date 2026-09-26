package com.svetanis.algorithms.matrix;

// 2352. Equal Row and Column Pairs

// THE DIRECT COMPARISON, and on random input it is much better
// than n^3 suggests. the inner loop breaks at the FIRST
// mismatching element, which on random input is element 1 --
// so it runs in about n^2. it reaches n^3 whenever rows and
// columns share a long common start: a grid of identical
// values is one such grid, and distinct rows such as
// [1, 1, ..., 1, i] are another.

// it comfortably passes LC 2352 (n <= 200), and it beats a
// hash-based version keyed on Arrays.toString.
// CountRowAndColPairs2352Optimized.java keys on Arrays.hashCode
// instead. it is faster on random input, but rows chosen so
// their hashes collide make it n^3 as well, and then slower
// than this file.

public final class CountRowAndColPairs2352 {
	// Time Complexity: O(n^3) -- three nested loops: every row against
	// every column, comparing n elements. Hashing the rows and looking up
	// each column is the O(n^2) version.
	// Space Complexity: O(n^2)

	public static int countPairs(int[][] grid) {
		int n = grid.length;
		int[][] transposed = transposed(grid);
		int count = 0;
		for (int[] row : grid) {
			for (int[] col : transposed) {
				int pair = 1;
				for (int i = 0; i < n; i++) {
					if (row[i] != col[i]) {
						pair = 0;
						break;
					}
				}
				count += pair;
			}
		}
		return count;
	}

	private static int[][] transposed(int[][] grid) {
		int n = grid.length;
		int[][] matrix = new int[n][n];
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				matrix[i][j] = grid[j][i];
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