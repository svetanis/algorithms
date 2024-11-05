package com.svetanis.algorithms.sorting;

import static java.lang.Math.abs;
import static java.util.Arrays.sort;

// 462. Minimum Moves to Equal Array ElementsII

public final class MinMovesToEqualArray {
	// Time Complexity: O(n log n)

	public static int minOperations(int[] a) {
		int n = a.length;
		sort(a);
		int median = median(a);
		int moves = 0;
		for (int i = 0; i < n; i++) {
			moves += abs(a[i] - median);
		}
		return moves;
	}

	private static int median(int[] a) {
		int n = a.length;
		if (n % 2 != 0) {
			return a[n / 2];
		}
		return (a[(n - 1) / 2] + a[n / 2]) / 2;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 3, 2, 6 };
		System.out.println(minOperations(a1)); // 6

		int[] a2 = { 1, 2, 3 };
		System.out.println(minOperations(a2)); // 2

		int[] a3 = { 1, 10, 2, 9 };
		System.out.println(minOperations(a3)); // 16
	}
}
