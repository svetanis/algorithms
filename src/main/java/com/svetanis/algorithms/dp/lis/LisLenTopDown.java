package com.svetanis.algorithms.dp.lis;

import static java.lang.Math.max;

// 300. Longest Increasing Subsequence

// Given a number sequence, find the length 
// of its Longest Increasing Subsequence (LIS). 
// In an increasing subsequence, all the elements 
// are in increasing order (from lowest to highest).

public final class LisLenTopDown {
  // Time Complexity: O(n^2)

	public static int lis(int[] a) {
		int n = a.length;
		int[][] dp = new int[n][n + 1];
		return lis(a, 0, -1, dp);
	}

	private static int lis(int[] a, int index, int prev, int[][] dp) {
		int n = a.length;
		// base case
		if (index == n) {
			return 0;
		}
		if (dp[index][prev + 1] != 0) {
			return dp[index][prev + 1];
		}

		// include
		int incl = 0;
		if (prev == -1 || a[index] > a[prev]) {
			incl = 1 + lis(a, index + 1, index, dp);
		}
		// exclude
		int excl = lis(a, index + 1, prev, dp);
		dp[index][prev + 1] = max(incl, excl);
		return dp[index][prev + 1];
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