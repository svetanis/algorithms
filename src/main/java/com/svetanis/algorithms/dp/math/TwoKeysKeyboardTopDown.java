package com.svetanis.algorithms.dp.math;

// 650. 2 Keys Keyboard

public final class TwoKeysKeyboardTopDown {

	public static int minSteps(int n) {
		Integer[] dp = new Integer[n + 1];
		return dfs(n, dp);
	}
	
	private static int dfs(int n, Integer[] dp) {
		if(n == 1) {
			return 0;
		}
		if(dp[n] != null) {
			return dp[n];
		}
		int min = Integer.MAX_VALUE;
		for(int i = 2; i <= n; i++) {
			if(n % i == 0) {
				min = Math.min(min, dfs(n/i, dp) + i);
			}
		}
		return dp[min] = min;
	}
	
	public static void main(String[] args) {
		System.out.println(minSteps(3)); // 3
		System.out.println(minSteps(1)); // 0
	}
}
