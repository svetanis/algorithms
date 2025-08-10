package com.svetanis.algorithms.prefixsum;

// 1413. Minimum Value to Get Positive Step by Step Sum

public final class MinStartValue {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minStartValue(int[] a) {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int num : a) {
			sum += num;
			min = Math.min(min, sum);
		}
		return Math.max(1, 1 - min);
	}

	public static void main(String[] args) {
		int[] a1 = { -3, 2, -3, 4, 2 };
		System.out.println(minStartValue(a1)); // 5

		int[] a2 = { 1, 2 };
		System.out.println(minStartValue(a2)); // 1

		int[] a3 = { 1, -2, -3 };
		System.out.println(minStartValue(a3)); // 5
	}
}
