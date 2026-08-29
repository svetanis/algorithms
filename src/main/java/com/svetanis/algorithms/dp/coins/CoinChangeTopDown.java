package com.svetanis.algorithms.dp.coins;

import static java.util.Arrays.asList;

import java.util.List;

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

// ⚠️ This is CoinChangeMemoization.java with List<Integer> instead of int[] --
// same algorithm, same limits, and the same StackOverflowError on a small
// judge stack. Pick one of the two; keeping both teaches nothing extra.
// Submit CoinChangeSubmit.java.

public final class CoinChangeTopDown {

	// Time Complexity: O(n * amount) -- one evaluation per (index, amount) state
	// Space Complexity: O(n * amount) table, plus a recursion depth of n + amount --
	// the depth is the risk: 5,003 frames on {1,2,5} at amount 5000 needs ~800k of
	// stack (measured: overflows at -Xss512k, clean at 800k). CoinChangeSubmit.java
	// is the fix -- it has no stack to run out of

	public static int count(List<Integer> list, int amount) {
		int n = list.size();
		Integer[][] dp = new Integer[n + 1][amount + 1];
		return count(list, 0, amount, dp);
	}

	private static int count(List<Integer> list, int index, int amount, Integer[][] dp) {
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
		if (index >= list.size() && amount >= 1) {
			return 0;
		}

		if (dp[index][amount] != null) {
			return dp[index][amount];
		}

		// return the sum of solutions
		// 1. include a[n - 1]: count(a[], n, v - a[n-1])
		int incl = count(list, index, amount - list.get(index), dp);
		// 2. excluding a[n - 1]: count(a[], n - 1, v)
		int excl = count(list, index + 1, amount, dp);
		dp[index][amount] = incl + excl;
		return dp[index][amount];
	}

	public static void main(String[] args) {
		System.out.println(count(asList(1, 2, 3), 4)); // 4
		System.out.println(count(asList(1, 2, 5), 5)); // 4
		System.out.println(count(asList(2), 3)); // 0
	}
}
