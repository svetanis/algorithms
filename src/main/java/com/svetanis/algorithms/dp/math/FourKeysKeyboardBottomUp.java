package com.svetanis.algorithms.dp.math;

// 651. 4 Keys Keybord

public final class FourKeysKeyboardBottomUp {

	public static int minSteps(int n) {
		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = i;
		}
		for (int i = 3; i <= n; i++) {
			for (int j = 2; j < i - 1; j++) {
				dp[i] = Math.max(dp[i], dp[j - 1] * (i - j));
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(minSteps(7)); // 9
	}
}
