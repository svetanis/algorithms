package com.svetanis.algorithms.backtracking.deduplication;

import static java.util.Arrays.asList;

import java.util.List;

// given a list of coins of different 
// denominations and a total amount of money
// find the number of combinations that make
// up that amount. each coin can be used any
// amount of times. if amount can't be made 
// up by any combination of the coins, return 0

public final class CoinChange {

	public static int coins(int amount, List<Integer> coins) {
		return dfs(amount, 0, 0, coins);
	}

	private static int dfs(int amount, int sum, int index, List<Integer> coins) {
		if (sum == amount) {
			return 1;
		}
		int count = 0;
		for (int i = index; i < coins.size(); i++) {
			int coin = coins.get(i);
			if (sum + coin <= amount) {
				count += dfs(amount, sum + coin, i, coins);
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