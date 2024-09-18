package com.svetanis.algorithms.dp.grid;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// given m x n matrix filled with
// non-negative integers, find a
// path from top left corner to 
// bottom right corner which 
// minimizes the sum of all numbers
// along its path
// movements can only be either down 
// or right at any point in time

// let dp[r][c] be the min path sum 
// to reach cell (r, c)
// 
// dp[r][c] = grid[r][c] + min(dp[r - 1][c], dp[r][c - 1])
// where (r - 1, c) is the cell on the top
// and (r, c - 1) is the cell at the left

public final class MinPathSumBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int mps(List<List<Integer>> grid) {
		int m = grid.size();
		int n = grid.get(0).size();
		int[][] dp = init(grid);
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < n; c++) {
				int left = dp[r][c - 1];
				int top = dp[r - 1][c];
				int val = grid.get(r).get(c);
				dp[r][c] = val + min(left, top);
			}
		}
		return dp[m - 1][n - 1];
	}

	private static int[][] init(List<List<Integer>> grid) {
		int m = grid.size();
		int n = grid.get(0).size();
		// initialize dp grid and
		int[][] dp = new int[m][n];
		// base case at dp[0][0]
		dp[0][0] = grid.get(0).get(0);
		// min path sum for the first row
		for (int c = 1; c < n; c++) {
			dp[0][c] = dp[0][c - 1] + grid.get(0).get(c);
		}
		// min path sum for the first column
		for (int r = 1; r < m; r++) {
			dp[r][0] = dp[r - 1][0] + grid.get(r).get(0);
		}
		return dp;
	}

	public static void main(String[] args) {
		// m rows and n columns
		List<List<Integer>> grid = newArrayList();
		grid.add(asList(1, 3, 1));
		grid.add(asList(1, 5, 1));
		grid.add(asList(4, 2, 1));
		System.out.println(mps(grid)); // 7: 1->3->1->1->1
	}
}
