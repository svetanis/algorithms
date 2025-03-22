package com.svetanis.algorithms.search.binary;

// 1482. Minimum Number of Days to Make m Bouquets

public final class MinDays {

	public static int minDays(int[] days, int m, int k) {
		if (m * k > days.length) {
			return -1;
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int day : days) {
			min = Math.min(min, day);
			max = Math.max(max, day);
		}
		int left = min;
		int right = max;
		int minDays = -1;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			if (check(days, m, k, mid)) {
				minDays = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return minDays;
	}

	private static boolean check(int[] days, int m, int k, int current) {
		int flowers = 0;
		int bouquets = 0;
		for (int day : days) {
			if (day <= current) {
				flowers++;
				if (flowers == k) {
					bouquets++;
					flowers = 0;
				}
			} else {
				flowers = 0;
			}
		}
		return bouquets >= m;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 10, 3, 10, 2 };
		System.out.println(minDays(a1, 3, 1)); // 3
		System.out.println(minDays(a1, 3, 2)); // -1
		int[] a2 = { 7, 7, 7, 7, 12, 7, 7 };
		System.out.println(minDays(a2, 2, 3)); // 12
	}
}
