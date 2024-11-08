package com.svetanis.algorithms.greedy;

import static java.lang.Math.max;
import static java.util.Arrays.sort;

// 3075. Maximize Happiness of Selected Children

public final class MaximizeHappiness {
	// Time Complexity: O(n log n + k)

	public static long maximizeHappiness(int[] a, int k) {
		sort(a);
		int n = a.length;
		long total = 0;
		for (int i = 0; i < k; i++) {
			int curr = a[n - 1 - i] - i;
			total += max(curr, 0);
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		int[] a2 = { 1, 1, 1, 1 };
		int[] a3 = { 2, 3, 4, 5 };
		System.out.println(maximizeHappiness(a1, 2)); // 4
		System.out.println(maximizeHappiness(a2, 2)); // 1
		System.out.println(maximizeHappiness(a3, 1)); // 5
	}
}
