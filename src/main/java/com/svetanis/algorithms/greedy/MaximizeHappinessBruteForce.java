package com.svetanis.algorithms.greedy;

import static java.util.Arrays.sort;

// 3075. Maximize Happiness of Selected Children

public final class MaximizeHappinessBruteForce {

	public static long maximizeHappiness(int[] a, int k) {
		sort(a);
		int n = a.length;
		int count = 0;
		long total = 0;
		while (count < k) {
			int index = n - 1 - count;
			total += a[index];
			count++;
			for (int i = n - 1; i >= 0; i--) {
				if (i == index) {
					a[i] = 0;
				} else {
					a[i] = Math.max(a[i] - 1, 0);
				}
			}
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
