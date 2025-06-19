package com.svetanis.algorithms.dp.coins;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.List;

// 322. Coin Change

// Given a value V, if we want to make change for V cents,
// and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
// what is the minimum number of coins to make the change?

// n - size of array of coins S
// V - coin value

public final class MinCoinChangeBottomUp {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	public static int minCoinChange(List<Integer> list, int amount) {
		if (amount == 0) {
			return 0;
		}
		int[] dp = new int[amount + 1];
		fill(dp, MAX_VALUE);
		dp[0] = 0;
		for (int sum = 1; sum <= amount; sum++) {
			int coins = MAX_VALUE;
			for (int coin : list) {
				if (coin <= sum) {
					coins = dp[sum - coin];
				}
				if (coins != MAX_VALUE) {
					dp[sum] = min(dp[sum], coins + 1);
				}
			}
		}
		return dp[amount] == MAX_VALUE ? -1 : dp[amount];
	}

	public static void main(String[] args) {
		System.out.println(minCoinChange(asList(25, 10, 5), 30)); // 2
		System.out.println(minCoinChange(asList(9, 6, 5, 1), 11)); // 2
		System.out.println(minCoinChange(asList(1, 2, 5), 11)); // 3
		System.out.println(minCoinChange(asList(3), 1)); // -1
	}
}
