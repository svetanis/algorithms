package com.svetanis.algorithms.dp.coins;

// 518. Coin Change II

// Given a value N, if we want to make change for N cents, 
// and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
// how many ways can we make the change? The order of coins doesn’t matter.

// n - size of array of coins a
// v - coin value
// returns the count of ways we can sum
// a[0 ... n-1] coins to get sum v

// the recurrence, over (index, amount):
// ways(i, amount) = ways(i, amount - coins[i]) + ways(i + 1, amount)

// (this header used to carry `f(a) = 1 + min(f(a - d0), ...)`, which is
// LC 322's recurrence -- the MINIMUM number of coins. Different problem;
// MinCoinChange*.java in this folder is the one that computes it.)

// ⚠️ RUNG 2: fast (0.15 ms on {1,2,5}/5000, 23 ms at LC's 300-coin ceiling)
// but a RISK to submit -- the recursion is amount / min(coin) + n deep,
// 5,003 frames on {1,2,5} at amount 5000 and 5,300 at the 300-coin ceiling.
// Measured: needs ~800k of stack (overflows at -Xss512k, clean at 800k, clean
// at the 1m JVM default). Whether a given judge gives that much is not
// something this file can know -- the iterative rungs do not ask.
// CoinChangeTopDown.java is this file with List instead of int[].
// The iterative rungs -- BottomUp, SpaceOptimized, Submit -- have no depth.

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

		// TAKE coins[index] -- index stays put, so the same coin can be taken again
		int take = dfs(coins, index, amount - coins[index], dp);
		// LEAVE coins[index] -- and never come back to it, which is what stops
		// 1+2 and 2+1 being counted as two different answers
		int leave = dfs(coins, index + 1, amount, dp);
		dp[index][amount] = take + leave;
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
