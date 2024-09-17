package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// given coins of different denominations and an amount
// compute the fewest number of coins that you need to
// make up that amount, otherwise return -1

public final class MinNumberOfCoinsMemoization {
	// Time Complexity: O(amount * n)
	// Space Complexity: O(amount)

	public static int coins(int amount, List<Integer> coins) {
		Map<Integer, Integer> map = new HashMap<>();
		int min = dfs(amount, 0, coins, map);
		return min == MAX_VALUE ? -1 : min;
	}

	private static int dfs(int amount, int sum, List<Integer> coins, 
			Map<Integer, Integer> map) {
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
		for (int coin : coins) {
			int result = dfs(amount, sum + coin, coins, map);
			if (result != MAX_VALUE) {
				min = min(min, result + 1);
			}
		}
		map.put(sum, min);
		return min;
	}

	public static void main(String[] args) {
		System.out.println(coins(11, asList(1, 2, 5))); // 3
		System.out.println(coins(3, asList(4, 5, 3))); // 1
		System.out.println(coins(0, asList(5))); // 0
		System.out.println(coins(1, asList(3))); // -1
		System.out.println(coins(3, asList(2))); // -1
		System.out.println(coins(6249, asList(186, 419, 83, 408))); // 20
	}
}