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

// Correct and fast -- 2 ms at LC 518's ceiling
// ({1,2,5}, amount 5000) -- but still not submittable, which is not obvious:
// `sum` climbs one coin at a time, so the recursion is amount / min(coin)
// deep, up to 5000 frames at LC 518's limits. It survives a default JVM
// stack and throws StackOverflowError on a smaller one, so the failure looks
// intermittent. That is the cost of accumulating forward, not a bug in it.
// dp/coins/CoinChangeSubmit.java is iterative and has no depth at all.

public final class CoinChangeMemoization {
	// Time Complexity: O(n^2 * amount) -- NOT O(n * amount). There are
	// n * amount states, one per (index, sum), and each one runs a loop over
	// the coins from index onward. O(n * amount) counts the states and forgets
	// the loop inside one. Measured with the amount held fixed: doubling n
	// multiplies the work by ~3.7, not by 2.
	// The take-or-leave form -- dp/coins/CoinChangeMemoization.java -- has the
	// same n * amount states with O(1) work in each, so it really is O(n * amount).
	// Space Complexity: O(n * amount)

	public static int coins(int amount, List<Integer> coins) {
		int n = coins.size();
		Integer[][] memo = new Integer[n + 1][amount + 1];
		return dfs(amount, 0, 0, memo, coins);
	}

	private static int dfs(int amount, int sum, int index, 
			Integer[][] memo, List<Integer> coins) {
		if (sum == amount) {
			return 1;
		}
		if (memo[index][sum] != null) {
			return memo[index][sum];
		}
		int count = 0;
		for (int i = index; i < coins.size(); i++) {
			int coin = coins.get(i);
			if (sum + coin <= amount) {
				count += dfs(amount, sum + coin, i, memo, coins);
			}
		}
		memo[index][sum] = count;
		return count;
	}

	public static void main(String[] args) {
		System.out.println(coins(5, asList(1, 2, 5))); // 4
		System.out.println(coins(3, asList(2))); // 0
		System.out.println(coins(10, asList(10))); // 1
	}
}
