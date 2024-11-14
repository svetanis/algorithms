package com.svetanis.algorithms.dp.lis;

import java.util.Arrays;

// 300. Longest Increasing Subsequence

// Given a number sequence, find the length 
// of its Longest Increasing Subsequence (LIS). 
// In an increasing subsequence, all the elements 
// are in increasing order (from lowest to highest).

public final class LisLenDPBinarySearch {
	// Time Complexity: O(n log n)

	private final static int INF = Integer.MAX_VALUE;

	public static int lis(int[] a) {
		int n = a.length;
		int[] dp = new int[n + 1];
		Arrays.fill(dp, INF);
		dp[0] = -INF;

		for (int i = 0; i < n; i++) {
			int index = upperBound(dp, a[i]);
			if (dp[index - 1] < a[i] && a[i] < dp[index]) {
				dp[index] = a[i];
			}
		}
		int max = 0;
		for (int i = 0; i <= n; i++) {
			if (dp[i] < INF) {
				max = i;
			}
		}
		return max;
	}

	private static int upperBound(int[] a, int target) {
		int low = 0;
		int high = a.length;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (a[mid] > target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
		System.out.println(lis(a1)); // 6
		int[] a2 = { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println(lis(a2)); // 4
		int[] a3 = { 0, 1, 0, 3, 2, 3 };
		System.out.println(lis(a3)); // 4
		int[] a4 = { 7, 7, 7, 7, 7, 7, 7 };
		System.out.println(lis(a4)); // 1
		int[] a5 = { 4, 2, 3, 6, 10, 1, 12 };
		System.out.println(lis(a5)); // 5
		int[] a6 = { -4, 10, 3, 7, 15 };
		System.out.println(lis(a6)); // 4
		int[] a7 = { 1, 2, 4, 3 };
		System.out.println(lis(a7)); // 3
		int[] a8 = { 50, 3, 10, 7, 40, 80 };
		System.out.println(lis(a8)); // 4
		int[] a9 = { 5, 46, 85, 26, 1, 5, 78, 45, 122, 56, 47, 26 };
		System.out.println(lis(a9)); // 4
		int[] a10 = { 0, 0, 1, 6, 0, 0, 0 };
		System.out.println(lis(a10)); // 3
	}
}