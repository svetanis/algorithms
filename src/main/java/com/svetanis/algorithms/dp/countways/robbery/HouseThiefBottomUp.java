package com.svetanis.algorithms.dp.countways.robbery;

import static java.lang.Math.max;

// 198. House Robber

// Given a number array representing 
// the wealth of ‘n’ houses, determine 
// the maximum amount of money the thief 
// can steal without alerting the security system.

public final class HouseThiefBottomUp {

	public static int maxProfit(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		if (n == 1) {
			return a[0];
		}
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = a[0];
		for (int i = 1; i < n; i++) {
			dp[i + 1] = Math.max(dp[i - 1] + a[i], dp[i]);
		}
		return dp[n];
	}

	public static int maxProfit1(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		if (n == 1) {
			return a[0];
		}
		int[] dp = new int[n];
		dp[0] = a[0];
		dp[1] = Math.max(a[0], a[1]);
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + a[i]);
		}
		return dp[n - 1];
	}

	public static int maxProfit2(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		if (n == 1) {
			return a[0];
		}
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			int excl = i > 0 ? dp[i - 1] : 0;
			int incl = i > 1 ? dp[i - 2] + a[i] : a[i];
			dp[i] = max(excl, incl);
		}
		return dp[n - 1];
	}

	public static void main(String[] args) {
		int[] a = { 6, 1, 2, 7 };
		System.out.println(maxProfit(a));

		int[] a1 = { 2, 5, 1, 3, 6, 2, 4 };
		System.out.println(maxProfit(a1));

		int[] a2 = { 2, 10, 14, 8, 1 };
		System.out.println(maxProfit(a2));
	}
}
