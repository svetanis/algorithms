package com.svetanis.algorithms.dp.sum.given.subseq;

// 494. Target Sum

public final class TargetSumSpaceOptimized {
	// Time complexity: O(sum * n)

	public static int count(int[] a, int k) {
		int total = 0;
		for (int num : a) {
			total += num;
		}
		if (total < k || (total - k) % 2 == 1) {
			return 0;
		}
		int target = (total - k) / 2;
		return countSubsets(a, target);
	}

	private static int countSubsets(int[] a, int sum) {
		int[] dp = new int[sum + 1];
		dp[0] = 1;
		for (int num : a) {
			for (int s = sum; s >= num; s--) {
				dp[s] += dp[s - num];
			}
		}
		return dp[sum];
	}

	public static void main(String[] args) {
		int[] a3 = { 1, 1, 1, 1, 1 };
		System.out.println(count(a3, 3)); // 5

		int[] a4 = { 1 };
		System.out.println(count(a4, 1)); // 1

		int[] a5 = { 0, 0, 0, 0, 0, 0, 0, 0, 1 };
		System.out.println(count(a5, 1)); // 256

		int[] a6 = { 1, 2, 1 };
		System.out.println(count(a6, 0)); // 2
	}
}
