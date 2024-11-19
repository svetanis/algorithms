package com.svetanis.algorithms.dp.dice;

// 1155. Number of Dice Rolls With Target Sum

// returns number of ways to get sum
// 'target' with 'n' dice and 'k' throws

public final class DiceThrowSubmit {
	// Time complexity: O(k * n * target)
	// Space complexity: O(target)

	private static final int MOD = (int) 1e9 + 7;

	public static int diceThrow(int n, int k, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int dice = 1; dice <= n; dice++) {
			int[] temp = new int[target + 1];
			for (int sum = 1; sum <= Math.min(target, dice * k); sum++) {
				for (int face = 1; face <= Math.min(k, sum); face++) {
					temp[sum] = (temp[sum] + dp[sum - face]) % MOD;
				}
			}
			dp = temp;
		}
		return dp[target];
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
