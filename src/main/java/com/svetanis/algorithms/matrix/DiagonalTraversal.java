package com.svetanis.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 498. Diagonal Traverse
//
// Input: grid -- an m x n matrix. Returns all its elements in zigzag diagonal order: starting at
// the top-left corner, the first diagonal is read going up and to the right, the next going down
// and to the left, and so on, ending at the bottom-right corner. On LeetCode: 1 <= m, n <= 10^4,
// 1 <= m * n <= 10^4, -10^5 <= mat[i][j] <= 10^5.
//
// The one idea: one diagonal is the set of cells whose row + col is the same number d, from d = 0
// (the top-left corner) to d = rows + cols - 2 (the bottom-right corner). Every diagonal is first
// collected the same way, from its top-right end to its bottom-left end: it starts in row 0 at
// column d while d < cols, and after that in the last column, at row d - cols + 1; each step goes
// down one row and left one column. That is the order the odd diagonals want. The even diagonals
// go up and to the right, the other way, so an even diagonal is reversed before it is copied out.
//
// Time: O(m * n) -- each cell is collected once and copied once; reversing a diagonal costs its
// length.
// Space: O(min(m, n)) beyond the answer -- the list holds one diagonal, at most min(m, n) cells;
// the answer array holds m * n.

public final class DiagonalTraversal {

	public static int[] diagonal(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int index = 0;
		int[] a = new int[rows * cols];
		List<Integer> list = new ArrayList<>(); // ONE diagonal, top-right end first
		for (int diagonal = 0; diagonal < rows + cols - 1; diagonal++) { // EACH d = row + col
			int row = diagonal < cols ? 0 : diagonal - cols + 1; // START: top row, or the last column
			int col = diagonal < cols ? diagonal : cols - 1;
			while (row < rows && col >= 0) {
				list.add(grid[row][col]);
				row++; // STEP down and left
				col--;
			}
			if (diagonal % 2 == 0) {
				Collections.reverse(list); // EVEN: read up and to the right instead
			}
			for (int element : list) {
				a[index++] = element; // COPY out in zigzag order
			}
			list.clear();
		}
		return a;
	}

	public static void main(String[] args) {
		int[][] m1 = { //
				{ 1, 2, 3 }, //
				{ 4, 5, 6 }, //
				{ 7, 8, 9 } //
		};//
		System.out.println(Arrays.toString(diagonal(m1))); // [1, 2, 4, 7, 5, 3, 6, 8, 9]

		int[][] m2 = { //
				{ 1, 2 }, //
				{ 3, 4 } //
		};//
		System.out.println(Arrays.toString(diagonal(m2))); // [1, 2, 3, 4]
	}
}
