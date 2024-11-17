package com.svetanis.algorithms.dp.coinchange;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 322. Coin Change

// Given a value V, if we want to make change for V cents,
// and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
// what is the minimum number of coins to make the change?

// n - size of array of coins S
// V - coin value

public final class MinCoinChangeTopDown {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	public static int minCoinChange(List<Integer> list, int amount) {
		Map<Integer, Integer> map = new HashMap<>();
		int min = dfs(amount, 0, list, map);
		return min == MAX_VALUE ? -1 : min;
	}

	private static int dfs(int amount, int sum, List<Integer> list, Map<Integer, Integer> map) {
		if (map.containsKey(sum)) {
			return map.get(sum);
		}
		if (sum == amount) {
			return 0;
		}
		if (sum > amount) {
			return MAX_VALUE;
		}
		int min = MAX_VALUE;
		for (int coin : list) {
			int coins = dfs(amount, sum + coin, list, map);
			if (coins != MAX_VALUE) {
				min = min(min, coins + 1);
			}
		}
		map.put(sum, min);
		return min;
	}

	public static void main(String[] args) {
		System.out.println(minCoinChange(asList(25, 10, 5), 30)); // 2
		System.out.println(minCoinChange(asList(9, 6, 5, 1), 11)); // 2
		System.out.println(minCoinChange(asList(1, 2, 5), 11)); // 3
		System.out.println(minCoinChange(asList(3), 1)); // -1
	}
}
