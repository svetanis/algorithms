package com.svetanis.algorithms.dp.countways.robbery;

// 213. House Robber II

public final class HouseThiefCircularMemoryOptimized {

	public static int maxProfit(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		if (n == 1) {
			return a[0];
		}
		int incl = maxProfit(a, 0, n - 2);
		int excl = maxProfit(a, 1, n - 1);
		return Math.max(incl, excl);
	}

	private static int maxProfit(int[] a, int start, int end) {
		int inclPrev = 0;
		int exclPrev = 0;
		for (int i = start; i <= end; i++) {
			int inclCurr = exclPrev + a[i];
			exclPrev = Math.max(inclPrev, exclPrev);
			inclPrev = inclCurr;
		}
		return Math.max(inclPrev, exclPrev);
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 2 };
		System.out.println(maxProfit(a1)); // 3

		int[] a2 = { 1, 2, 3, 1 };
		System.out.println(maxProfit(a2)); // 4

		int[] a3 = { 1, 2, 3 };
		System.out.println(maxProfit(a3)); // 3

		int[] a4 = { 2, 5, 1, 3, 6, 2, 4 };
		System.out.println(maxProfit(a4));

		int[] a5 = { 2, 10, 14, 8, 1 };
		System.out.println(maxProfit(a5));
	}
}
