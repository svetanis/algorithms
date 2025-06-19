package com.svetanis.algorithms.dp.coins;

// CSES: Coin Combinations I

public final class CoinCombinations {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	private static final int MOD = 1000007;

	public static long coinChange(int[] coins, int amount) {
		long[] dp = new long[amount + 1];
		dp[0] = 1;
		// iterate over all possible sums from 1 to amount
		for (int sum = 1; sum <= amount; sum++) {
			// iterate over all coins
			for (int coin : coins) {
				if (coin <= sum) {
					dp[sum] = (dp[sum] + dp[sum - coin]) % MOD;
				}
			}
		}
		return dp[amount] % MOD;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 5 };
		int[] a2 = { 1, 2 };
		System.out.println(coinChange(a1, 9)); // 8
		System.out.println(coinChange(a2, 3)); // 3
	}
}
