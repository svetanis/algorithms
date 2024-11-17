package com.svetanis.algorithms.dp.coinchange;

import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.List;

// 518. Coin Change II

// n - size of array of coins S
// V - coin value
// we need n + 1 rows as the table is
// constructed in bottom up manner
// using the base case 0 value case (n = 0)

public final class CoinChangeBottomUp {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(n * amount)

	public static int count(List<Integer> list, int amount) {
		int n = list.size();
		int[][] dp = new int[amount + 1][n];
		// fill the entries for 0 value case
		fill(dp[0], 1);

		for (int sum = 1; sum <= amount; ++sum) {
			for (int i = 0; i < n; ++i) {
				int excl = 0;
				if (i > 0) {
					excl = dp[sum][i - 1];
				}
				int incl = 0;
				if (sum >= list.get(i)) {
					incl = dp[sum - list.get(i)][i];
				}
				// total count
				dp[sum][i] = incl + excl;
			}
		}
		return dp[amount][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(count(asList(1, 2, 3), 4)); // 4
		System.out.println(count(asList(1, 2, 5), 5)); // 4
		System.out.println(count(asList(2), 3)); // 0
	}
}
