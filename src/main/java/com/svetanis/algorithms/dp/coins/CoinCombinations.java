package com.svetanis.algorithms.dp.coins;

// CSES: Coin Combinations I

// counts ORDERED sequences, not combinations -- 1+2 and 2+1 are two ways,
// which is LC 377 (Combination Sum IV). The sum-outer / coin-inner nesting
// below is the whole reason: swap the two loops and you get
// CoinChangeSubmit.java, which counts unordered combinations.
// {2,3,5} amount 9 -> 8 here, 3 there. Same arithmetic, different question.

public final class CoinCombinations {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	// was 1000007 -- three zeros short of the 1e9+7 every other counting file
	// in this repo uses, so it reduced answers that should not have been
	// reduced: {1,2,5} amount 40 returned 890382 instead of 142898369.
	private static final int MOD = 1_000_000_007;

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
