package com.svetanis.algorithms.dp.countways.dice;

// 1155. Number of Dice Rolls With Target Sum

// returns number of ways to get sum
// 'target' with 'n' dice and 'k' throws

public final class DiceThrowTopDown {
	// Time complexity: O(k * n * target)

	private static final int MOD = (int) 1e9 + 7;

	public static int diceThrow(int n, int k, int target) {
		Integer[][] dp = new Integer[n + 1][target + 1];
		return dfs(n, k, target, dp);
	}

	private static int dfs(int n, int k, int target, Integer[][] dp) {
		if (target < 1) {
			return 0;
		}
		if (n == 1) {
			return target <= k ? 1 : 0;
		}
		if (dp[n][target] != null) {
			return dp[n][target];
		}
		int count = 0;
		for (int face = 1; face <= k; face++) {
			count = (count + dfs(n - 1, k, target - face, dp)) % MOD;
		}
		return dp[n][target] = count;
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
