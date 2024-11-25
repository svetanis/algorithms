package com.svetanis.algorithms.dp.consttransition;

// 2466. Count Ways To Build Good Strings

public final class CountWaysToBuildValidString {

	private static final int MOD = (int) 1e9 + 7;

	public static int count(int low, int high, int zero, int one) {
		Integer[] dp = new Integer[high + 1];
		return dfs(low, high, zero, one, 0, dp);
	}

	private static int dfs(int low, int high, int zero, int one, int index, Integer[] dp) {
		if (index > high) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int count = 0;
		if (in(low, high, index)) {
			count += 1;
		}
		count += dfs(low, high, zero, one, index + zero, dp);
		count += dfs(low, high, zero, one, index + one, dp);
		count %= MOD;
		dp[index] = (int) count;
		return dp[index];
	}

	private static boolean in(int low, int high, int len) {
		return len >= low && len <= high;
	}

	public static void main(String[] args) {
		System.out.println(count(3, 3, 1, 1)); // 8
		System.out.println(count(2, 3, 1, 2)); // 5
	}
}
