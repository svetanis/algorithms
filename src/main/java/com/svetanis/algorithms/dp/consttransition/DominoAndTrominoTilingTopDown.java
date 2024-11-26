package com.svetanis.algorithms.dp.consttransition;

// 2466. Count Ways To Build Good Strings

public final class DominoAndTrominoTilingTopDown {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	private static final int MOD = (int) 1e9 + 7;

	public static int count(int n) {
		Long[][] dp = new Long[n + 1][n + 1];
		return dfs(n, 0, 0, dp);
	}

	private static int dfs(int n, int row, int col, Long[][] dp) {
		if (row > n || col > n) {
			return 0;
		}
		if (row == n && col == n) {
			return 1;
		}
		if (dp[row][col] != null) {
			return dp[row][col].intValue();
		}
		long count = 0;
		if (row > col) {
			count += dfs(n, row, col + 2, dp);
			count += dfs(n, row + 1, col + 2, dp);
		} else if (row < col) {
			count += dfs(n, row + 2, col, dp);
			count += dfs(n, row + 2, col + 1, dp);
		} else {
			count += dfs(n, row + 2, col + 2, dp);
			count += dfs(n, row + 1, col + 1, dp);
			count += dfs(n, row + 2, col + 1, dp);
			count += dfs(n, row + 1, col + 2, dp);
		}
		count %= MOD;
		dp[row][col] = count;
		return dp[row][col].intValue();
	}

	public static void main(String[] args) {
		System.out.println(count(3)); // 5
		System.out.println(count(1)); // 1
	}
}
