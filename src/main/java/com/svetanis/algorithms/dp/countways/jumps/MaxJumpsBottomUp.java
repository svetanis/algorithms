package com.svetanis.algorithms.dp.countways.jumps;

import java.util.Arrays;

// 2770. Maximum Number of Jumps to Reach the Last Index

public final class MaxJumpsBottomUp {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	private final static int INF = -Integer.MAX_VALUE;

	public static int count(int[] a, int target) {
		int n = a.length;
		int[] dp = new int[n];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int start = 0; start < n - 1; start++) {
			for (int end = start + 1; end < n; end++) {
				int jump = Math.abs(a[end] - a[start]);
				if (jump <= target && dp[start] != INF) {
					dp[end] = Math.max(dp[end], dp[start] + 1);
				}
			}
		}
		return dp[n - 1] == INF ? -1 : dp[n - 1];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 6, 4, 1, 2 };
		System.out.println(count(a1, 2)); // 3

		int[] a2 = { 1, 3, 6, 4, 1, 2 };
		System.out.println(count(a2, 3)); // 5

		int[] a3 = { 1, 3, 6, 4, 1, 2 };
		System.out.println(count(a3, 0)); // -1

		int[] a4 = { 0, 2, 1, 3 };
		System.out.println(count(a4, 1)); // -1
	}
}
