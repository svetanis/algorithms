package com.svetanis.algorithms.dp.sum.max.subarr;

//53. Maximum subarray

public final class MaxSumSubArrayBottomUp {

	public static int maxSubArrSum(int[] a) {
		int n = a.length;
		if (n == 0) {
			return 0;
		}
		int[] dp = new int[n];
		dp[0] = a[0];
		int max = dp[0];
		for (int i = 1; i < n; ++i) {
			dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { -2, -3, 4, -1, -2, 1, 5, -3 };
		System.out.println(maxSubArrSum(a1));

		int[] a2 = { -1, 4, -2, 5, -5, 2, -20, 6 };
		System.out.println(maxSubArrSum(a2));

		int[] a3 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(maxSubArrSum(a3)); // sum = 6
		// [4, -1, 2, 1]

		int[] a4 = { 2, -8, 3, -2, 4, -10 };
		System.out.println(maxSubArrSum(a4));

		int[] a5 = { -3, -10, -5 };
		System.out.println(maxSubArrSum(a5));
	}
}