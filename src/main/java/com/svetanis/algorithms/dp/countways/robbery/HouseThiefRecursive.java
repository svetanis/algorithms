package com.svetanis.algorithms.dp.countways.robbery;

import static java.lang.Math.max;

// 198. House Robber

// Given a number array representing 
// the wealth of ‘n’ houses, determine 
// the maximum amount of money the thief 
// can steal without alerting the security system.

public final class HouseThiefRecursive {

	public static int maxProfit(int[] a) {
		int n = a.length;
		if (a == null || n == 0) {
			return 0;
		}
		if (n == 1) {
			return a[0];
		}
		return maxProfit(a, 0);
	}

	private static int maxProfit(int[] a, int index) {
		if (index >= a.length) {
			return 0;
		}
		// include
		int incl = a[index] + maxProfit(a, index + 2);
		// exclude
		int excl = maxProfit(a, index + 1);
		return max(incl, excl);
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 5, 1, 3, 6, 2, 4 };
		System.out.println(maxProfit(a1));

		int[] a2 = { 2, 10, 14, 8, 1 };
		System.out.println(maxProfit(a2));
	}
}
