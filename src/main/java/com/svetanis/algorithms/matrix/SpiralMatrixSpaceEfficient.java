package com.svetanis.algorithms.matrix;

import java.util.ArrayList;
import java.util.List;

// 54. Spiral Matrix

public final class SpiralMatrixSpaceEfficient {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static List<Integer> spiral(int[][] grid) {
		int top = 0;
		int left = 0;
		int bottom = grid.length - 1;
		int right = grid[0].length - 1;

		List<Integer> list = new ArrayList<>();
		while (top <= bottom && left <= right) {
			// move from left to right along the top boundary
			for (int i = left; i <= right; i++) {
				list.add(grid[top][i]);
			}
			top += 1;
			// move from top to bottom along the right boundary
			for (int i = top; i <= bottom; i++) {
				list.add(grid[i][right]);
			}
			right -= 1;
			// move from right to left along the bottom boundary
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					list.add(grid[bottom][i]);
				}
				bottom -= 1;
			}
			// move from bottom to top along the left boundary
			if (left <= right) {
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
		System.out.println(spiral(g1)); // 1,2,3,6,9,8,7,4,5
		int[][] g2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		System.out.println(spiral(g2)); // 1,2,3,4,8,12,11,10,9,5,6,7
	}
}