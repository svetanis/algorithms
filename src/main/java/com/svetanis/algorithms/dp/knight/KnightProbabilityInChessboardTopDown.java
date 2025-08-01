package com.svetanis.algorithms.dp.knight;

// 688. Knight Probability in Chessboard

public final class KnightProbabilityInChessboardTopDown {
	// Time Complexity: O(n^2 * k)
	// Space Complexity: O(n^2 * k)

	private static int[] direction = { -2, -1, 2, 1, -2, 1, 2, -1, -2 };

	public static double knightProbability(int n, int k, int row, int col) {
		double[][][] dp = new double[n][n][k + 1];
		return dfs(n, k, row, col, dp);
	}

	private static double dfs(int n, int k, int row, int col, double[][][] dp) {
		if (k == 0) {
			return 1;
		}
		if (dp[row][col][k] != 0) {
			return dp[row][col][k];
		}
		double probability = 0;
		for (int move = 0; move < 8; move++) {
			int x = row + direction[move];
			int y = col + direction[move + 1];
			if (valid(n, x, y)) {
				probability += dfs(n, k - 1, x, y, dp);
			}
		}
		return dp[row][col][k] = probability / 8;
	}

	private static boolean valid(int n, int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	public static void main(String[] args) {
		System.out.println(knightProbability(3, 2, 0, 0)); // 0.0625
		System.out.println(knightProbability(1, 0, 0, 0)); // 1.0
	}
}
