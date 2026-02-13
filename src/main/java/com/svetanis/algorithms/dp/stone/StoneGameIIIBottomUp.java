package com.svetanis.algorithms.dp.stone;

// 1406. Stone Game III

public final class StoneGameIIIBottomUp {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String stoneGame(int[] stones) {
		int n = stones.length;
		int[] dp = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			dp[i] = Integer.MIN_VALUE;
			int sum = 0;
			for (int move = i; move < Math.min(i + 3, n); move++) {
				sum += stones[move];
				dp[i] = Math.max(dp[i], sum - dp[move + 1]);
			}
		}
		if (dp[0] == 0) {
			return "Tie";
		}
		return dp[0] > 0 ? "Alice" : "Bob";
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
