package com.svetanis.algorithms.dp.sum.max.subarr;

// 53. Maximum subarray

public final class MaxSumSubArrayKadaneAllNeg {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int kadane(int[] a) {
		int max = a[0];
		int sum = a[0];
		for (int i = 1; i < a.length; ++i) {
			sum = Math.max(sum, 0) + a[i];
			max = Math.max(max, sum);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { -2, -3, -4, -1, -2, -1, -5, -3 };
		System.out.println(kadane(a1));

		int[] a2 = { -1, 4, -2, 5, -5, 2, -20, 6 };
		System.out.println(kadane(a2));

		int[] a3 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(kadane(a3));

		int[] a4 = { 2, -8, 3, -2, 4, -10 };
		System.out.println(kadane(a4));

		int[] a5 = { -3, -10, -5 };
		System.out.println(kadane(a5));
	}
}