package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.stream.IntStream;

// 416. Partition Equal Subset Sum

// Partition problem is to determine
// whether a given set can be partitioned
// into two subsets such that the sum
// of elements in both subsets is same.

// this file and BalancedPartitionTopDown memoize the SAME recursion:
// include or exclude one element, against a target of sum / 2. they are
// kept apart to show both input types. this one takes int[], which is what
// LeetCode 416 hands you and what every other file in this package uses;
// BalancedPartitionTopDown takes List<Integer>.
//
// two further differences follow from the array form. the index runs
// backward, n - 1 down to -1, which makes this the memoized twin of the
// backward isSum in BalancedPartitionRecursive. and the table axes are
// dp[sum][n], the reverse of the dp[n][sum] used elsewhere here.

public final class BalancedPartitionMemoization {
	// Time Complexity: O(sum * n)
	// Space Complexity: O(sum * n)

	public static boolean canPartition(int[] a) {
		int sum = IntStream.of(a).sum();
		if (sum % 2 != 0) {
			return false;
		}
		int n = a.length;
		Boolean[][] dp = new Boolean[sum + 1][n + 1];
		return isSum(a, n - 1, sum / 2, dp);
	}

	private static boolean isSum(int[] a, int n, int sum, Boolean[][] dp) {
		if (sum == 0) {
			return true;
		}
		if (n < 0 || sum < 0) {
			return false;
		}
		if (dp[sum][n] != null) {
			return dp[sum][n];
		}
		// 1. include last element
		boolean incl = isSum(a, n - 1, sum - a[n], dp);
		// 2. exclude last element
		boolean excl = isSum(a, n - 1, sum, dp);
		return dp[sum][n] = incl || excl;
	}

	public static void main(String[] args) {
		int[] a2 = { 1, 5, 11, 5 };
		System.out.println(canPartition(a2)); // true
		int[] a3 = { 1, 2, 3, 5 };
		System.out.println(canPartition(a3)); // false
	}
}
