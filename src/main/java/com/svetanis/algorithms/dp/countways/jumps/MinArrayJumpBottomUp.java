package com.svetanis.algorithms.dp.countways.jumps;

import java.util.Arrays;

// 45. Jump Game II

// Given an array of positive numbers,
// where each element represents the max number of jumps
// that can be made forward from that element,
// write a program to find the minimum number of jumps
// needed to reach the end of the array
// (starting from the first element).
// If an element is 0,
// then we cannot move through that element.

public final class MinArrayJumpBottomUp {
	// Time Complexity: O(n^2)

	private static final int INF = Integer.MAX_VALUE;

	public static int count(int[] a) {
		int n = a.length;
		int[] dp = new int[n];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int start = 0; start < n - 1; start++) {
			int jump = start + a[start];
			for (int end = start + 1; end <= jump && end < n; end++) {
				if (dp[start] != INF) {
					dp[end] = Math.min(dp[end], dp[start] + 1);
				}
			}
		}
		return dp[n - 1] == INF ? -1 : dp[n - 1];
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 1, 1, 4 };
		System.out.println(count(a1)); // 3

		int[] a2 = { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
		System.out.println(count(a2)); // 4

		int[] a3 = { 2, 3, 1, 1, 4 };
		System.out.println(count(a3)); // 2

		int[] a4 = { 2, 3, 0, 1, 4 };
		System.out.println(count(a4)); // 2
	}
}
