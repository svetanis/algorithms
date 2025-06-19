package com.svetanis.algorithms.dp.countways.dice;

// 1155. Number of Dice Rolls With Target Sum

// returns number of ways to get sum
// 'target' with 'n' dice and 'k' throws

public final class DiceThrowBottomUp {
	// Time complexity: O(k * n * target)

	private static final int MOD = (int) 1e9 + 7;

	public static int diceThrow(int n, int k, int target) {
		int[][] dp = new int[n + 1][target + 1];
		for (int face = 1; face <= k && face <= target; face++) {
			dp[1][face] = 1;
		}

		for (int dice = 2; dice <= n; dice++) {
			for (int sum = 1; sum <= target; sum++) {
				for (int face = 1; face <= k && face < sum; face++) {
					dp[dice][sum] = (dp[dice][sum] + dp[dice - 1][sum - face]) % MOD;
				}
			}
		}
		return dp[n][target];
	}

	public static void main(String[] args) {
		System.out.println(diceThrow(2, 4, 1)); // 0
		System.out.println(diceThrow(2, 2, 3)); // 2
		System.out.println(diceThrow(3, 6, 8)); // 21
		System.out.println(diceThrow(2, 4, 5)); // 4
		System.out.println(diceThrow(3, 4, 5)); // 6
		System.out.println(diceThrow(1, 6, 3)); // 1
		System.out.println(diceThrow(2, 6, 7)); // 6
		System.out.println(diceThrow(30, 30, 500)); // 222616187
	}
}
