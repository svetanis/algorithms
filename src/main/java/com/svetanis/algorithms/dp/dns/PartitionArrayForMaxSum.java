package com.svetanis.algorithms.dp.dns;

// 1043. Partition Array for Maximum Sum

public final class PartitionArrayForMaxSum {
	// Time Complexity: O(n * k)
	// Space Complexity: O(n)

	public static int maxSum(int[] a, int k) {
		int n = a.length;
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int max = 0;
			for (int j = i; j > Math.max(0, i - k); j--) {
				max = Math.max(max, a[j - 1]);
				int sum = dp[j - 1] + max * (i - j + 1);
				dp[i] = Math.max(dp[i], sum);
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 15, 7, 9, 2, 5, 10 };
		int[] a2 = { 1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3 };
		int[] a3 = { 1 };
		System.out.println(maxSum(a1, 3)); // 84
		System.out.println(maxSum(a2, 4)); // 83
		System.out.println(maxSum(a3, 1)); // 1
	}
}
