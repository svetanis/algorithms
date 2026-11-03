package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.util.Arrays.asList;

import java.util.List;

// 518. Coin Change II

// given a list of coins of different
// denominations and a total amount of money
// find the number of combinations that make
// up that amount. each coin can be used any
// amount of times. if amount can't be made
// up by any combination of the coins, return 0

// This is the teaching version: correct, but not
// submittable -- no memoization, so at LC 518's limits it does not finish in time.
// The exponential cost is the point; CoinChangeMemoization.java next door
// is the fix, and dp/coins/ carries the iterative rewrites.

public final class CoinChange {
	// Time Complexity: O(C(amount + n, n)) -- the loop visits exactly one node per
	// distinct non-decreasing coin sequence whose sum is at most the amount, which is
	// the number of multisets of coins. For n fixed coins that grows like amount^n / n!,
	// so doubling the amount multiplies the nodes by about 2^n. Exponential in n.
	// Space Complexity: O(amount) stack -- `sum` climbs by one coin per frame, so the
	// depth is amount / min(coin)

	public static int coins(int amount, List<Integer> coins) {
		return dfs(amount, 0, 0, coins);
	}

	private static int dfs(int amount, int sum, int index, List<Integer> coins) {
		if (sum == amount) { // BASE: the coins taken so far make the amount exactly
			return 1;
		}
		int count = 0;
		for (int i = index; i < coins.size(); i++) { // WALK: coins from index on, so each multiset is built in one order
			int coin = coins.get(i);
			if (sum + coin <= amount) { // SKIP a coin that would pass the amount
				count += dfs(amount, sum + coin, i, coins); // TAKE: coin i; stay at i, so it can be taken again
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(coins(5, asList(1, 2, 5))); // 4
		System.out.println(coins(3, asList(2))); // 0
		System.out.println(coins(10, asList(10))); // 1
	}
}
