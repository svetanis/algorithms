package com.svetanis.algorithms.dp.grid.alluniquepaths;

import java.util.ArrayList;
import java.util.List;

// CSES: Grid Paths I

public final class AllGridPathsWithObstacleBottomUp {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	private static final int MOD = 1000000007;

	public static long countUniquePaths(List<String> grid) {
		int n = grid.size();
		long[][] dp = new long[n][n];
		for (int r = 0; r < n && grid.get(r).charAt(0) == '.'; r++) {
			dp[r][0] = 1;
		}
		for (int c = 0; c < n && grid.get(0).charAt(c) == '.'; c++) {
			dp[0][c] = 1;
		}
		for (int r = 1; r < n; r++) {
			for (int c = 1; c < n; c++) {
				if (grid.get(r).charAt(c) == '.') {
					dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % MOD;
				}
			}
		}
		return dp[n - 1][n - 1];
	}

	// more compact
	public static long countUniquePaths2(List<String> grid) {
		int n = grid.size();
		long[][] dp = new long[n][n];
		dp[0][0] = grid.get(0).charAt(0) == 1 ? 0 : 1;
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < n; ++c) {
				if (grid.get(r).charAt(c) == '.') {
					long right = r < 1 ? 0 : dp[r - 1][c];
					long down = c < 1 ? 0 : dp[r][c - 1];
					dp[r][c] = (dp[r][c] + right + down) % MOD;
				}
			}
		}
		return dp[n - 1][n - 1];
	}

	public static void main(String[] args) {
		// n rows and n columns
		List<String> grid = new ArrayList<>();
		grid.add("....");
		grid.add(".*..");
		grid.add("...*");
		grid.add("*...");
		System.out.println(countUniquePaths(grid)); // 3
		System.out.println(countUniquePaths2(grid)); // 3

		List<String> grid2 = new ArrayList<>();
		grid2.add("...");
		grid2.add("...");
		grid2.add("..*");
		System.out.println(countUniquePaths(grid2)); // 0
		System.out.println(countUniquePaths2(grid2)); // 0
	}
}
