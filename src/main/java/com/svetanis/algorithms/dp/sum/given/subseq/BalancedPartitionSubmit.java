package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.stream.IntStream;

// 416. Partition Equal Subset Sum

// Partition problem is to determine 
// whether a given set can be partitioned  
// into two subsets such that the sum 
// of elements in both subsets is same. 

public final class BalancedPartitionSubmit {
	// Time Complexity: O(target * n)
	// Space Complexity: O(target)

	public static boolean canPartition(int[] nums) {
		int sum = IntStream.of(nums).sum();
		if (sum % 2 != 0) {
			return false;
		}
		int target = sum / 2;
		boolean[] dp = new boolean[target + 1];
		dp[0] = true;
		for (int num : nums) {
			for (int s = target; s >= num; s--) {
				dp[s] = dp[s] || dp[s - num];
			}
		}
		return dp[target];
	}

	public static void main(String[] args) {
		int[] a2 = { 1, 5, 11, 5 };
		System.out.println(canPartition(a2)); // true
		int[] a3 = { 1, 2, 3, 5 };
		System.out.println(canPartition(a3)); // false
	}
}
