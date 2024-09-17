package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// given coins of different denominations and an amount
// compute the fewest number of coins that you need to
// make up that amount, otherwise return -1

public final class MinNumberOfCoins {

	public static int coins(int amount, List<Integer> coins) {
		int min = dfs(amount, 0, coins);
		return min == MAX_VALUE ? -1 : min;
	}

	private static int dfs(int amount, int sum, List<Integer> coins) {
		if (sum == amount) {
			return 0;
		}
		if (sum > amount) {
			return MAX_VALUE;
		}
		int min = MAX_VALUE;
		for (int coin : coins) {
			int result = dfs(amount, sum + coin, coins);
			if (result != MAX_VALUE) {
				min = min(min, result + 1);
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