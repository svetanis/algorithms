package com.svetanis.algorithms.dp.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3148. Maximum Difference Score in a Grid

public final class MaxDiffScore {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final int INF = 1 << 30;

	public static int maxScore(List<List<Integer>> grid) {
		int n = grid.size();
		int m = grid.get(0).size();
		int[][] dp = new int[n][m];
		int max = -INF;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int min = INF;
				if (i > 0) {
					min = Math.min(min, dp[i - 1][j]);
				}
				if (j > 0) {
					min = Math.min(min, dp[i][j - 1]);
				}
				int val = grid.get(i).get(j);
				max = Math.max(max, val - min);
				dp[i][j] = Math.min(min, val);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		List<List<Integer>> g1 = new ArrayList<>();
		g1.add(Arrays.asList(9, 5, 7, 3));
		g1.add(Arrays.asList(8, 9, 6, 1));
		g1.add(Arrays.asList(6, 7, 14, 3));
		g1.add(Arrays.asList(2, 5, 3, 1));
		System.out.println(maxScore(g1)); // 9

		List<List<Integer>> g2 = new ArrayList<>();
		g2.add(Arrays.asList(4, 3, 2));
		g2.add(Arrays.asList(3, 2, 1));
		System.out.println(maxScore(g2)); // -1
	}
}
