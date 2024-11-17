package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.stream.IntStream;

// 416. Partition Equal Subset Sum

// Partition problem is to determine 
// whether a given set can be partitioned  
// into two subsets such that the sum 
// of elements in both subsets is same. 

public final class BalancedPartitionMemoization {

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
		dp[sum][n] = incl || excl;
		return dp[sum][n];
	}

	public static void main(String[] args) {
		int[] a2 = { 1, 5, 11, 5 };
		System.out.println(canPartition(a2)); // true
		int[] a3 = { 1, 2, 3, 5 };
		System.out.println(canPartition(a3)); // false
	}
}
