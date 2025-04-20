package com.svetanis.algorithms.dp.math;

// 650. 2 Keys Keyboard

public final class TwoKeysKeyboardBottomUp {

	public static int minSteps(int n) {
		if (n == 1) {
			return 0;
		}
		int[] dp = new int[n + 1];
		for (int i = 2; i <= n; i++) {
			dp[i] = i;
			for (int j = 1; j <= i / 2; j++) {
				if (i % j == 0) {
					dp[i] = Math.min(dp[i], dp[j] + i / j);
				}
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(minSteps(3)); // 3
		System.out.println(minSteps(1)); // 0
	}
}
