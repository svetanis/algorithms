package com.svetanis.algorithms.dp.interval;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

public final class MaxGainTopDown {

	public static int game(List<Integer> coins) {
		int n = coins.size();
		Integer[][] dp = new Integer[n][n];
		return maxScore(coins, 0, n - 1, dp);
	}

	private static int maxScore(List<Integer> coins, int left, int right, Integer[][] dp) {
		if (left > right) {
			return 0; // base condition
		}
		if (dp[left][right] == null) {
			int x = maxScore(coins, left + 2, right, dp);
			int y = maxScore(coins, left + 1, right - 1, dp);
			int v = maxScore(coins, left + 1, right - 1, dp);
			int u = maxScore(coins, left, right - 2, dp);
			int sum1 = coins.get(left) + min(x, y);
			int sum2 = coins.get(right) + min(v, u);
			dp[left][right] = max(sum1, sum2);
		}
		return dp[left][right];
	}

	public static void main(String[] args) {
		System.out.println(game(asList(8, 15, 3, 7))); // 22
		System.out.println(game(asList(2, 2, 2, 2))); // 4
		System.out.println(game(asList(20, 30, 2, 2, 2, 10))); // 42
		System.out.println(game(asList(25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10))); // 140

		System.out.println(game(asList(5, 3, 4, 5))); // 9
		System.out.println(game(asList(4, 4, 9, 4))); // 13
		System.out.println(game(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0))); // 55
		System.out.println(game(asList(1, 2, 9999, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))); // 10104
	}
}
