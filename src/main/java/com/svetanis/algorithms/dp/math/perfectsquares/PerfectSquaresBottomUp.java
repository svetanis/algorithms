package com.svetanis.algorithms.dp.math.perfectsquares;

import static java.lang.Math.sqrt;

import java.util.Arrays;

// 279. Perfect Squares

public final class PerfectSquaresBottomUp {
	// Time Complexity: O(n * sqrt(n))
	// Space Complexity: O(n)

	public static int sum(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= sqrt(n); i++) {
			int sqr = i * i;
			for (int j = sqr; j <= n; j++) {
				dp[j] = Math.min(dp[j], dp[j - sqr] + 1);
			}
		}
		return dp[n];
	}

	public static int sum2(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(sum(6)); // 3 --> 4 + 1 + 1
		System.out.println(sum(12)); // 3 --> 4 + 4 + 4
		System.out.println(sum(13)); // 2 --> 4 + 9

		System.out.println(sum2(6)); // 3 --> 4 + 1 + 1
		System.out.println(sum2(12)); // 3 --> 4 + 4 + 4
		System.out.println(sum2(13)); // 2 --> 4 + 9
	}
}