package com.svetanis.algorithms.dp.coins;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 322. Coin Change

// Given a value V, if we want to make change for V cents,
// and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
// what is the minimum number of coins to make the change?

// n - size of array of coins S
// V - coin value

public final class MinCoinChangeSubmit {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(amount)

	private static final int INF = 1 << 30;

	// 11 ms
	public static int minCoinChange(int[] coins, int target) {
		if (target == 0) {
			return 0;
		}
		int[] dp = new int[target + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int coin : coins) {
			for (int sum = coin; sum <= target; sum++) {
				dp[sum] = Math.min(dp[sum], dp[sum - coin] + 1);
			}
		}
		return dp[target] >= INF ? -1 : dp[target];
	}

	// 16ms
	public static int bottomUp2(int[] coins, int target) {
		if (target == 0) {
			return 0;
		}
		int[] dp = new int[target + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int sum = 1; sum <= target; sum++) {
			for (int coin : coins) {
				if (coin <= sum) {
					dp[sum] = Math.min(dp[sum], dp[sum - coin] + 1);
				}
			}
		}
		return dp[target] >= INF ? -1 : dp[target];
	}

	// too slow ***************************************************
	public static int topDown(int[] coins, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int result = dfs(coins, target, map);
		return result == INF ? -1 : result;
	}

	private static int dfs(int[] coins, int target, Map<Integer, Integer> map) {
		if (target == 0) {
			return 0;
		}
		if (map.containsKey(target)) {
			return map.get(target);
		}
		int min = INF;
		for (int coin : coins) {
			if (coin <= target) {
				int change = dfs(coins, target - coin, map);
				min = Math.min(min, 1 + change);
			}
		}
		map.put(target, min);
		return min;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 5 };
		int[] a2 = { 2 };
		int[] a3 = { 1 };
		System.out.println(minCoinChange(a1, 11)); // 3
		System.out.println(minCoinChange(a2, 3)); // -1
		System.out.println(minCoinChange(a3, 0)); // 0
	}
}
