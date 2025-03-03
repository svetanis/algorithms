package com.svetanis.algorithms.dp.sum.max.subarr;

// 1014. Best Sightseeing Pair

public final class BestSightseeingPair {
	// Time Complexity: O(n)

	public static int bsp(int[] a) {
		int max = a[0];
		int scope = 0;
		for(int j = 1; j < a.length; j++) {
			scope = Math.max(scope, max + a[j] - j);
			max = Math.max(max, a[j] + j);
		}
		return scope;
	}

	public static void main(String[] args) {
		int[] a1 = { 8, 1, 5, 2, 6 };
		System.out.println(bsp(a1)); // 11

		int[] a2 = { 1, 2 };
		System.out.println(bsp(a2)); // 2
	}
}
