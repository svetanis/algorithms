package com.svetanis.algorithms.matrix;

import java.util.Arrays;

// 1727. Largest Submatrix With Rearrangements
//
// Input: matrix -- n rows and m columns of 0s and 1s. Its columns may be put in any order.
// Returns the area of the largest rectangle made only of 1s, under the best order of the columns.
// On LeetCode: 1 <= rows * columns <= 10^5, matrix[i][j] is 0 or 1.
//
// The one idea: after the first pass, one cell holds the number of 1s in an unbroken run going up
// its column and ending at that cell -- the height of a bar of 1s standing on it; a 0 stays 0.
// Pick a row as the bottom edge of the rectangle. A rectangle of height h and width w with that
// bottom edge needs w columns whose bars here are all at least h tall. Columns can be put in any
// order, so the w tallest bars of the row can always stand side by side, and the best rectangle
// of width w has the height of the shortest of them. Sorting the row puts the w tallest bars at
// its end, and the shortest of them is the w-th from the end; trying every w on every row finds
// the largest area. Every height is built before any row is sorted, because sorting a row moves
// its heights away from their columns and the row below would add to the wrong bar.
//
// Time: O(n * m * log m), n = rows, m = columns -- each row of length m is sorted.
// Space: O(1) extra -- but it OVERWRITES the caller's matrix with column heights and reorders
// every row.

public final class LargestSubmatrixWithRearrangements {

	public static int largestSubmatrix(int[][] matrix) {
		int m = matrix[0].length;
		histogram(matrix); // HEIGHTS for every row, before any sort
		int max = 0;
		for (int[] row : matrix) { // EACH row as the bottom edge
			Arrays.sort(row); // SORT: the tallest bars at the end
			int width = 1;
			for (int j = m - 1; j >= 0; j--) { // WIDEN: one more bar, the next tallest
				int area = row[j] * width++; // HEIGHT is the shortest bar so far: row[j]
				max = Math.max(max, area); // RECORD the largest area
			}
		}
		return max;
	}

	private static void histogram(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] != 0) {
					matrix[i][j] += matrix[i - 1][j]; // STACK on the bar above; a 0 breaks it
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] m1 = { { 0, 0, 1 }, { 1, 1, 1 }, { 1, 0, 1 } };
		int[][] m2 = { { 1, 0, 1, 0, 1 } };
		int[][] m3 = { { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(largestSubmatrix(m1)); // 4
		System.out.println(largestSubmatrix(m2)); // 3
		System.out.println(largestSubmatrix(m3)); // 2
	}
}