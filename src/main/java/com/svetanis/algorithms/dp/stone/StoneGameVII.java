package com.svetanis.algorithms.dp.stone;

// 1690. Stone Game VII

public final class StoneGameVII {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int stoneGame(int[] stones) {
		int n = stones.length;
		int[] prefix = prefix(stones);
		Integer[][] dp = new Integer[n][n];
		return dfs(prefix, 0, n - 1, dp);
	}

	private static int dfs(int[] prefix, int start, int end, Integer[][] dp) {
		if (start > end) {
			return 0;
		}
		if (dp[start][end] != null) {
			return dp[start][end];
		}
		int left = prefix[end + 1] - prefix[start + 1] - dfs(prefix, start + 1, end, dp);
		int right = prefix[end] - prefix[start] - dfs(prefix, start, end - 1, dp);
		return dp[start][end] = Math.max(left, right);
	}

	private static int[] prefix(int[] stones) {
		int n = stones.length;
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] + stones[i];
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a = { 5, 3, 1, 4, 2 };
		System.out.println(stoneGame(a)); // 6

		int[] a2 = { 7, 90, 5, 1, 100, 10, 10, 2 };
		System.out.println(stoneGame(a2)); // 122
	}
}
