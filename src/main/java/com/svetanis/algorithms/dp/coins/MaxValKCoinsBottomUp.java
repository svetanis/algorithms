package com.svetanis.algorithms.dp.coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2218. Maximum Value of K Coins From Piles

public final class MaxValKCoinsBottomUp {
	// Time Complexity: O(k * total number of coins)
	// Space Complexity: O(n * k)

	public static int maxValueOfCoins(List<List<Integer>> piles, int k) {
		int n = piles.size();
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			List<Integer> pile = piles.get(i - 1);
			int m = pile.size();
			int[] prefix = new int[m + 1];
			for (int coin = 1; coin <= m; coin++) {
				prefix[coin] = prefix[coin - 1] + pile.get(coin - 1);
			}
			for (int coin = 0; coin <= k; coin++) {
				int coins = Math.min(m, coin);
				for (int j = 0; j <= coins; j++) {
					int curr = dp[i - 1][coin - j] + prefix[j];
					dp[i][coin] = Math.max(dp[i][coin], curr);
				}
			}
		}
		return dp[n][k];
	}

	public static void main(String[] args) {
		List<List<Integer>> piles1 = new ArrayList<>();
		piles1.add(Arrays.asList(1, 100, 3));
		piles1.add(Arrays.asList(7, 8, 9));
		System.out.println(maxValueOfCoins(piles1, 2)); // 101

		List<List<Integer>> piles2 = new ArrayList<>();
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(1, 1, 1, 1, 1, 1, 700));
		System.out.println(maxValueOfCoins(piles2, 7)); // 706
	}
}
