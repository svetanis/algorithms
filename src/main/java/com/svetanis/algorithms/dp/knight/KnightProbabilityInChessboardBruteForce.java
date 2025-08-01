package com.svetanis.algorithms.dp.knight;

// 688. Knight Probability in Chessboard

public final class KnightProbabilityInChessboardBruteForce {
	// Time Complexity: O(8^k)
	// Space Complexity: O(k)

	private static int[] direction = { -2, -1, 2, 1, -2, 1, 2, -1, -2 };

	public static double knightProbability(int n, int k, int row, int col) {
		return dfs(n, k, row, col) / Math.pow(8, k);
	}

	private static int dfs(int n, int k, int row, int col) {
		if (!valid(n, row, col)) {
			return 0;
		}
		if (k == 0) {
			return 1;
		}
		int ways = 0;
		for (int move = 0; move < 8; move++) {
			int x = row + direction[move];
			int y = col + direction[move + 1];
			ways += dfs(n, k - 1, x, y);
		}
		return ways;
	}

	private static boolean valid(int n, int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	public static void main(String[] args) {
		System.out.println(knightProbability(3, 2, 0, 0)); // 0.0625
		System.out.println(knightProbability(1, 0, 0, 0)); // 1.0
	}
}
