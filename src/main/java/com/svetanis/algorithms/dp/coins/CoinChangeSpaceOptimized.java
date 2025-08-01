package com.svetanis.algorithms.dp.coins;

import static java.util.Arrays.asList;

import java.util.List;

// 518. Coin Change II

// Given a value N, if we want to make change for N cents, 
// and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
// how many ways can we make the change? The order of coins doesn’t matter.

// n - size of array of coins a
// v - coin value
// dp[i] will be storing the number
// of solutions for value i.
// we need n + 1 rows as the table is
// constructed in bottom up manner
// using the base case (n = 0)

public final class CoinChangeSpaceOptimized {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	private static final int MOD = 1000007;
	
	public static int count(List<Integer> coins, int amount) {
		int[] dp = new int[amount + 1];
		// base case
		// given value is 0
		dp[0] = 1;
		// pick all coins one by one and
		// update the dp[] values
		// after the index greater than
		// or equal to the value of
		// the picked coin
		for (int coin : coins) {
			for (int sum = coin; sum <= amount; sum++) {
				dp[sum] = (dp[sum] + dp[sum - coin]) % MOD;
			}
		}
		return dp[amount] % MOD;
	}

	public static void main(String[] args) {
		System.out.println(count(asList(1, 2, 3), 4)); // 4
		System.out.println(count(asList(1, 2, 5), 5)); // 4
		System.out.println(count(asList(2), 3)); // 0
	}
}
