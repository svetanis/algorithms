package com.svetanis.algorithms.dp.knight;

// 688. Knight Probability in Chessboard

public final class KnightProbabilityInChessboardBottomUp {
	// Time Complexity: O(k * n^2)
	// Space Complexity: O(k * n^2)

	private static int[] direction = { -2, -1, 2, 1, -2, 1, 2, -1, -2 };

	public static double knightProbability(int n, int k, int row, int col) {
		double[][][] dp = init(n, k);
		for (int step = 1; step <= k; step++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int move = 0; move < 8; move++) {
						int x = i + direction[move];
						int y = j + direction[move + 1];
						if (valid(n, x, y)) {
							dp[step][i][j] += dp[step - 1][x][y] / 8.0;
						}
					}
				}
			}
		}
		return dp[k][row][col];
	}

	private static boolean valid(int n, int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	private static double[][][] init(int n, int k) {
		double[][][] dp = new double[k + 1][n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dp[0][i][j] = 1;
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		System.out.println(knightProbability(3, 2, 0, 0)); // 0.0625
		System.out.println(knightProbability(1, 0, 0, 0)); // 1.0
	}
}
