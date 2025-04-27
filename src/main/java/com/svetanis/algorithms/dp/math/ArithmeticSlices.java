package com.svetanis.algorithms.dp.math;

// 413. Arithmetic Slices

public final class ArithmeticSlices {
	// Time Complexity: O(n)

	private static final int INF = 1 << 30;

	public static int arithmeticSlices(int[] a) {
		int diff = INF;
		int count = 0;
		int total = 0;
		for (int i = 1; i < a.length; i++) {
			int curr = a[i] - a[i - 1];
			if (curr == diff) {
				count += 1;
			} else {
				count = 0;
				diff = curr;
			}
			total += count;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(arithmeticSlices(a1)); // 3
		int[] a2 = { 1 };
		System.out.println(arithmeticSlices(a2)); // 0
	}
}
