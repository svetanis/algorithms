package com.svetanis.algorithms.dp.grid.alluniquepaths;

import java.util.Arrays;

// 62. Unique Paths

public final class RobotOnGridBottomUpOptimized {
	// Time Complexity: O(n * m)
	// Space Complexity: O(m)

	public static int uniquePathsSimple(int n, int m) {
		int[] dp = new int[m];
		Arrays.fill(dp, 1);
		for (int r = 1; r < n; r++) {
			for (int c = 1; c < m; c++) {
				dp[c] += dp[c - 1];
			}
		}
		return dp[m - 1];
	}

	public static int countUniquePaths(int n, int m) {
		int[] prev = new int[m];
		Arrays.fill(prev, 1);
		for (int r = 1; r < n; r++) {
			int[] curr = new int[m];
			Arrays.fill(curr, 1);
			for (int c = 1; c < m; c++) {
				curr[c] = prev[c] + curr[c - 1];
			}
			prev = curr;
		}
		return prev[m - 1];
	}

	public static void main(String[] args) {
		// n rows and m columns
		System.out.println(uniquePathsSimple(3, 2)); // 3
		System.out.println(uniquePathsSimple(7, 3)); // 28
		System.out.println(uniquePathsSimple(1, 1)); // 1
		System.out.println(uniquePathsSimple(10, 5)); // 715

		System.out.println(countUniquePaths(3, 2)); // 3
		System.out.println(countUniquePaths(7, 3)); // 28
		System.out.println(countUniquePaths(1, 1)); // 1
		System.out.println(countUniquePaths(10, 5)); // 715
	}
}
