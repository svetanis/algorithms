package com.svetanis.algorithms.dp.common.lcs;

// 718. Maximum Length of Repeated Subarray

public final class MaxLenRepeatedSubArray {
	// Time Complexity: O(n*m)
	// Space Complexity: O(n*m)

	public static int maxLen(int[] a1, int[] a2) {
		int n = a1.length;
		int m = a2.length;
		int max = 0;
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (a1[i - 1] == a2[j - 1]) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 2, 1 };
		int[] a2 = { 3, 2, 1, 4, 7 };
		System.out.println(maxLen(a1, a2)); // 3

		int[] a3 = { 0, 0, 0, 0, 0 };
		int[] a4 = { 0, 0, 0, 0, 0 };
		System.out.println(maxLen(a3, a4)); // 5

	}
}
