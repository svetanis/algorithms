package com.svetanis.algorithms.dp.coins;

// 518. Coin Change II
// CSES: Coin Combinations II

public final class CoinChangeSubmit {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	public static int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int coin : coins) {
			for (int sum = coin; sum <= amount; sum++) {
				dp[sum] += dp[sum - coin];
			}
		}
		return dp[amount];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 5 };
		int[] a2 = { 2 };
		int[] a3 = { 10 };
		System.out.println(coinChange(a1, 5)); // 4
		System.out.println(coinChange(a2, 3)); // 0
		System.out.println(coinChange(a3, 10)); // 1
	}
}
