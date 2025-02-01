package com.svetanis.algorithms.dp.grid;

import java.util.Arrays;

// 62. Unique Paths

public final class RobotOnGridBottomUpOptimized {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n)

	public static int uniquePathsSimple(int m, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < n; c++) {
				dp[c] += dp[c - 1];
			}
		}
		return dp[n - 1];
	}

	public static int countUniquePaths(int m, int n) {
		int[] prev = new int[n];
		Arrays.fill(prev, 1);
		for (int r = 1; r < m; r++) {
			int[] curr = new int[n];
			Arrays.fill(curr, 1);
			for (int c = 1; c < n; c++) {
				curr[c] = prev[c] + curr[c - 1];
			}
			prev = curr;
		}
		return prev[n - 1];
	}

	public static void main(String[] args) {
		// m rows and n columns
		System.out.println(uniquePathsSimple(3, 2)); // 3
		System.out.println(uniquePathsSimple(7, 3)); // 28
		System.out.println(uniquePathsSimple(1, 1)); // 1
		System.out.println(uniquePathsSimple(10, 5)); // 715
	}
}
