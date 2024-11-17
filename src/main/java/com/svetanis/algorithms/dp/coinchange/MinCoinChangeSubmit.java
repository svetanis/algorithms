package com.svetanis.algorithms.dp.coinchange;

import java.util.Arrays;

// 322. Coin Change

// Given a value V, if we want to make change for V cents,
// and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
// what is the minimum number of coins to make the change?

// n - size of array of coins S
// V - coin value

public final class MinCoinChangeSubmit {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	private static final int INF = 1 << 30;

	public static int minCoinChange(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int coin : coins) {
			for (int sum = coin; sum <= amount; sum++) {
				dp[sum] = Math.min(dp[sum], dp[sum - coin] + 1);
			}
		}
		return dp[amount] >= INF ? -1 : dp[amount];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 5 };
		int[] a2 = { 2 };
		int[] a3 = { 1 };
		System.out.println(minCoinChange(a1, 11)); // 3
		System.out.println(minCoinChange(a2, 3)); // -1
		System.out.println(minCoinChange(a3, 0)); // 0
	}
}
