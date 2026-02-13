package com.svetanis.algorithms.dp.stone;

// 1406. Stone Game III

public final class StoneGameIIITopDown {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String stoneGame(int[] stones) {
		int n = stones.length;
		Integer[] dp = new Integer[n + 1];
		int score = dfs(stones, 0, dp);
		if (score == 0) {
			return "Tie";
		}
		return score > 0 ? "Alice" : "Bob";
	}

	private static int dfs(int[] stones, int index, Integer[] dp) {
		int n = stones.length;
		if (index >= n) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for (int move = 0; move < 3 && index + move < n; move++) {
			sum += stones[index + move];
			int diff = sum - dfs(stones, index + move + 1, dp);
			max = Math.max(max, diff);
		}
		return dp[index] = max;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 7 };
		System.out.println(stoneGame(a)); // Bob

		int[] a2 = { 1, 2, 3, -9 };
		System.out.println(stoneGame(a2)); // Alice

		int[] a3 = { 1, 2, 3, 6 };
		System.out.println(stoneGame(a3)); // Tie
	}
}
