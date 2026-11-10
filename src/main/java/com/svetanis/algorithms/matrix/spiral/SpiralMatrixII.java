package com.svetanis.algorithms.matrix.spiral;

import java.util.Arrays;

// 59. Spiral Matrix II
//
// Input: n -- the side of a square grid. Returns the n x n grid filled with 1, 2, ..., n^2 in
// spiral order: along the top row to the right, down the right column, back along the bottom
// row, up the left column, then the same again one ring further in. On LeetCode: 1 <= n <= 20.
//
// The one idea: four numbers -- top, bottom, left, right -- mark the square still empty, and
// val is the last number written. One ring is four sides, each written in its direction and
// then moved one step in, so the empty part shrinks and the next ring starts inside it. The
// checks before the bottom row and the left column come from the rectangle version
// (SpiralMatrixSpaceEfficient). On a square they never stop a write: they fail only on the
// last ring of an odd n, a single cell, and there the loops they guard are empty anyway.
//
// Time: O(n^2) -- each cell is written once.
// Space: O(n^2) for the returned grid, O(1) besides it.
//
// Siblings: SpiralMatrixSpaceEfficient (54) is the same walk reading a grid instead of writing
// one; SpiralMatrix (54) reads the same order with a visited[][] array.

public final class SpiralMatrixII {

	public static int[][] spiral(int n) {
		int top = 0; // BOUNDS: the square still empty
		int left = 0;
		int bottom = n - 1;
		int right = n - 1;

		int val = 0; // COUNT: the last number written
		int[][] grid = new int[n][n];
		while (top <= bottom && left <= right) { // RING: while some cell is empty
			// move from left to right along the top boundary
			for (int i = left; i <= right; i++) {
				grid[top][i] = ++val;
			}
			top += 1; // SHRINK: the top row is full
			// move from top to bottom along the right boundary
			for (int i = top; i <= bottom; i++) {
				grid[i][right] = ++val;
			}
			right -= 1; // SHRINK: the right column is full
			// move from right to left along the bottom boundary
			if (top <= bottom) { // CHECK: a row is still empty
				for (int i = right; i >= left; i--) {
					grid[bottom][i] = ++val;
				}
				bottom -= 1;
			}
			// move from bottom to top along the left boundary
			if (left <= right) { // CHECK: a column is still empty
				for (int i = bottom; i >= top; i--) {
					grid[i][left] = ++val;
				}
				left += 1;
			}
		}
		return grid;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(spiral(3))); // [[1, 2, 3], [8, 9, 4], [7, 6, 5]]
		System.out.println(Arrays.deepToString(spiral(1))); // [[1]]
	}
}