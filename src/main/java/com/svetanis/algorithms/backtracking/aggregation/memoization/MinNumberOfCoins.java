package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// 322. Coin Change
//
// Input: coins, the denominations (1 <= coins.length <= 12, 1 <= coins[i] <= 2^31 - 1), and an
// amount (0 <= amount <= 10^4). Returns the fewest coins that add up to the amount, using each
// denomination as often as needed, or -1 if no combination reaches it.
//
// The one idea: one call answers "starting with sum already paid, what is the fewest coins that
// finish exactly at amount?". It tries every coin that still fits as the next one, asks the smaller
// question with that coin added to sum, and keeps the best answer plus the one coin just used.
// Reaching amount costs 0 more coins; MAX_VALUE marks "no coins finish from here", so it never
// wins a min.
//
// This is the plain recursion with no memo: the same sum is reached again by every order of the
// same coins, so it takes exponential time and is only fit for small amounts. A coin larger than
// amount - sum is skipped before sum + coin is formed, because sum + coin can exceed the int
// range for coins near 2^31 - 1.
//
// Time: O(n^(amount / min coin)) -- n coins tried at every level, the sum climbs by at least the
// smallest coin per level.
// Space: O(amount / min coin) -- the recursion depth.
//
// Siblings: MinNumberOfCoinsMemoization caches the answer per sum; the iterative versions in
// com.svetanis.algorithms.dp.coins (MinCoinChangeSubmit) are the ones to submit.

public final class MinNumberOfCoins {

	public static int coins(int amount, List<Integer> coins) {
		int min = dfs(amount, 0, coins);
		return min == MAX_VALUE ? -1 : min; // RETURN: MAX_VALUE means no combination
	}

	private static int dfs(int amount, int sum, List<Integer> coins) {
		if (sum == amount) { // BASE: paid exactly, no more coins
			return 0;
		}
		if (sum > amount) { // BASE: overshot; unreachable now that oversized coins are skipped
			return MAX_VALUE;
		}
		int min = MAX_VALUE;
		for (int coin : coins) {
			if (coin > amount - sum) { // SKIP: would overshoot; also avoids int overflow of sum + coin
				continue;
			}
			int result = dfs(amount, sum + coin, coins); // TAKE: this coin next
			if (result != MAX_VALUE) {
				min = min(min, result + 1); // RECORD: the coin just used counts one
			}
		}
		return min;
	}

	public static void main(String[] args) {
		System.out.println(coins(11, asList(1, 2, 5))); // 3
		System.out.println(coins(3, asList(4, 5, 3))); // 1
		System.out.println(coins(0, asList(5))); // 0
		System.out.println(coins(1, asList(3))); // -1
		System.out.println(coins(3, asList(2))); // -1
	}
}
