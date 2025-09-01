package com.svetanis.algorithms.greedy;

// 2918. Minimum Equal Sum of Two Arrays After Replacing Zeros

public final class MinEqualSumOfTwoArrays {
	// Time Complexity: O(n + m)
	// Space Complexity: O(1)

	public static long minSum(int[] a1, int[] a2) {
		long sum1 = 0;
		boolean hasZero = false;
		for (int num : a1) {
			hasZero |= num == 0;
			sum1 += Math.max(num, 1);
		}
		long sum2 = 0;
		for (int num : a2) {
			sum2 += Math.max(num, 1);
		}
		if (sum1 > sum2) {
			return minSum(a2, a1);
		}
		if (sum1 == sum2) {
			return sum1;
		}
		return hasZero ? sum2 : -1;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 2, 0, 1, 0 };
		int[] a2 = { 6, 5, 0 };
		System.out.println(minSum(a1, a2)); // 12

		int[] a3 = { 2, 0, 2, 0 };
		int[] a4 = { 1, 4 };
		System.out.println(minSum(a3, a4)); // -1

		int[] a5 = { 0, 16, 28, 12, 10, 15, 25, 24, 6, 0, 0 };
		int[] a6 = { 20, 15, 19, 5, 6, 29, 25, 8, 12 };
		System.out.println(minSum(a5, a6)); // 139
	}
}
