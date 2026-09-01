package com.svetanis.algorithms.dp.coins;

import static java.util.Arrays.asList;

import java.util.List;

// 518. Coin Change II

// n - size of array of coins S
// V - coin value
// we need n + 1 rows as the table is
// constructed in bottom up manner
// using the base case 0 value case (n = 0)

// RUNG 3, and the safe one to submit: iterative, so there is no recursion
// depth to blow. Of the three recursive rungs above it, only Recursive
// actually fails LC 518, and it fails on time -- it does not finish
// amount = 5000 in eight seconds. Memoization and TopDown both answer it
// in milliseconds on a default stack; their depth grows with the amount,
// so they overflow once the stack is cut to 256k, which makes them a risk
// rather than a certain failure.

// the table is dp[coin][amount] -- item-major, matching dp/knapsack/ and
// dp/sum/given/subseq/. row 0 is "no coins considered yet": one way to make
// 0, no way to make anything else. that row is also why an empty coin list
// returns an answer here rather than throwing.

public final class CoinChangeBottomUp {
	// Time Complexity: O(n * amount)
	// Space Complexity: O(n * amount)

	public static int count(List<Integer> coins, int amount) {
		int n = coins.size();
		int[][] dp = new int[n + 1][amount + 1];
		// base case: exactly one way to make 0 -- take nothing
		dp[0][0] = 1;

		for (int i = 1; i <= n; ++i) {
			int coin = coins.get(i - 1);
			for (int sum = 0; sum <= amount; ++sum) {
				// TAKE coin i -- stay on row i, so the coin can be taken again
				int take = sum >= coin ? dp[i][sum - coin] : 0;
				// LEAVE coin i -- whatever the first i - 1 coins could already do
				int leave = dp[i - 1][sum];
				// the same + as the recursion
				dp[i][sum] = take + leave;
			}
		}
		return dp[n][amount];
	}

	public static void main(String[] args) {
		System.out.println(count(asList(1, 2, 3), 4)); // 4
		System.out.println(count(asList(1, 2, 5), 5)); // 4
		System.out.println(count(asList(2), 3)); // 0
	}
}
