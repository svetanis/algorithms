package com.svetanis.algorithms.dp.coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2218. Maximum Value of K Coins From Piles

public final class MaxValKCoinsTopDown {
	// Time Complexity: O(k * total num of coins)
	// Space Complexity: O(n * k)

	private List<List<Integer>> piles;

	public int maxValueOfCoins(List<List<Integer>> piles, int k) {
		this.piles = piles;
		int n = piles.size();
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			for (int coin = 0; coin <= k; coin++) {
				dp[i][coin] = -1;
			}
		}
		return dfs(n, k, dp);
	}

	private int dfs(int i, int coins, int[][] dp) {
		if (i == 0) {
			return 0;
		}
		if (dp[i][coins] != -1) {
			return dp[i][coins];
		}
		int sum = 0;
		int n = piles.get(i - 1).size();
		for (int coin = 0; coin <= Math.min(n, coins); coin++) {
			if (coin > 0) {
				sum += piles.get(i - 1).get(coin - 1);
			}
			dp[i][coins] = Math.max(dp[i][coins], //
					sum + dfs(i - 1, coins - coin, dp));
		}
		return dp[i][coins];
	}

	public static void main(String[] args) {
		MaxValKCoinsTopDown mvc = new MaxValKCoinsTopDown();
		List<List<Integer>> piles1 = new ArrayList<>();
		piles1.add(Arrays.asList(1, 100, 3));
		piles1.add(Arrays.asList(7, 8, 9));
		System.out.println(mvc.maxValueOfCoins(piles1, 2)); // 101

		List<List<Integer>> piles2 = new ArrayList<>();
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(100));
		piles2.add(Arrays.asList(1, 1, 1, 1, 1, 1, 700));
		System.out.println(mvc.maxValueOfCoins(piles2, 7)); // 706
	}
}
