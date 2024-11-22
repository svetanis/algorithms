package com.svetanis.algorithms.dp.stone;

// 1140. Stone Game II

public final class StoneGameII {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n^2)

	public static int stoneGame(int[] piles) {
		int n = piles.length;
		int[] prefix = prefix(piles);
		Integer[][] dp = new Integer[n][n + 1];
		return dfs(prefix, 0, 1, dp);
	}

	private static int dfs(int[] prefix, int index, int m, Integer[][] dp) {
		int n = prefix.length - 1;
		int sum = prefix[n] - prefix[index];
		if (2 * m >= n - index) {
			return sum;
		}
		if (dp[index][m] != null) {
			return dp[index][m];
		}
		int max = 0;
		for (int x = 1; x <= m * 2; x++) {
			int next = Math.max(m, x);
			int diff = sum - dfs(prefix, index + x, next, dp);
			max = Math.max(max, diff);
		}
		return dp[index][m] = max;
	}

	private static int[] prefix(int[] piles) {
		int[] a = new int[piles.length + 1];
		for (int i = 0; i < piles.length; i++) {
			a[i + 1] = a[i] + piles[i];
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a = { 2, 7, 9, 4, 4 };
		System.out.println(stoneGame(a)); // 10

		int[] a2 = { 1, 2, 3, 4, 5, 100 };
		System.out.println(stoneGame(a2)); // 104
	}
}
