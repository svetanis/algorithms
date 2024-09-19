package com.svetanis.algorithms.dp.grid;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// given a binary matrix
// find out the largest size
// square sub-matrix with all
// 1's and return its area

// let dp[r][c] be the max square 
// of all 1s whose bottom-right 
// corner is at cell (r,c)
// dp[r][c] = 1 + min(dp[r-1][c],dp[r][c-1],dp[r-1][c-1])

public final class MaximalSquare {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int mss(List<List<Integer>> grid) {
		int m = grid.size();
		int n = grid.get(0).size();
		int max = MIN_VALUE;
		// initialize dp grid and
		int[][] dp = new int[m][n];
		for (int r = 0; r < m; r++) {
			dp[r][0] = grid.get(r).get(0);
			max = max(max, dp[r][0]);
		}
		for (int c = 0; c < n; c++) {
			dp[0][c] = grid.get(0).get(c);
			max = max(max, dp[0][c]);
		}
		for (int r = 1; r < grid.size(); r++) {
			for (int c = 1; c < grid.get(0).size(); c++) {
				int curr = grid.get(r).get(c);
				if (curr == 1) {
					int top = dp[r - 1][c];
					int left = dp[r][c - 1];
					int diag = dp[r - 1][c - 1];
					dp[r][c] = curr + min(min(top, left), diag);
					max = max(max, dp[r][c]);
				} else {
					dp[r][c] = 0;
				}
			}
		}
		return max * max;
	}

	public static void main(String[] args) {
		List<List<Integer>> grid = newArrayList();
		grid.add(asList(1, 0, 1, 0, 0));
		grid.add(asList(1, 0, 1, 1, 1));
		grid.add(asList(1, 1, 1, 1, 0));
		grid.add(asList(1, 0, 0, 1, 0));
		System.out.println(mss(grid)); // 4: 2x2 matrix

		List<List<Integer>> grid2 = newArrayList();
		grid2.add(asList(1, 1, 0));
		grid2.add(asList(0, 0, 0));
		grid2.add(asList(1, 0, 0));
		System.out.println(mss(grid2)); // 1: 1x1 matrix

	}
}
