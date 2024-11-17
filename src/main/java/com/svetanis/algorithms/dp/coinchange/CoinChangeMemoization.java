package com.svetanis.algorithms.dp.coinchange;

// 518. Coin Change II

// Given a value N, if we want to make change for N cents, 
// and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
// how many ways can we make the change? The order of coins doesn’t matter.

// n - size of array of coins a
// v - coin value
// returns the count of ways we can sum
// a[0 ... n-1] coins to get sum v

// f(a) : min number of coins needed to make 
//        the amount of a using denominations
//        d0, ..., dk-1

// f(a) = 1 + min(f(a - d0), f(a - d1), ..., f(a - dk))

public final class CoinChangeMemoization {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(n * amount)

	public static int coinChange(int[] coins, int amount) {
		int n = coins.length;
		Integer[][] dp = new Integer[n + 1][amount + 1];
		return dfs(coins, 0, amount, dp);
	}

	private static int dfs(int[] coins, int index, int amount, Integer[][] dp) {
		// base case
		if (amount == 0) {
			return 1;
		}
		// if V < 0 then no solution exists
		if (amount < 0) {
			return 0;
		}
		// if there are no coins and V > 0,
		// then no solution exists
		if (index >= coins.length && amount >= 1) {
			return 0;
		}

		if (dp[index][amount] != null) {
			return dp[index][amount];
		}

		// return the sum of solutions
		// 1. include a[n - 1]: count(a[], n, v - a[n-1])
		int incl = dfs(coins, index, amount - coins[index], dp);
		// 2. excluding a[n - 1]: count(a[], n - 1, v)
		int excl = dfs(coins, index + 1, amount, dp);
		dp[index][amount] = incl + excl;
		return dp[index][amount];
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
