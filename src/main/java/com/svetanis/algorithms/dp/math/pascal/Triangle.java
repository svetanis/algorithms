package com.svetanis.algorithms.dp.math.pascal;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

import java.util.List;

// given a triangle. find
// the min path sum from
// top to bottom
// each step you may move 
// to adjacent numbers on
// the row below

// let dp[r][c] be the min path sum 
// to reach cell (r, c)
// 
// dp[r][c] = grid[r][c] + min(dp[r - 1][c], dp[r - 1][c - 1])
// where (r - 1, c) is the cell on the top
// and (r - 1, c - 1) is the cell at the top left

public final class Triangle {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static int mps(List<List<Integer>> triangle) {
		int m = triangle.size();
		int[][] dp = init(triangle);
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < triangle.get(r).size(); c++) {
				int top = dp[r - 1][c];
				int left = dp[r - 1][c - 1];
				int val = triangle.get(r).get(c);
				dp[r][c] = val + min(left, top);
			}
		}
		return stream(dp[m - 1]).min().getAsInt();
	}

	private static int[][] init(List<List<Integer>> triangle) {
		int n = triangle.size();
		// initialize dp grid and
		int[][] dp = new int[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				dp[r][c] = MAX_VALUE;
			}
		}
		// base case at dp[0][0]
		dp[0][0] = triangle.get(0).get(0);
		// min path sum for the first column and diagonal
		for (int r = 1; r < n; r++) {
			dp[r][0] = dp[r - 1][0] + triangle.get(r).get(0);
			dp[r][r] = dp[r - 1][r - 1] + triangle.get(r).get(r);
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
