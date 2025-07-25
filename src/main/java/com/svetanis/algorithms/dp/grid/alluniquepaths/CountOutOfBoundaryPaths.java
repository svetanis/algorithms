package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 576. Out of Boundary Paths

public final class CountOutOfBoundaryPaths {
	// Time Complexity: O(n * m * max)
	// Space Complexity: O(n * m * max)

	private static final int[] dx = { 1, -1, 0, 0 };
	private static final int[] dy = { 0, 0, 1, -1 };
	private static final int MOD = (int) 1e9 + 7;

	public static int count(int m, int n, int max, int row, int col) {
		Integer[][][] dp = new Integer[m][n][max + 1];
		return dfs(m, n, max, row, col, dp);
	}

	private static int dfs(int m, int n, int max, int row, int col, Integer[][][] dp) {
		if (row < 0 || col < 0 || row >= m || col >= n) {
			return 1;
		}
		if (dp[row][col][max] != null) {
			return dp[row][col][max];
		}
		if (max == 0) {
			return 0;
		}
		int count = 0;
		for (int k = 0; k < dx.length; k++) {
			int x = row + dx[k];
			int y = col + dy[k];
			count += dfs(m, n, max - 1, x, y, dp);
			count %= MOD;
		}
		dp[row][col][max] = count;
		return count;
	}

	public static void main(String[] args) {
		System.out.println(count(2, 2, 2, 0, 0)); // 6
		System.out.println(count(1, 3, 3, 0, 1)); // 12
		System.out.println(count(2, 3, 8, 1, 0)); // 1104
	}
}
