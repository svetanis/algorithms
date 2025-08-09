package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 3107. Minimum Operations to Make Median of Array Equal to K

public final class MinOperations {
	// Time Complexity: O(n log n)

	public static long minOperations(int[] a, int k) {
		Arrays.sort(a);
		int n = a.length;
		int mindex = n / 2;
		long min = Math.abs(a[mindex] - k);
		if (a[mindex] > k) {
			for (int i = mindex - 1; i >= 0 && a[i] > k; i--) {
				min += a[i] - k;
			}
		} else {
			for (int i = mindex + 1; i < n && a[i] < k; i++) {
				min += k - a[i];
			}
		}
		return min;
	}

	public static void main(String[] args) {
		int[] a = { 2, 5, 6, 8, 5 };
		System.out.println(minOperations(a, 4)); // 2
		System.out.println(minOperations(a, 7)); // 3

		int[] a1 = { 1, 2, 3, 4, 5, 6 };
		System.out.println(minOperations(a1, 4)); // 0
	}
}
