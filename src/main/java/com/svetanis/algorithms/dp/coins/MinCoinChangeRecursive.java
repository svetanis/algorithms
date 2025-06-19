package com.svetanis.algorithms.dp.coins;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// 322. Coin Change

// Given a value V, if we want to make change for V cents,
// and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
// what is the minimum number of coins to make the change?

// n - size of array of coins S
// V - coin value

public final class MinCoinChangeRecursive {

	public static int minCoinChange(List<Integer> list, int amount) {
		int min = dfs(amount, 0, list);
		return min == MAX_VALUE ? -1 : min;
	}

	private static int dfs(int amount, int sum, List<Integer> list) {
		if (amount == sum) {
			return 0;
		}
		if (sum > amount) {
			return MAX_VALUE;
		}
		int min = MAX_VALUE;
		for (int coin : list) {
			int coins = dfs(amount, sum + coin, list);
			if (coins != MAX_VALUE) {
				min = min(min, coins + 1);
			}
		}
		return min;
	}

	public static void main(String[] args) {
		System.out.println(minCoinChange(asList(25, 10, 5), 30)); // 2
		System.out.println(minCoinChange(asList(9, 6, 5, 1), 11)); // 2
		System.out.println(minCoinChange(asList(1, 2, 5), 11)); // 3
		System.out.println(minCoinChange(asList(3), 1)); // -1
	}
}
