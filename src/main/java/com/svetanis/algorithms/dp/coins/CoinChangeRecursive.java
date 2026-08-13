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
// ways(i, amount) = ways(i, amount - coins[i])   // take coins[i] again
//                 + ways(i + 1, amount)          // stop using coins[i]
// staying at i on the first branch is what makes each coin unlimited;
// never returning to i - 1 is what keeps the count unordered.

// (this header used to carry `f(a) = 1 + min(f(a - d0), ...)`, which is
// LC 322's recurrence -- the MINIMUM number of coins. Different problem;
// MinCoinChange*.java in this folder is the one that computes it.)

// RUNG 1: correct, but ~8 s at LC 518's ceiling ({1,2,5}, amount 5000) --
// a TLE, and slower than the recursive version in
// backtracking/deduplication/. Submit CoinChangeSubmit.java.

public final class CoinChangeRecursive {

	public static int count(List<Integer> coins, int amount) {
		return count(coins, 0, amount);
	}

	private static int count(List<Integer> coins, int index, int amount) {
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
		if (index >= coins.size() && amount >= 1) {
			return 0;
		}

		// return the sum of solutions
		// 1. include a[n - 1]: count(a[], n, v - a[n-1])
		int incl = count(coins, index, amount - coins.get(index));
		// 2. excluding a[n - 1]: count(a[], n - 1, v)
		int excl = count(coins, index + 1, amount);
		return incl + excl;
	}

	public static void main(String[] args) {
		System.out.println(count(asList(1, 2, 3), 4)); // 4
		System.out.println(count(asList(1, 2, 5), 5)); // 4
		System.out.println(count(asList(2), 3)); // 0
	}
}
