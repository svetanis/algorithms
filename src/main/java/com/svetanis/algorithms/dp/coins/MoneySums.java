package com.svetanis.algorithms.dp.coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// CSES: Money Sums

public final class MoneySums {
	// Time Complexity: O(n * sum)
	// Space Complexity: O(n * sum)

	public static List<Integer> moneySums(int[] coins) {
		int n = coins.length;
		int max = Arrays.stream(coins).sum();
		boolean[][] dp = build(max, coins);
		List<Integer> list = new ArrayList<>();
		for (int sum = 1; sum <= max; sum++) {
			if (dp[n][sum]) {
				list.add(sum);
			}
		}
		return list;
	}

	private static boolean[][] build(int max, int[] coins) {
		int n = coins.length;
		boolean[][] dp = new boolean[n + 1][max + 1];
		dp[0][0] = true;
		for (int i = 1; i <= n; i++) {
			int coin = coins[i - 1];
			for (int sum = 0; sum <= max; sum++) {
				if (dp[i - 1][sum]) {
					dp[i][sum] = true;
				}
				if (coin <= sum && dp[i - 1][sum - coin]) {
					dp[i][sum] = true;
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		int[] a = { 4, 2, 5, 2 };
		System.out.println(moneySums(a)); // 2,4,5,6,7,8,9,11,13
	}
}
