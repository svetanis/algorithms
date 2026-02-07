package com.svetanis.algorithms.dp.math.perfectsquares;

// 1025. Divisor Game

public final class DivisorGame {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static boolean divisorGameSimple(int n) {
		return n % 2 == 0;
	}

	public static boolean divisorGame(int n) {
		boolean[] dp = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				if (i % j == 0 && !dp[i - j]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(divisorGame(2)); // true
		System.out.println(divisorGame(3)); // false
	}
}