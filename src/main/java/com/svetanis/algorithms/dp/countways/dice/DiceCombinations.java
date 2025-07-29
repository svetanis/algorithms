package com.svetanis.algorithms.dp.countways.dice;

// count the number of ways to construct sum n 
// by throwing a dice one or more times. 
// Each throw produces an outcome between 1 and  6.

public final class DiceCombinations {
	// Time complexity: O(n)
	// Space complexity: O(n)

	private static final int MOD = (int) 1e9 + 7;

	public static long diceThrow(int n) {
		long[] dp = new long[n + 1];
		dp[0] = 1;
		for (int sum = 1; sum <= n; sum++) {
			for (int outcome = 1; outcome <= 6 && outcome <= sum; outcome++) {
				dp[sum] = (dp[sum] + dp[sum - outcome]) % MOD;
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(diceThrow(3)); // 4
		System.out.println(diceThrow(4)); // 8
	}
}
