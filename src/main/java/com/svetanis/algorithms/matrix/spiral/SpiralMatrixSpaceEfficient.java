package com.svetanis.algorithms.matrix.spiral;

import java.util.ArrayList;
import java.util.List;

// 54. Spiral Matrix
//
// Input: grid -- an n x m grid of numbers. Returns every number once, in spiral order: along the
// top row to the right, down the right column, back along the bottom row, up the left column,
// then the same again one ring further in. On LeetCode: 1 <= n, m <= 10,
// -100 <= grid[i][j] <= 100.
//
// The one idea: four numbers -- top, bottom, left, right -- mark the rectangle whose numbers are
// not yet read. One ring is four sides: the top row left to right, then the right column top to
// bottom, then the bottom row right to left, then the left column bottom to top. Each side, once
// read, moves its boundary one step in, so the rectangle shrinks and the next ring starts inside
// it. After the top row and the right column, the rectangle can already be empty -- one row or
// one column was left -- so the bottom row and the left column are read only if top <= bottom
// and left <= right still hold; without those checks that last row or column is read twice.
//
// Time: O(n * m) -- each number is read once.
// Space: O(1) auxiliary -- four int boundaries, no visited[][]; the answer list besides.
//
// Siblings: SpiralMatrix (54) walks the same order with a boolean[n][m] visited array,
// O(n * m) auxiliary -- the only difference between the two. SpiralMatrixII (59) is this walk
// writing 1 .. n^2 instead of reading.

public final class SpiralMatrixSpaceEfficient {

	public static List<Integer> spiral(int[][] grid) {
		int top = 0; // BOUNDS: the rectangle not yet read
		int left = 0;
		int bottom = grid.length - 1;
		int right = grid[0].length - 1;

		List<Integer> list = new ArrayList<>();
		while (top <= bottom && left <= right) { // RING: while the rectangle is not empty
			// move from left to right along the top boundary
			for (int i = left; i <= right; i++) {
				list.add(grid[top][i]);
			}
			top += 1; // SHRINK: the top row is read
			// move from top to bottom along the right boundary
			for (int i = top; i <= bottom; i++) {
				list.add(grid[i][right]);
			}
			right -= 1; // SHRINK: the right column is read
			// move from right to left along the bottom boundary
			if (top <= bottom) { // CHECK: a row is left, or the top row would be read again
				for (int i = right; i >= left; i--) {
					list.add(grid[bottom][i]);
				}
				bottom -= 1;
			}
			// move from bottom to top along the left boundary
			if (left <= right) { // CHECK: a column is left, or the right column would be read again
				for (int i = bottom; i >= top; i--) {
					list.add(grid[i][left]);
				}
				left += 1;
			}
		}

		return list;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(spiral(g1)); // [1, 2, 3, 6, 9, 8, 7, 4, 5]
		int[][] g2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		System.out.println(spiral(g2)); // [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
	}
}