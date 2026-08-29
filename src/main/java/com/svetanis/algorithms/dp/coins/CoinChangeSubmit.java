package com.svetanis.algorithms.dp.coins;

// 518. Coin Change II
// CSES: Coin Combinations II

// the submission. Of the six CoinChange* rungs here only the three iterative
// ones -- BottomUp, SpaceOptimized, this -- pass LC 518: CoinChangeRecursive
// is ~7 s, and Memoization/TopDown recurse amount / min(coin) + n deep --
// 5,003 frames on {1,2,5} at amount 5000, 5,300 at LC's 300-coin ceiling --
// which needs ~800k of stack and can overflow a judge that gives less.

// counts UNORDERED combinations -- 1+2 and 2+1 are one way. The coin-outer /
// sum-inner nesting is what makes that true; CoinCombinations.java swaps the
// loops and counts ordered sequences instead.

// no modulus: LC 518 guarantees the answer fits in a signed int.

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
