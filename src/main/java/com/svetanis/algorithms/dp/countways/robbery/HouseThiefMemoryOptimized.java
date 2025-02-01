package com.svetanis.algorithms.dp.countways.robbery;

// 198. House Robber

// Given a number array representing 
// the wealth of ‘n’ houses, determine 
// the maximum amount of money the thief 
// can steal without alerting the security system.

public final class HouseThiefMemoryOptimized {

	public static int maxProfit(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		if (n == 1) {
			return a[0];
		}
		int first = 0;
		int second = a[0];
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < n; i++) {
			max = Math.max(first + a[i], second);
			first = second;
			second = max;
		}
		return max;
	}

	public static int maxProfit2(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		if (n == 1) {
			return a[0];
		}
		int first = Math.max(a[0], a[1]);
		int second = a[0];
		for (int i = 2; i < n; i++) {
			int curr = Math.max(first, second + a[i]);
			second = first;
			first = curr;
		}
		return first;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 5, 1, 3, 6, 2, 4 };
		System.out.println(maxProfit(a1));

		int[] a2 = { 2, 10, 14, 8, 1 };
		System.out.println(maxProfit(a2));
	}
}
