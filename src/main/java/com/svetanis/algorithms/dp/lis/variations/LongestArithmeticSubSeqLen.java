package com.svetanis.algorithms.dp.lis.variations;

import java.util.Arrays;

// 1027. Longest Arithmetic Subsequence

public final class LongestArithmeticSubSeqLen {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int lis(int[] a) {
		int n = a.length;
		int[][] dp = new int[n][1001];
		for (int[] row : dp) {
			Arrays.fill(row, 1);
		}
		int max = 0;
		for (int i = 1; i < n; i++) {
			for (int k = 0; k < i; k++) {
				int diff = a[i] - a[k] + 500;
				dp[i][diff] = Math.max(dp[i][diff], dp[k][diff] + 1);
				max = Math.max(max, dp[i][diff]);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 6, 9, 12 };
		System.out.println(lis(a1)); // 4: 3, 6, 9, 12
		int[] a2 = { 9, 4, 7, 2, 10 };
		System.out.println(lis(a2)); // 3: 4, 7, 10
		int[] a3 = { 20, 1, 15, 3, 10, 5, 8 };
		System.out.println(lis(a3)); // 4: 20, 15, 10, 5

	}
}