package com.svetanis.algorithms.dp.math.pascal;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// 120. Triangle

// given a triangle. find
// the min path sum from
// top to bottom
// each step you may move 
// to adjacent numbers on
// the row below

// bottom-up: start from the smallest solution
// start from the last row and build our way up
// to the first row.

// let dp[r][c] be the min path sum 
// starting from r-th row and c-th column
// 
// dp[r][c] = grid[r][c] + min(dp[r - 1][c], dp[r - 1][c - 1])
// where (r - 1, c) is the cell on the top
// and (r - 1, c - 1) is the cell at the top left

public final class TriangleBottomUpSpaceOptimized {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int minTotal(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[] dp = new int[n];
		for (int col = 0; col < n; col++) {
			dp[col] = triangle.get(n - 1).get(col);
		}
		for (int row = n - 2; row >= 0; row--) {
			for (int col = 0; col <= row; col++) {
				int top = dp[col];
				int left = dp[col + 1];
				int val = triangle.get(row).get(col);
				dp[col] = val + Math.min(top, left);
			}
		}
		return dp[0];
	}

	public static int mps(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[][] dp = init(triangle);
		// start with the second last row
		// and build up
		for (int row = n - 2; row >= 0; row--) {
			for (int col = 0; col <= row; col++) {
				int top = dp[1][col];
				int left = dp[1][col + 1];
				int val = triangle.get(row).get(col);
				dp[0][col] = val + min(left, top);
			}
			// swap rows
			for (int c = 0; c <= row; c++) {
				dp[1][c] = dp[0][c];
			}
		}
		return dp[1][0];
	}

	private static int[][] init(List<List<Integer>> triangle) {
		int n = triangle.size();
		// initialize dp grid
		int[][] dp = new int[2][n];
		// and the last row
		for (int col = 0; col < n; col++) {
			dp[1][col] = triangle.get(n - 1).get(col);
		}
		return dp;
	}

	public static void main(String[] args) {
		List<List<Integer>> grid = newArrayList();
		grid.add(asList(2));
		grid.add(asList(3, 4));
		grid.add(asList(6, 5, 7));
		grid.add(asList(4, 1, 8, 3));
		System.out.println(mps(grid)); // 11: 2->3->5->1
	}
}
