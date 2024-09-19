package com.svetanis.algorithms.backtracking.deduplication;

import static java.util.Arrays.asList;

import java.util.List;

// given a list of coins of different 
// denominations and a total amount of money
// find the number of combinations that make
// up that amount. each coin can be used any
// amount of times. if amount can't be made 
// up by any combination of the coins, return 0

public final class CoinChangeMemoization {
	// Time Complexity: O(n * amount)
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